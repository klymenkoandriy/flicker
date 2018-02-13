package software.sigma.klym;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;

/**
 * Message service.
 */
@ComponentScan
@SpringBootApplication
@EnableOAuth2Sso
@EnableEurekaClient
@RibbonClient(name = "message-service")
public class ClientApplication {

    /**
     * Main method to start application.
     * @param args arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

    /**
     * Produces OAuth2 rest template bean.
     *
     * @param oauth2ClientContext context
     * @param details details
     * @return OAuth2 rest template
     */
    @Bean
    @LoadBalanced
    OAuth2RestTemplate oauth2RestTemplate(OAuth2ClientContext oauth2ClientContext, OAuth2ProtectedResourceDetails details) {
        return new OAuth2RestTemplate(details, oauth2ClientContext);
    }

}
