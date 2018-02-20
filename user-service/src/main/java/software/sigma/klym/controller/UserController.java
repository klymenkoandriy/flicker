package software.sigma.klym.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import software.sigma.klym.model.User;

import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Andriy Klymenko
 */
@RestController
@RequestMapping(value = "/api/v1/users")
public class UserController {

    final List<User> users = Collections.synchronizedList(new LinkedList<>());

    UserController() {
        users.add(new User("1", "user", "User", "Me", "email@com", "password",
                LocalDate.of(1990, 02, 20)));
        users.add(new User("2", "tester", "Tester", "Who", "email2@com", "password2",
                LocalDate.of(2000, 01, 10)));
    }

    @GetMapping(value = "/by-username")
    public User getByUsername(@RequestParam(value = "username") String name) {
        for (User user : users) {
            if (user.getUsername().equals(name)) {
                return user;
            }
        }
        return  null;
    }

    @PostMapping("")
    public User saveUser(@RequestBody User user) {
        users.add(0, user);
        return user;
    }

    @GetMapping("")
    public List<User> getAll() {
        return users;
    }
}
