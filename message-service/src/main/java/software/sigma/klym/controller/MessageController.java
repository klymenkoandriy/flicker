package software.sigma.klym.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import software.sigma.klym.model.Message;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Andriy Klymenko
 */
@RestController
@RequestMapping(value = "api/messages")
public class MessageController {

    final List<Message> messages = Collections.synchronizedList(new LinkedList<>());

    @GetMapping()
    public List<Message> getMessages(Principal principal) {
        return messages;
    }

    @GetMapping(value = "/get-by-username")
    public List<Message> getByUserName(@RequestParam(value = "username") String name) {
        return  messages;
    }

    @PostMapping()
    public Message postMessage(Principal principal, @RequestBody Message message) {
        message.setId(principal.getName());
        message.setCreatedAt(LocalDateTime.now());
        messages.add(0, message);
        return message;
    }


}
