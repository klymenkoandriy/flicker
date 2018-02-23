package software.sigma.klym.domain;

import software.sigma.klym.model.User;

/**
 * User repository.
 *
 * @author Andriy Klymenko
 */
public interface UserRepository {

    /**
     * Returns the User entity with the specified username.
     *
     * @param username username
     * @return user data
     */
    User findByUsername(String username);

    /**
     * Returns the User entity with the specified id.
     *
     * @param id id
     * @return user data
     */
    User findById(String id);

    /**
     * Saves User entity.
     *
     * @param user user data
     * @return user data
     */
    User save(User user);
}
