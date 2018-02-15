package software.sigma.klym.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import software.sigma.klym.model.User;

/**
 * @author Andriy Klymenko
 */
@Service
public class UserServiceImpl implements UserService {

    @Value("${users.url:http://user-service}/api")
    String userUrl;

    @Autowired
    RestTemplate restTemplate;

//    @Autowired
//    OAuth2RestTemplate restTemplate;

    @Override
    public User findByUsername(String username) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(userUrl + "/users/get-by-username")
                .queryParam("username", username);
        User user = restTemplate.getForObject(builder.toUriString(), User.class);
        return  user;
    }
}
