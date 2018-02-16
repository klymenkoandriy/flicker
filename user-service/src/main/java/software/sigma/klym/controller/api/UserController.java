package software.sigma.klym.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import software.sigma.klym.model.User;

import java.util.List;

/**
 * @author Andriy Klymenko
 */
public interface UserController {

    @RequestMapping(method = RequestMethod.GET, value = "api/users/get-by-username")
    User getByUsername(String name);

    @RequestMapping(method = RequestMethod.POST, value = "api/users")
    User saveUser(User user);

    @RequestMapping(method = RequestMethod.GET, value = "api/users")
    List<User> getAll();
}
