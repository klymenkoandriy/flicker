package software.sigma.klym.service;

import software.sigma.klym.model.User;

/**
 * @author Andriy Klymenko
 */
public interface UserService {
    User findByUsername(String username);
}
