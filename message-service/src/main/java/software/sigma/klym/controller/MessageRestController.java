package software.sigma.klym.controller;

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
@RestController
@RequestMapping(value = "/api/v1/messages")
public class MessageRestController {

    @Autowired
    private UserFeignService userFeignService;

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping(value = "")
    public ResponseEntity getByUserName(Principal principal,
            @RequestParam(value = "username",  required = false) String usernameParam,
            @RequestParam(value = "tag",  required = false) String tagParam,
            @RequestParam(value = "page", required = false) String pageParam) {

        String username = (usernameParam != null) ? usernameParam : "";
        String tag = (tagParam != null) ? tagParam : "";
        int page = StringUtils.isNotBlank(pageParam) ? Integer.parseInt(pageParam) : 0;

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

    @PostMapping("")
    public Message saveMessage(Principal principal, @RequestParam String text) {
        Message message = new Message();
        message.setText(text);
        message.setUsername(principal.getName());
        message.setCreatedAt(LocalDateTime.now());
        return messageRepository.save(message);
    }

}
