package software.sigma.flicker.auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author Andriy Klymenko
 */
@RestController
public class UserController {

    /**
     * Registers user.
     *
     * @param principal principal
     * @return principal
     */
    @RequestMapping("/user/me")
    public Principal user(Principal principal) {
        return principal;
    }
}
