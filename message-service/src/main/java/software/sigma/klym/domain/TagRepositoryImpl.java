package software.sigma.klym.domain;

import org.springframework.stereotype.Repository;
import software.sigma.klym.model.Tag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * @author Andriy Klymenko
 */
@Repository
public class TagRepositoryImpl implements TagRepository {

    final List<Tag> tags = Collections.synchronizedList(new ArrayList<>());

    /**
     * Mock data.
     */
    TagRepositoryImpl() {
        tags.add(new Tag("1", "aaa", 5));
        tags.add(new Tag("2", "bbb", 3));
    }

    @Override
    public List<Tag> findHottest(int number) {
        return tags;
    }

    @Override
    public Tag save(Tag tag) {
        tag.setId(UUID.randomUUID().toString());
        tags.add(tag);
        return tag;
    }

    @Override
    public Tag findByName(String name) {
        for (Tag tag : tags) {
            if (tag.getName().equals(name)) {
                return tag;
            }
        }
        return null;
    }

}
