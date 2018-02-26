package software.sigma.klym.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import software.sigma.klym.domain.TagRepository;
import software.sigma.klym.model.Tag;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Tag RESTful controller.
 *
 * @author Andriy Klymenko
 */
@Api(value = "Tags data operations", description = "RESTful API to interact with tags resources.")
@RestController
@RequestMapping(value = "/api/v1/messages/tags")
public class TagRestController {

    private static final String DEFAULT_TAG_SIZE = "10";

    private static final String SORTED_FIELD = "used";

    @Autowired
    private TagRepository tagRepository;

    /**
     * Returns the specified number of tags sorted by the field 'used'.
     *
     * @param number number of tags
     * @return tags
     */
    @ApiOperation(value = "Get most popular tags", httpMethod = "GET", responseContainer = "List", response = String.class,
            notes = "Returns the specified number of the most popular tags.")
    @GetMapping
    public List<String> getHottestTags(
            @ApiParam(value = "Number of tags.", required = false, defaultValue = DEFAULT_TAG_SIZE)
            @RequestParam(value = "number", required = false, defaultValue = DEFAULT_TAG_SIZE) Integer number) {

        Pageable pageRequest = new PageRequest(0, number, new Sort(Sort.Direction.DESC, SORTED_FIELD));
        List<Tag> tags = tagRepository.findAll(pageRequest).getContent();
        return tags.stream().map(Tag::getName).collect(Collectors.toList());
    }
}
