package software.sigma.klym.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * MessageDTO entity, that contains message data and public user data.
 *
 * @author Andriy Klymenko
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class MessageDTO {

    private String id;
    private String text;
    private String username;
    private String firstName;
    private String lastName;
    private LocalDateTime createdAt;

}
