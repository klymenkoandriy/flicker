package software.sigma.klym.domain;

import software.sigma.klym.model.Message;

import java.util.List;

/**
 * @author Andriy Klymenko
 */
public interface MessageRepository {

    List<Message> findAll();
    List<Message> findByUsername(String username);
    Message save(Message message);
}
