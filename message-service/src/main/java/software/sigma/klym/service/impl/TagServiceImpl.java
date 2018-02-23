package software.sigma.klym.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.sigma.klym.domain.TagRepository;
import software.sigma.klym.model.Tag;
import software.sigma.klym.service.TagService;

import java.util.List;

/**
 * Tag service implementation.
 *
 * @author Andriy Klymenko
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Override
    public void addTags(List<String> tagNames) {
        for (String tagName : tagNames) {
            Tag tag = tagRepository.findByName(tagName);
            if (tag == null) {
                tag = new Tag();
                tag.setName(tagName);
                tag.setUsed(1);
            } else {
                tag.setUsed(tag.getUsed() + 1);
            }
            tagRepository.save(tag);
        }
    }
}
