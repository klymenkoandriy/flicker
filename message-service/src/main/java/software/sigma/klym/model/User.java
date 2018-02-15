package software.sigma.klym.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * @author Andriy Klymenko
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class User {
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Date birthDate;

    /**
     * Constructor for creation.
     *
     * @param id id
     * @param username username
     * @param firstName firstName
     * @param lastName lastName
     * @param email email
     * @param password password
     * @param birthDate birthDate
     */
    public User(String id, String username, String firstName, String lastName, String email, String password, Date birthDate) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
    }
}
