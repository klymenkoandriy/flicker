package software.sigma.klym.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import software.sigma.klym.domain.UserRepository;
import software.sigma.klym.model.User;

import java.security.Principal;

/**
 * @author Andriy Klymenko
 */
@Api(value = "User data operations", description = "RESTful API to interact with users data.")
@RestController
@RequestMapping(value = "/api/v1/users/my")
public class UserDefendedController {

    @Autowired
    private UserRepository userRepository;

    @ApiOperation(value = "Get current user", httpMethod = "GET", response = User.class,
            notes = "Returns User entity for current user.")
    @GetMapping
    public User getCurrentUser(Principal principal) {
        return  userRepository.findByUsername(principal.getName());
    }
}
