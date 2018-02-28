package software.sigma.klym.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * The main class to start the application.
 */
@SuppressWarnings({"HideUtilityClassConstructor", "JavadocMethod"})
@SpringBootApplication
@EnableEurekaClient
@EnableResourceServer
@EnableAutoConfiguration
public class UserResourceApplication extends ResourceServerConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(UserResourceApplication.class, args);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/v1/users/my**").access("#oauth2.hasScope('read')")
                .antMatchers(HttpMethod.PUT, "/api/v1/users/my**").access("#oauth2.hasScope('write')");
    }
}
