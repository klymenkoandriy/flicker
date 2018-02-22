package software.sigma.klym.domain;

import org.springframework.stereotype.Repository;
import software.sigma.klym.model.Message;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Andriy Klymenko
 */
@Repository
public class MessageRepositoryImpl implements MessageRepository {

    final List<Message> messages = Collections.synchronizedList(new LinkedList<>());

    @Override
    public List<Message> findAll() {
        return messages;
    }

    @Override
    public List<Message> findByUsername(String username) {
        return  messages.stream().filter(e -> e.getUsername().equals(username)).collect(Collectors.toList());
    }

    @Override
    public Message save(Message message) {
        messages.add(0, message);
        return message;
    }
}
