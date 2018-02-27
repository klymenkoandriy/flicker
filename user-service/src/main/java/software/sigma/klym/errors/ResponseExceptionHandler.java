package software.sigma.klym.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;

/**
 * An exception handler for intercepting exceptions thrown in controllers.
 *
 * @author Andriy Klymenko
 */
@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String MESSAGE_VALIDATION_ERROR = "Not valid parameters";

    /**
     * Interceptor for {@link ConstraintViolationException}.
     *
     * @param ex exception
     * @param request request
     * @return response entity
     */
    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<ApiError> handleValidationException(RuntimeException ex, WebRequest request) {
        String uri = ((ServletWebRequest)request).getRequest().getRequestURI();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(), MESSAGE_VALIDATION_ERROR, uri));
    }

    /**
     * Interceptor for {@link RequestException}.
     *
     * @param ex exception
     * @param request request
     * @return response entity
     */
    @ExceptionHandler(RequestException.class)
    protected ResponseEntity<ApiError> handleRequestException(RequestException ex, WebRequest request) {
        String uri = ((ServletWebRequest)request).getRequest().getRequestURI();
        return ResponseEntity.status(ex.getStatus()).body(new ApiError(LocalDateTime.now(), ex.getStatus().value(),
                ex.getStatus().getReasonPhrase(), ex.getMessage(), uri));
    }
}
