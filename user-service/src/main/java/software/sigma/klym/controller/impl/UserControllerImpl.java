package software.sigma.klym.controller.impl;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import software.sigma.klym.controller.api.UserController;
import software.sigma.klym.model.User;

import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Andriy Klymenko
 */
@RestController
public class UserControllerImpl implements UserController {

    final List<User> users = Collections.synchronizedList(new LinkedList<>());

    UserControllerImpl() {
        users.add(new User("1", "user", "User", "Me", "email@com", "password",
                LocalDate.of(1990, 02, 20)));
        users.add(new User("2", "tester", "Tester", "Who", "email2@com", "password2",
                LocalDate.of(2000, 01, 10)));
    }

    @Override
    public User getByUsername(@RequestParam(value = "username") String name) {
        for (User user : users) {
            if (user.getUsername().equals(name)) {
                return user;
            }
        }
        return  null;
    }

    @Override
    public User saveUser(@RequestBody User user) {
        users.add(0, user);
        return user;
    }

    @Override
    public List<User> getAll() {
        return users;
    }
}
