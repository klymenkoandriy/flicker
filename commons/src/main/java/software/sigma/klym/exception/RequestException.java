package software.sigma.klym.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * A runtime exception thrown by methods in REST controllers.
 *
 * @author Andriy Klymenko
 */
@Getter
public class RequestException extends RuntimeException {

    private HttpStatus status;

    /**
     * Constructor for creation.
     *
     * @param status status
     * @param message message
     */
    public RequestException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }
}
