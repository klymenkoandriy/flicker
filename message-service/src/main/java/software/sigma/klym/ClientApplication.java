package software.sigma.klym;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * Message service.
 */
@SpringBootApplication
@ComponentScan
@EnableResourceServer
@EnableEurekaClient
@EnableFeignClients
public class ClientApplication extends ResourceServerConfigurerAdapter {

    /**
     * Main method to start application.
     * @param args arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

}
