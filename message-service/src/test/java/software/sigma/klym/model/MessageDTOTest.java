package software.sigma.klym.model;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import software.sigma.klym.messageservice.model.MessageDTO;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Andriy Klymenko
 */
public class MessageDTOTest {

    private static final String FIELDS_SIZE_ERR_MESSAGE = "size must be between 3 and 64";

    private static final String TEXT_SIZE_ERR_MESSAGE = "size must be between 3 and 1024";

    private MessageDTO message;

    private static ValidatorFactory validatorFactory;

    private static Validator validator;

    @BeforeClass
    public static void createValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterClass
    public static void close() {
        validatorFactory.close();
    }

    @Before
    public void init() {
        message = new MessageDTO("1", "text", "userName", "firstName", "lastName",
                LocalDateTime.now());
    }

    @Test
    public void shouldHaveNoViolations() {
        Set<ConstraintViolation<MessageDTO>> violations = validator.validate(message);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void shouldDetectInvalidUsername() {
        message.setUsername("");
        checkSizeViolation(FIELDS_SIZE_ERR_MESSAGE);
    }

    @Test
    public void shouldDetectInvalidFirstName() {
        message.setFirstName("");
        checkSizeViolation(FIELDS_SIZE_ERR_MESSAGE);
    }

    @Test
    public void shouldDetectInvalidLastName() {
        message.setLastName("");
        checkSizeViolation(FIELDS_SIZE_ERR_MESSAGE);
    }

    @Test
    public void shouldDetectInvalidText() {
        message.setText("");
        checkSizeViolation(TEXT_SIZE_ERR_MESSAGE);
    }

    private void checkSizeViolation(String errMessage) {
        Set<ConstraintViolation<MessageDTO>> violations = validator.validate(message);
        assertEquals(1, violations.size());
        assertEquals(errMessage, violations.iterator().next().getMessage());
    }
}
