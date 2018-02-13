package software.sigma.klym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import software.sigma.klym.model.Message;
import software.sigma.klym.service.MessageService;

import java.security.Principal;
import java.util.List;

/**
 * @author Andriy Klymenko
 */
@RestController
@RequestMapping(value = "api/messages")
public class MessageRestController {

    @Autowired
    OAuth2RestTemplate restTemplate;

    @Autowired
    private MessageService messageService;

    @Value("${messages.url:http://message-service}/api")
    String messagesUrl;

    @GetMapping(value = "/get-by-username")
    public List<Message> getByUserName(@RequestParam(value = "username") String name) {
        return  messageService.findByUsername(name);
    }

    @GetMapping()
    public List<Message> getAll() {
        return messageService.findAll();
    }

    @PostMapping()
    Message saveMessage(Principal principal, @RequestParam String text) {
        Message message = new Message();
        message.setText(text);
        return messageService.save(message);
    }

}
