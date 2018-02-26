package software.sigma.klym.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import software.sigma.klym.model.Message;

/**
 * Message repository.
 *
 * @author Andriy Klymenko
 */
public interface MessageRepository extends MongoRepository<Message, String> {

    /**
     * Returns Page object with list of messages according to pagination parameter.
     *
     * @param pageRequest page request
     * @return Page object with list of messages
     */
    Page<Message> findAll(Pageable pageRequest);

    /**
     * Returns Page object with list of messages that contain specified tagName according to pagination parameter.
     *
     * @param pageRequest page request
     * @param tagName tag name
     * @return Page object with list of messages
     */
    Page<Message> findByTagNamesContaining(Pageable pageRequest, String tagName);

    /**
     * Saves message.
     *
     * @param message message
     * @return saved message
     */
    Message save(Message message);

    /**
     * Returns message with specified id.
     *
     * @param id id
     * @return message
     */
    Message findById(String id);

    /**
     * Deletes message with specified id.
     *
     * @param id id
     */
    void delete(String id);
}
