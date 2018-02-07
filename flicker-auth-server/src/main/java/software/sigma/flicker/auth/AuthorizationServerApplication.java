package software.sigma.flicker.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * Main class for Authorization Server.
 *
 * @author Andriy Klymenko
 */
@SpringBootApplication
@EnableResourceServer
public class AuthorizationServerApplication  extends SpringBootServletInitializer {

    /**
     * The main method to start application.
     *
     * @param args arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(AuthorizationServerApplication.class, args);
    }
}
