package software.sigma.klym.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import software.sigma.klym.domain.UserRepository;
import software.sigma.klym.model.User;

import java.security.Principal;
import java.time.LocalDateTime;

/**
 * User RESTful controller that is used with authentication.
 *
 * @author Andriy Klymenko
 */
@Api(value = "User data operations", description = "RESTful API to interact with users data.")
@RestController
@RequestMapping(value = "/api/v1/users/my")
public class UserDefendedController {

    private static final String INFO_UPDATING_ANOTHER_FORBIDDEN = "Updating another user's data is forbidden.";

    private static final String INFO_UPDATING_NAME_FORBIDDEN = "Updating username is forbidden.";

    private static final String INFO_USER_NOT_FOUND = "User not found.";

    @Autowired
    private UserRepository userRepository;

    /**
     * Returns User entity accotding to the current user.
     *
     * @param principal principal
     * @return user data
     */
    @ApiOperation(value = "Get current user", httpMethod = "GET", response = User.class,
            notes = "Returns User entity for current user.")
    @GetMapping
    public User getCurrentUser(Principal principal) {
        return  userRepository.findByUsername(principal.getName());
    }

    /**
     * Updates user data.
     *
     * @param principal principal
     * @param newUserData user data
     * @return saved user data
     */
    @ApiOperation(value = "Update current user", httpMethod = "PUT", response = User.class,
            notes = "Updates User data.")
    @ApiResponses(value = { @ApiResponse(code = 404, message = INFO_USER_NOT_FOUND),
            @ApiResponse(code = 400, message = INFO_UPDATING_ANOTHER_FORBIDDEN),
            @ApiResponse(code = 400, message = INFO_UPDATING_NAME_FORBIDDEN)
    })

    @PutMapping
    public ResponseEntity updateUser(Principal principal, @RequestBody User newUserData) {

        User user = userRepository.findById(newUserData.getId());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(),
                    INFO_USER_NOT_FOUND));
        }

        if (!user.getUsername().equals(principal.getName())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(),
                    INFO_UPDATING_ANOTHER_FORBIDDEN));
        }

        if (!newUserData.getUsername().equals(principal.getName())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(),
                    INFO_UPDATING_NAME_FORBIDDEN));
        }

        return ResponseEntity.ok().body(userRepository.save(newUserData));
    }
}