package software.sigma.klym.userservice.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
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

    @Id
    private String id;

    @Size(min = 3, max = 64)
    private String username;

    @Size(min = 3, max = 64)
    private String firstName;

    @Size(min = 3, max = 64)
    private String lastName;

    @Email
    private String email;

    @Size(min = 3, max = 64)
    private String password;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthDate;

}
