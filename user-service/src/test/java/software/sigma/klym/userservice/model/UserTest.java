package software.sigma.klym.userservice.model;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.time.Month;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test class for the {@link User} class.
 *
 * @author Andriy Klymenko
 */
public class UserTest {

    private static final String SIZE_ERR_MESSAGE = "size must be between 3 and 64";

    private static final String EMAIL_ERR_MESSAGE = "not a well-formed email address";

    private static ValidatorFactory validatorFactory;

    private static Validator validator;

    private User user;

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
        user = new User("1", "username", "firstName", "lastName", "email@com", "password",
                LocalDate.of(2000, Month.JANUARY, 20));
    }

    @Test
    public void shouldHaveNoViolations() {
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void shouldDetectInvalidUsername() {
        user.setUsername("");
        checkSizeViolation(SIZE_ERR_MESSAGE);
    }

    @Test
    public void shouldDetectInvalidFirstName() {
        user.setFirstName("");
        checkSizeViolation(SIZE_ERR_MESSAGE);
    }

    @Test
    public void shouldDetectInvalidLastName() {
        user.setLastName("");
        checkSizeViolation(SIZE_ERR_MESSAGE);
    }

    @Test
    public void shouldDetectInvalidPassword() {
        user.setPassword("");
        checkSizeViolation(SIZE_ERR_MESSAGE);
    }

    @Test
    public void shouldDetectInvalidEmail() {
        user.setEmail("email");
        checkSizeViolation(EMAIL_ERR_MESSAGE);
    }

    private void checkSizeViolation(String message) {
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
        assertEquals(message, violations.iterator().next().getMessage());
    }
}
