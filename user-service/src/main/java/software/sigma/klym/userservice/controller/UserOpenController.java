package software.sigma.klym.userservice.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import software.sigma.klym.error.ApiError;
import software.sigma.klym.exception.RequestException;
import software.sigma.klym.userservice.domain.UserRepository;
import software.sigma.klym.userservice.model.User;

/**
 * User RESTful controller that is used without authentication.
 *
 * @author Andriy Klymenko
 */
@Api(value = "User data operations", description = "RESTful API to interact with users data.")
@RestController
@RequestMapping(value = "/api/v1/users")
public class UserOpenController {

    public static final String INFO_ALREADY_EXISTS = "User with the specified username or id already exists";

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
    @RequestMapping(method = RequestMethod.GET)
    public User getByUsername(
            @ApiParam(value = "Username", required = true)
            @RequestParam(value = "username") String username) {
        return  userRepository.findByUsername(username);
    }

    /**
     * Saves User data.
     *
     * @param user user
     * @return saved user
     */
    @Validated
    @ApiOperation(value = "Save User", httpMethod = "POST", response = User.class, notes = "Saves new User.")
    @ApiResponses(value = { @ApiResponse(code = 422, message = INFO_ALREADY_EXISTS, response = ApiError.class) })
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity saveUser(@RequestBody User user) {

        if (user.getId() != null && user.getId().equals("")) {
            user.setId(null);
        }

        if (userRepository.findByUsername(user.getUsername()) != null
                || (user.getId() != null && userRepository.findOne(user.getId()) != null)) {
            throw new RequestException(HttpStatus.UNPROCESSABLE_ENTITY, INFO_ALREADY_EXISTS);
        }

        return ResponseEntity.ok().body(userRepository.save(user));
    }

}
