package software.sigma.klym.messageservice.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import software.sigma.klym.messageservice.model.Tag;

/**
 * Tag repository.
 *
 * @author Andriy Klymenko
 */
public interface TagRepository extends MongoRepository<Tag, String> {

    /**
     * Returns tag with specified name.
     *
     * @param name name
     * @return tag
     */
    Tag findByName(String name);
}
