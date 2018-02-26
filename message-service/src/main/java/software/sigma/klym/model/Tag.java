package software.sigma.klym.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

/**
 * Tag entity.
 *
 * @author Andriy Klymenko
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Tag {

    @Id
    private String id;

    private String name;

    /**
     * The number of messages containing this tag.
     */
    private int used;
}
