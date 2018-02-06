package software.sigma.flicker.user;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Main start class.
 *
 * @author Andriy Klymenko
 */
@RestController
@EnableAutoConfiguration
@ComponentScan
@Log4j2
@SpringBootApplication
@EnableOAuth2Sso
public class Start {

    /**
     * The home REST endpoint.
     *
     * @return response
     */
    @RequestMapping("/")
    public String home() {
        return "Home";
    }

    /**
     * The main method to start application.
     *
     * @param args arguments
     * @throws Exception exception
     */
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Start.class, args);
        log.info("Application started");
    }
}
