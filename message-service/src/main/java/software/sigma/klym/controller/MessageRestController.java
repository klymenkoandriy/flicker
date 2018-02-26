package software.sigma.klym.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import software.sigma.klym.domain.MessageRepository;
import software.sigma.klym.model.Message;
import software.sigma.klym.model.MessageDTO;
import software.sigma.klym.model.MessageResponse;
import software.sigma.klym.model.User;
import software.sigma.klym.service.TagService;
import software.sigma.klym.service.UserFeignService;
import software.sigma.klym.util.MessageUtils;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Message RESTful controller.
 *
 * @author Andriy Klymenko
 */
@Api(value = "Messages data operations", description = "RESTful API to interact with messages resources.")
@RestController
@RequestMapping(value = "/api/v1/messages/items")
public class MessageRestController {

    private static final String MESSAGE_NOT_FOUND = "Message not found.";

    private static final String DELETING_FORBIDDEN = "Deleting another user's message is forbidden.";

    private static final String DEFAULT_PAGE_NUMBER = "0";

    private static final String DEFAULT_PAGE_SIZE = "5";

    private static final String SORTED_FIELD = "createdAt";

    @Autowired
    private UserFeignService userFeignService;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private TagService tagService;
    /**
     * Returns messages according to the search parameters.
     *
     * @param principal principal
     * @param tag tag
     * @param pageNumber page number
     * @param pageSize page size
     * @return response with entities
     */
    @ApiOperation(value = "Get messages", httpMethod = "GET", response = MessageResponse.class,
            notes = "Get messages using parameters.")
    @GetMapping
    public ResponseEntity<MessageResponse> getMessages(Principal principal,
            @ApiParam(value = "The search string that used to find messages by hash tag.", required = false, defaultValue = "")
                @RequestParam(value = "tag",  required = false) String tag,
            @ApiParam(value = "The page number for pagination.", required = false, defaultValue = DEFAULT_PAGE_NUMBER)
                @RequestParam(value = "pageNumber", required = false, defaultValue = DEFAULT_PAGE_NUMBER) Integer pageNumber,
            @ApiParam(value = "The page size for pagination.", required = false, defaultValue = DEFAULT_PAGE_SIZE)
                @RequestParam(value = "pageSize", required = false, defaultValue = DEFAULT_PAGE_SIZE) Integer pageSize) {

        Page<Message> pageMessages;
        Pageable pageRequest = new PageRequest(pageNumber, pageSize, new Sort(Sort.Direction.DESC, SORTED_FIELD));

        if (StringUtils.isBlank(tag)) {
            pageMessages = messageRepository.findAll(pageRequest);
        } else {
            pageMessages = messageRepository.findByTagNamesContaining(pageRequest, tag);
        }

        List<MessageDTO> messageDTOs = new ArrayList<>();
        for (Message message : pageMessages.getContent()) {
            User user = userFeignService.getByUsername(message.getUsername());
            messageDTOs.add(new MessageDTO(message.getId(), message.getText(), user.getUsername(), user.getFirstName(), user.getLastName(),
                    message.getCreatedAt()));
        }

        MessageResponse messageResponse = new MessageResponse(messageDTOs, pageMessages.isFirst(), pageMessages.isLast(), pageMessages.getNumberOfElements(),
                pageMessages.getTotalPages(), pageMessages.getTotalElements(), pageMessages.getSize(), pageMessages.getNumber());

        return ResponseEntity.ok().body(messageResponse);
    }

    /**
     * Creates and saves message with certain text.
     * Extracts tags from the text and saves them as Tag entities.
     *
     * @param principal principal
     * @param text text
     * @return saved message
     */
    @ApiOperation(value = "Save message", httpMethod = "POST", response = MessageDTO.class,
            notes = "Saves message.")
    @PostMapping
    public Message saveMessage(Principal principal,
            @ApiParam(value = "The text to save.", required = true) @RequestParam(value = "text",  required = true) String text) {
        Set<String> tagNames = MessageUtils.extractTags(text);
        Message message = new Message();
        message.setText(text);
        message.setTagNames(tagNames);
        message.setUsername(principal.getName());
        message.setCreatedAt(LocalDateTime.now());
        tagService.addTags(tagNames);
        return messageRepository.save(message);
    }

    /**
     * Deletes message with certain id.
     *
     * @param principal principal
     * @param id id
     * @return response with the operation result
     */
    @ApiOperation(value = "Delete message", httpMethod = "DELETE", response = String.class,
            notes = "Deletes message with specified id.")
    @ApiResponses(value = { @ApiResponse(code = 404, message = MESSAGE_NOT_FOUND),
            @ApiResponse(code = 406, message = DELETING_FORBIDDEN) })
    @DeleteMapping
    public ResponseEntity deleteMessage(Principal principal,
            @ApiParam(value = "Message id to delete.", required = true) @RequestParam(value = "id",  required = true) String id) {
        Message message = messageRepository.findById(id);

        if (message == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(),
                    MESSAGE_NOT_FOUND));
        }

        if (!message.getUsername().equals(principal.getName())) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ApiError(LocalDateTime.now(), HttpStatus.NOT_ACCEPTABLE.value(),
                    DELETING_FORBIDDEN));
        }

        messageRepository.delete(id);

        return ResponseEntity.ok().body(null);
    }

}
