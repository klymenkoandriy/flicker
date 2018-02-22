package software.sigma.klym.domain;

import org.springframework.stereotype.Repository;
import software.sigma.klym.model.User;

import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Andriy Klymenko
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    final List<User> users = Collections.synchronizedList(new LinkedList<>());

    UserRepositoryImpl() {
        users.add(new User("1", "user", "User", "Me", "email@com", "password",
                LocalDate.of(1990, 02, 20)));
        users.add(new User("2", "tester", "Tester", "Who", "email2@com", "password2",
                LocalDate.of(2000, 01, 10)));
    }

    @Override
    public User findByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return  null;
    }

    @Override
    public User save(User user) {
        users.add(0, user);
        return user;
    }
}
