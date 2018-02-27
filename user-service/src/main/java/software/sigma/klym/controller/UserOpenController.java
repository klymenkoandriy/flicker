package software.sigma.klym.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import software.sigma.klym.domain.UserRepository;
import software.sigma.klym.error.ApiError;
import software.sigma.klym.exception.RequestException;
import software.sigma.klym.model.User;

import java.time.LocalDate;

/**
 * User RESTful controller that is used without authentication.
 *
 * @author Andriy Klymenko
 */
@Api(value = "User data operations", description = "RESTful API to interact with users data.")
@RestController
@RequestMapping(value = "/api/v1/users")
public class UserOpenController {

    public static final String INFO_ALREADY_EXISTS = "User with the specified name already exists";

    @Autowired
    private UserRepository userRepository;

    /**
     * Returns the User entity with the specified username.
     *
     * @param username username
     * @return user data
     */
    @ApiOperation(value = "Get user", httpMethod = "GET", response = User.class, tags = {"User internal services"},
            notes = "Returns User with the specified username.")
    @GetMapping
    public User getByUsername(
            @ApiParam(value = "Username", required = true)
            @RequestParam(value = "username") String username) {
        return  userRepository.findByUsername(username);
    }

    /**
     * Saves User data.
     *
     * @param username username
     * @param firstName first name
     * @param lastName last name
     * @param email email
     * @param password password
     * @param birthDate date of birthday
     * @return saved user
     */
    @Validated
    @ApiOperation(value = "Save User", httpMethod = "POST", response = User.class, notes = "Saves new User.")
    @ApiResponses(value = { @ApiResponse(code = 422, message = INFO_ALREADY_EXISTS, response = ApiError.class) })
    @PostMapping
    public ResponseEntity saveUser(
            @ApiParam(value = "Username string used to login.", required = true)
                @RequestParam(value = "username",  required = true) String username,
            @ApiParam(value = "First name.", required = true)
                @RequestParam(value = "firstName",  required = true) String firstName,
            @ApiParam(value = "Last name.", required = true)
                @RequestParam(value = "lastName",  required = true) String lastName,
            @ApiParam(value = "Email address.", required = true)
                @RequestParam(value = "email",  required = true) String email,
            @ApiParam(value = "Password string used to login.", required = true)
                @RequestParam(value = "password",  required = true) String password,
            @ApiParam(value = "Date of birthday.", required = true)
                @RequestParam(value = "birthDate",  required = true) String birthDate) {

        if (userRepository.findByUsername(username) != null) {
            throw new RequestException(HttpStatus.UNPROCESSABLE_ENTITY, INFO_ALREADY_EXISTS);
        }

        User user = new User(null, username, firstName, lastName, email, password, LocalDate.parse(birthDate));
        return ResponseEntity.ok().body(userRepository.save(user));
    }

}
