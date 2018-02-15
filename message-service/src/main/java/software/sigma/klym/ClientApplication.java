package software.sigma.klym;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.client.RestTemplate;

/**
 * Message service.
 */
@SpringBootApplication
@ComponentScan
//@EnableOAuth2Sso
@EnableWebSecurity
@EnableResourceServer
@EnableEurekaClient
@RibbonClient(name = "message-service")
public class ClientApplication extends ResourceServerConfigurerAdapter {

    /**
     * Main method to start application.
     * @param args arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

//    /**
//     * Produces OAuth2 rest template bean.
//     *
//     * @param oauth2ClientContext context
//     * @param details details
//     * @return OAuth2 rest template
//     */
//    @Bean
//    @LoadBalanced
//    OAuth2RestTemplate oauth2RestTemplate(OAuth2ClientContext oauth2ClientContext, OAuth2ProtectedResourceDetails details) {
//        return new OAuth2RestTemplate(details, oauth2ClientContext);
//    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/**").access("#oauth2.hasScope('read')")
                .antMatchers(HttpMethod.POST, "/api/**").access("#oauth2.hasScope('write')");
    }
}
