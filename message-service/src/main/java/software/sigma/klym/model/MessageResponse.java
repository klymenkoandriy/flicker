package software.sigma.klym.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author Andriy Klymenko
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class MessageResponse {

    private List<MessageDTO> content;
    private boolean first;
    private boolean last;
    private int numberOfElements;
    private int totalPages;
    private long totalElements;
    private int size;
    private int number;

}
