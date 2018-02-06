package software.sigma.flicker.user.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * User entity.
 *
 * @author Andriy Klymenko
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class User {

    /**
     * The unique technical key for the entity.
     */
    @Id
    private String id;

    /**
     * The user name for login.
     */
    private String username;

    /**
     * The first name.
     */
    private String firstName;

    /**
     * The first name..
     */
    private String lastName;

    /**
     * The email address.
     */
    private String email;

    /**
     * The password.
     */
    private String password;

    /**
     * The birthday date.
     */
    private Date birthDate;

    /**
     * Constructor for creation.
     *
     * @param username username
     * @param firstName firstName
     * @param lastName lastName
     * @param email email
     * @param password password
     * @param birthDate birthDate
     */
    public User(String username, String firstName, String lastName, String email, String password, Date birthDate) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
    }
}
