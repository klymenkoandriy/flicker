package software.sigma.klym.domain;

import org.springframework.data.repository.CrudRepository;
import software.sigma.klym.model.User;

/**
 * User repository.
 *
 * @author Andriy Klymenko
 */
public interface UserRepository extends CrudRepository<User, String> {

    /**
     * Returns the User entity with the specified username.
     *
     * @param username username
     * @return user data
     */
    User findByUsername(String username);

}
