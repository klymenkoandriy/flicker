package software.sigma.klym.domain;

import software.sigma.klym.model.Message;

import java.util.List;

/**
 * Message repository.
 *
 * @author Andriy Klymenko
 */
public interface MessageRepository {

    /**
     * Returns messages with pagination.
     *
     * @param pageNumber page number
     * @param pageSize page size
     * @return messages
     */
    List<Message> findAll(int pageNumber, int pageSize);

    /**
     * Returns messages that contain specified tag with pagination.
     * @param tagName tag name
     * @param pageNumber page number
     * @param pageSize page size
     * @return messages
     */
    List<Message> findByTag(String tagName, int pageNumber, int pageSize);

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
