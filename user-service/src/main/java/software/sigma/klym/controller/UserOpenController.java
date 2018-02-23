package software.sigma.klym.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import software.sigma.klym.domain.UserRepository;
import software.sigma.klym.model.User;

import java.time.LocalDateTime;

/**
 * User RESTful controller that is used without authentication.
 *
 * @author Andriy Klymenko
 */
@Api(value = "User data operations", description = "RESTful API to interact with users data.")
@RestController
@RequestMapping(value = "/api/v1/users")
public class UserOpenController {

    public static final String INFO_REDUNDANT_ID = "Wrong parameter: redundant parameter 'id' in POST method.";
    public static final String INFO_ALREADY_EXISTS = "User with the specified name already exists.";

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
     * @param user user data
     * @return saved user data
     */
    @ApiOperation(value = "Save User", httpMethod = "POST", response = User.class,
            notes = "Saves User. To save new user - 'id' field must be null or empty.")
    @ApiResponses(value = { @ApiResponse(code = 400, message = INFO_REDUNDANT_ID),
            @ApiResponse(code = 422, message = INFO_ALREADY_EXISTS) })
    @PostMapping
    public ResponseEntity saveUser(@RequestBody User user) {

        if (StringUtils.isNotBlank(user.getId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(),
                    INFO_REDUNDANT_ID));
        }

        if (userRepository.findByUsername(user.getUsername()) != null) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ApiError(LocalDateTime.now(), HttpStatus.UNPROCESSABLE_ENTITY.value(),
                    INFO_ALREADY_EXISTS));
        }

        return ResponseEntity.ok().body(userRepository.save(user));
    }

}
