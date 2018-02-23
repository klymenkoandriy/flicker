package software.sigma.klym.domain;

import org.springframework.data.repository.CrudRepository;
import software.sigma.klym.model.Tag;

/**
 * Tag repository.
 *
 * @author Andriy Klymenko
 */
public interface TagRepository extends CrudRepository<Tag, String> {

//    /**
//     * Returns the specified number of tags sorted by the field 'used'.
//     *
//     * @param number number of tags
//     * @return tags
//     */
//    List<Tag> findHottest(int number);

    /**
     * Returns tag with specified name.
     *
     * @param name name
     * @return tag
     */
    Tag findByName(String name);
}
