package software.sigma.klym.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import software.sigma.klym.domain.TagRepository;
import software.sigma.klym.model.Tag;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Andriy Klymenko
 */
@Api(value = "Tags data operations", description = "RESTful API to interact with tags resources.")
@RestController
@RequestMapping(value = "/api/v1/messages/tags")
public class TagRestController {

    @Autowired
    private TagRepository tagRepository;

    @ApiOperation(value = "Get most popular tags", httpMethod = "GET", responseContainer = "List", response = String.class,
            notes = "Returns the specified number of the most popular tags.")
    @GetMapping
    public List<String> getHottestTags(
            @ApiParam(value = "Number of tags.", required = false, defaultValue = "")
            @RequestParam(value = "number", required = false, defaultValue = "10") Integer number) {
        return tagRepository.findHottest(number).stream().map(Tag::getName).collect(Collectors.toList());
    }
}
