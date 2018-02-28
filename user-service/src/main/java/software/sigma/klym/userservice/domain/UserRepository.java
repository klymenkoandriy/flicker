package software.sigma.klym.userservice.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import software.sigma.klym.userservice.model.User;

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
