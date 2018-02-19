package software.sigma.klym;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * The main class to start the application.
 */
@SuppressWarnings({"JavadocMethod", "HideUtilityClassConstructor"})
@SpringBootApplication
@EnableFeignClients
@EnableAuthorizationServer
@EnableEurekaClient
@RestController
@SessionAttributes("authorizationRequest")
public class AuthorizationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorizationApplication.class, args);
    }

}
