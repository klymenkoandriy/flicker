package software.sigma.klym.domain;

import org.springframework.stereotype.Repository;
import software.sigma.klym.model.Message;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Andriy Klymenko
 */
@Repository
public class MessageRepositoryImpl implements MessageRepository {

    final List<Message> messages = Collections.synchronizedList(new LinkedList<>());

    @Override
    public List<Message> findAll(int pageNumber, int pageSize) {
        return messages;
    }

    @Override
    public List<Message> findByTag(String tagName, int start, int limit) {
        List<Message> result = messages.stream().filter(e -> e.getTags().contains(tagName)).collect(Collectors.toList());
        return result;
    }

    @Override
    public Message save(Message message) {
        message.setId(UUID.randomUUID().toString());
        messages.add(0, message);
        return message;
    }

    @Override
    public Message findById(String id) {
        for (Message message : messages) {
            if (message.getId().equals(id)) {
                return message;
            }
        }
        return null;
    }

    @Override
    public void delete(String id) {
        Message message = findById(id);
        messages.remove(message);
    }
}
