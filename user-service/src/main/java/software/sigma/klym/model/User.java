package software.sigma.klym.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @author Andriy Klymenko
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class User {

    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private LocalDate birthDate;

}
