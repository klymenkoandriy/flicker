package software.sigma.klym.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import software.sigma.klym.domain.MessageRepository;
import software.sigma.klym.model.Message;
import software.sigma.klym.model.MessageDTO;
import software.sigma.klym.model.User;
import software.sigma.klym.service.UserFeignService;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Andriy Klymenko
 */
@Api(value = "Messages data operations", description = "RESTful API to interact with messages resources.")
@RestController
@RequestMapping(value = "/api/v1/messages/items")
public class MessageRestController {

    @Autowired
    private UserFeignService userFeignService;

    @Autowired
    private MessageRepository messageRepository;

    @ApiOperation(value = "Get messages", httpMethod = "GET", responseContainer = "List", response = MessageDTO.class,
            notes = "Get messages using parameters.")
    @GetMapping
    public ResponseEntity getMessages(Principal principal,
            @ApiParam(value = "The search string is used to find messages by username.", required = false, defaultValue = "")
                @RequestParam(value = "username",  required = false) String username,
            @ApiParam(value = "The search string is used to find messages by hash tag.", required = false, defaultValue = "")
                @RequestParam(value = "tag",  required = false) String tag,
            @ApiParam(value = "The page number for pagination.", required = false, defaultValue = "0")
                @RequestParam(value = "page", required = false, defaultValue = "0") Integer page) {

        User user = userFeignService.getByUsername(principal.getName());

        if (StringUtils.isNotBlank(username) && !user.getUsername().equals(username)) {
            return ResponseEntity.badRequest().body(null);
        }

        List<Message> messages = messageRepository.findByUsername(username);

        List<MessageDTO> result = new ArrayList<>();
        for (Message message : messages) {
            result.add(new MessageDTO(message.getId(), message.getText(), username, user.getFirstName(), user.getLastName(), message.getCreatedAt()));
        }
        return ResponseEntity.ok().body(messages);

    }

    @ApiOperation(value = "Save message", httpMethod = "POST", response = MessageDTO.class,
            notes = "Saves message.")
    @PostMapping
    public Message saveMessage(Principal principal,
            @ApiParam(value = "The text to save.", required = true) @RequestParam String text) {
        Message message = new Message();
        message.setText(text);
        message.setUsername(principal.getName());
        message.setCreatedAt(LocalDateTime.now());
        return messageRepository.save(message);
    }

}
