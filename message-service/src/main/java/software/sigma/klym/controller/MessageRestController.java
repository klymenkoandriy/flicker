package software.sigma.klym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import software.sigma.klym.domain.MessageRepository;
import software.sigma.klym.model.Message;
import software.sigma.klym.model.MessageDTO;
import software.sigma.klym.model.User;
import software.sigma.klym.service.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Andriy Klymenko
 */
@RestController
@RequestMapping(value = "api/messages")
public class MessageRestController {

//    @Autowired
//    OAuth2RestTemplate restTemplate;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping(value = "/get-by-username")
    public List<MessageDTO> getByUserName(Principal principal, @RequestParam(value = "username") String name) {
        List<Message> messages = messageRepository.findByUsername(name);
        User user = userService.findByUsername(principal.getName());
        List<MessageDTO> result = new ArrayList<>();
        for (Message message : messages) {
            result.add(new MessageDTO(message.getId(), message.getText(), name, user.getFirstName(), user.getLastName()));
        }
        return  result;
    }

    @GetMapping()
    public List<Message> getAll() {
        return messageRepository.findAll();
    }

    @PostMapping()
    Message saveMessage(Principal principal, @RequestParam String text) {
        Message message = new Message();
        message.setText(text);
        message.setUsername(principal.getName());
        return messageRepository.save(message);
    }

}
