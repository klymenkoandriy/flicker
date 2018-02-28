package software.sigma.klym.messageservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * Base Message entity.
 *
 * @author Andriy Klymenko
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseMessage {

    @Id
    private String id;

    @Size(min = 3, max = 1024)
    private String text;

    @Size(min = 3, max = 64)
    private String username;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createdAt;

}
