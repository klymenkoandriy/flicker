package software.sigma.klym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import software.sigma.klym.model.User;

/**
 * @author Andriy Klymenko
 */
@RestController
@RequestMapping(value = "api/users")
public class UserRestController {

    @Autowired
    OAuth2RestTemplate restTemplate;

    @Value("${users.url:http://user-service}/api")
    String userUrl;

    @GetMapping(value = "/get-by-username")
    public User getByUserName(@RequestParam(value = "username") String name) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(userUrl + "/users/get-by-username")
                .queryParam("username", name);
        User user = restTemplate.getForObject(builder.toUriString(), User.class);
        return  user;
    }

    @GetMapping(value = "/get-by-id")
    public User getById(@RequestParam(value = "id") String id) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(userUrl + "/users/get-by-id")
                .queryParam("id", id);
        User user = restTemplate.getForObject(builder.toUriString(), User.class);
        return  user;
    }
}
