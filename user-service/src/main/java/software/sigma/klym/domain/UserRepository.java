package software.sigma.klym.domain;

import software.sigma.klym.model.User;

/**
 * @author Andriy Klymenko
 */
public interface UserRepository {

    User findByUsername(String username);
    User save(User user);
}
