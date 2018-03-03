package software.sigma.klym.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import software.sigma.klym.messageservice.domain.TagRepository;
import software.sigma.klym.messageservice.model.Tag;
import software.sigma.klym.messageservice.service.TagService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Andriy Klymenko
 */
@RunWith(SpringRunner.class)
@ImportAutoConfiguration(TagServiceTestConfiguration.class)
public class TagServiceTest {

    private static final String TAG_NAME = "tag";

    private Tag tag;

    @Autowired
    private TagService service;

    @MockBean
    private TagRepository tagRepository;

    @Before
    public void init() {
        tag = new Tag("1", TAG_NAME, 1);
    }

    @Test
    public void shouldSaveNewTag() {
        when(tagRepository.findByName(TAG_NAME)).thenReturn(null);
        Set<String> tagNames = new HashSet<>(Arrays.asList(TAG_NAME));
        service.addTags(tagNames);

        ArgumentCaptor<Tag> tagCaptor = ArgumentCaptor.forClass(Tag.class);
        verify(tagRepository).save(tagCaptor.capture());
        assertEquals(TAG_NAME, tagCaptor.getValue().getName());
        assertEquals(1, tagCaptor.getValue().getUsed());
        assertEquals(null, tagCaptor.getValue().getId());
    }

    @Test
    public void shouldUpdateExistingTag() {
        when(tagRepository.findByName(TAG_NAME)).thenReturn(tag);
        Set<String> tagNames = new HashSet<>(Arrays.asList(TAG_NAME));
        service.addTags(tagNames);

        ArgumentCaptor<Tag> tagCaptor = ArgumentCaptor.forClass(Tag.class);
        verify(tagRepository).save(tagCaptor.capture());
        assertEquals(TAG_NAME, tagCaptor.getValue().getName());
        assertEquals(tag, tagCaptor.getValue());
        assertEquals(2, tag.getUsed());
    }

}
