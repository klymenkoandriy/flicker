package software.sigma.klym.messageservice.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import software.sigma.klym.messageservice.model.Tag;

/**
 * Tag repository.
 *
 * @author Andriy Klymenko
 */
public interface TagRepository extends MongoRepository<Tag, String> {

    /**
     * Returns the specified number of sorted tags.
     *
     * @param pageRequest page request
     * @return Page object with list of tags
     */
    Page<Tag> findAll(Pageable pageRequest);

    /**
     * Returns tag with specified name.
     *
     * @param name name
     * @return tag
     */
    Tag findByName(String name);
}
