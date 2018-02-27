package software.sigma.klym.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * MessageDTO entity, that contains message data and public user data.
 *
 * @author Andriy Klymenko
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MessageDTO extends BaseMessage {

    @Size(min = 3, max = 64)
    private String firstName;

    @Size(min = 3, max = 64)
    private String lastName;

    /**
     *  Constructor for creation.
     *
     * @param id id
     * @param text text
     * @param username username
     * @param firstName first name
     * @param lastName last name
     * @param createdAt creation date and time
     */
    public MessageDTO(String id, String text, String username, String firstName, String lastName, LocalDateTime createdAt) {
        super(id, text, username, createdAt);
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
