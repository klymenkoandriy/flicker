package software.sigma.klym.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.sigma.klym.domain.MessageRepository;
import software.sigma.klym.model.Message;

import java.util.List;

/**
 * @author Andriy Klymenko
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository repository;

    @Override
    public List<Message> findAll() {
        return repository.findAll();
    }

    @Override
    public Message save(Message message) {
        return repository.save(message);
    }

    @Override
    public List<Message> findByUsername(String username) {
        return repository.findByUsername(username);
    }
}
