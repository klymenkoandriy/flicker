package software.sigma.klym.domain;

import org.springframework.stereotype.Repository;
import software.sigma.klym.model.Tag;

import java.util.Arrays;
import java.util.List;

/**
 * @author Andriy Klymenko
 */
@Repository
public class TagRepositoryImpl implements TagRepository {

    @Override public List<Tag> findHottest(int number) {
        return Arrays.asList(new Tag("1", "aaa", 5), new Tag("2", "bbb", 3));
    }
}
