package software.sigma.klym.messageservice.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import software.sigma.klym.messageservice.model.Message;

/**
 * Message repository.
 *
 * @author Andriy Klymenko
 */
public interface MessageRepository extends MongoRepository<Message, String> {

    /**
     * Returns Page object with list of messages that contain specified tagName according to pagination parameter.
     *
     * @param pageRequest page request
     * @param tagName tag name
     * @return Page object with list of messages
     */
    Page<Message> findByTagNamesContaining(Pageable pageRequest, String tagName);
}
