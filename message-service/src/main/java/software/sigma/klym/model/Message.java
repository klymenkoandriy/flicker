package software.sigma.klym.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Message entity.
 *
 * @author Andriy Klymenko
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Message extends BaseMessage {

    private Set<String> tagNames = new HashSet<>();

    /**
     *  Constructor for creation.
     *
     * @param text text
     * @param username username
     * @param createdAt creation date and time
     * @param tagNames set of tag names
     */
    public Message(String text, String username, LocalDateTime createdAt, Set<String> tagNames) {
        super(null, text, username, createdAt);
        this.tagNames = tagNames;
    }
}
