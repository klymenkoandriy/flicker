package software.sigma.flicker.user.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import software.sigma.flicker.user.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller for user.
 *
 * @author Andriy Klymenko
 */
@Log4j2
@RestController
@RequestMapping(value = "/user")
public class UserController {

    /**
     * Endpoint to get all users using.
     *
     * @return list of user
     */
    @GetMapping
    public List<User> fluxFindAll() {
        return new ArrayList<>();
    }

    /**
     * Endpoint to get user by id.
     *
     * @param id user id
     * @return user
     */
    @GetMapping(path = "/{id}")
    public User get(@PathVariable("id") String id) {
        return new User();
    }
}
