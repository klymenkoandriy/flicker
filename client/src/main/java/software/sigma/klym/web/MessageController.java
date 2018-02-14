package software.sigma.klym.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import software.sigma.klym.domain.MessageRepository;

import java.security.Principal;

@Controller
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @RequestMapping("/")
    String home(Model model) {
        model.addAttribute("messages", messageRepository.findAll());
        return "index";
    }

    @RequestMapping(path = "messages", method = RequestMethod.POST)
    String postMessages(Principal principal, @RequestParam String text) {
//        Message message = new Message();
//        message.setText(text);
//        restTemplate.exchange(RequestEntity
//                .post(UriComponentsBuilder.fromHttpUrl(messagesUrl).pathSegment("messages").build().toUri())
//                .body(message), Message.class);
        return "redirect:/";
    }

}
