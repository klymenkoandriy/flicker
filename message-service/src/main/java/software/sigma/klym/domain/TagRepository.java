package software.sigma.klym.domain;

import software.sigma.klym.model.Tag;

import java.util.List;

/**
 * Tag repository.
 *
 * @author Andriy Klymenko
 */
public interface TagRepository {

    /**
     * Returns the specified number of tags sorted by the field 'used'.
     *
     * @param number number of tags
     * @return tags
     */
    List<Tag> findHottest(int number);

    /**
     * Saves tag.
     *
     * @param tag tag
     * @return saved tag
     */
    Tag save(Tag tag);

    /**
     * Returns tag with specified name.
     *
     * @param name name
     * @return tag
     */
    Tag findByName(String name);
}
