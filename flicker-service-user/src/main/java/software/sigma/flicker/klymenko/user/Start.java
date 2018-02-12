package software.sigma.flicker.klymenko.user;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestContextListener;

/**
 * Main start class.
 *
 * @author Andriy Klymenko
 */
@Log4j2
@SpringBootApplication
public class Start extends SpringBootServletInitializer {

    /**
     * Creates and returns RequestContextListener.
     *
     * @return new listener
     */
    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
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
