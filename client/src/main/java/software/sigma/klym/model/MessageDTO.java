package software.sigma.klym.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Andriy Klymenko
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class MessageDTO {

    private String id;
    private String text;
    private String username;
    private String firstName;
    private String lastName;

    /**
     * Constructor for creation.
     *
     * @param id id
     * @param text text
     * @param username username
     * @param firstName firstName
     * @param lastName lastName
     */
    public MessageDTO(String id, String text, String username, String firstName, String lastName) {
        this.id = id;
        this.text = text;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
