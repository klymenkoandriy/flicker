package software.sigma.klym.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

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
@EqualsAndHashCode
public class Message {

    @Id
    private String id;
    private String text;
    private String username;
    private LocalDateTime createdAt;
    private Set<String> tagNames = new HashSet<>();

}
