package software.sigma.klym.messageservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.sigma.klym.messageservice.domain.TagRepository;
import software.sigma.klym.messageservice.model.Tag;
import software.sigma.klym.messageservice.service.TagService;

import java.util.Set;

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
    public void addTags(Set<String> tagNames) {
        for (String tagName : tagNames) {
            Tag tag = tagRepository.findByName(tagName);
            if (tag == null) {
                tag = new Tag(null, tagName, 1);
            } else {
                tag.setUsed(tag.getUsed() + 1);
            }
            tagRepository.save(tag);
        }
    }
}
