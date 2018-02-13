package software.sigma.klym;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import software.sigma.klym.model.Message;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@SpringBootApplication
@EnableResourceServer
@EnableEurekaClient
@RestController
public class MessageResourceApplication extends ResourceServerConfigurerAdapter {

    final List<Message> messages = Collections.synchronizedList(new LinkedList<>());

    @GetMapping(path = "api/messages")
    List<Message> getMessages(Principal principal) {
        return messages;
    }

    @PostMapping(path = "api/messages") Message postMessage(Principal principal, @RequestBody Message message) {
        message.setUsername(principal.getName());
        message.setCreatedAt(LocalDateTime.now());
        messages.add(0, message);
        return message;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/**").access("#oauth2.hasScope('read')")
                .antMatchers(HttpMethod.POST, "/api/**").access("#oauth2.hasScope('write')");
    }

    public static void main(String[] args) {
        SpringApplication.run(MessageResourceApplication.class, args);
    }

}
