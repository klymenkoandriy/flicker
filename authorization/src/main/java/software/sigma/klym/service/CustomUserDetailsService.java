package software.sigma.klym.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import software.sigma.klym.model.UserAuth;

/**
 * @author Andriy Klymenko
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private static final String USER_ROLE = "USER";

    @Autowired
    private UserFeignService userFeignService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAuth user = userFeignService.getByUsername(username);
        return toUserDetails(user);
    }

    private UserDetails toUserDetails(UserAuth user) {
        return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(USER_ROLE).build();
    }
}
