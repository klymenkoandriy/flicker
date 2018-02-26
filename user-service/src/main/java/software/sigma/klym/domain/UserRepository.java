package software.sigma.klym.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import software.sigma.klym.model.User;

/**
 * User repository.
 *
 * @author Andriy Klymenko
 */
public interface UserRepository extends MongoRepository<User, String> {

    /**
     * Returns the User entity with the specified username.
     *
     * @param username username
     * @return user data
     */
    User findByUsername(String username);

}
