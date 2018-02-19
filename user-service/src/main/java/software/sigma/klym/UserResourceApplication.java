package software.sigma.klym;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * The main class to start the application.
 */
@SuppressWarnings({"HideUtilityClassConstructor", "JavadocMethod"})
@SpringBootApplication
@EnableEurekaClient
public class UserResourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserResourceApplication.class, args);
    }
}
