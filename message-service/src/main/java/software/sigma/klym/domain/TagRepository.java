package software.sigma.klym.domain;

import software.sigma.klym.model.Tag;

import java.util.List;

/**
 * @author Andriy Klymenko
 */
public interface TagRepository {

    List<Tag> findHottest(int number);
}
