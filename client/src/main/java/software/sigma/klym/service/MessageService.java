package software.sigma.klym.service;

import software.sigma.klym.model.Message;

import java.util.List;

/**
 * @author Andriy Klymenko
 */
public interface MessageService {

    List<Message> findAll();
    Message save(Message message);
    List<Message> findByUsername(String username);
}
