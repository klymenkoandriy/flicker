package software.sigma.klym.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value = "/api/messages")
public class MessageRestController {

    @Autowired
    private UserFeignService userFeignService;

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping(value = "/by-username")
    public List<MessageDTO> getByUserName(Principal principal, @RequestParam(value = "username") String username) {
        List<Message> messages = messageRepository.findByUsername(username);
        User user = userFeignService.getByUsername(principal.getName());
        List<MessageDTO> result = new ArrayList<>();
        for (Message message : messages) {
            result.add(new MessageDTO(message.getId(), message.getText(), username, user.getFirstName(), user.getLastName(), message.getCreatedAt()));
        }
        return  result;
    }

    @GetMapping("")
    public List<Message> getAll() {
        return messageRepository.findAll();
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
