package software.sigma.klym;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@EnableAuthorizationServer
@EnableEurekaClient
@RestController
@SessionAttributes("authorizationRequest")
public class AuthorizationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorizationApplication.class, args);
    }

    @Configuration
    static class MvcConfig extends WebMvcConfigurerAdapter {
        @Override
        public void addViewControllers(ViewControllerRegistry registry) {
            registry.addViewController("login").setViewName("login");
            registry.addViewController("/oauth/confirm_access").setViewName("authorize");
            registry.addViewController("/").setViewName("index");
        }
    }

    @Configuration
    static class LoginConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .csrf().disable()
                    .formLogin().loginPage("/login").permitAll()
                    .and()
                    .requestMatchers()
//                    .antMatchers("/", "/login", "/oauth/authorize", "/oauth/confirm_access", "/api/messages", "/api/users")
                    .antMatchers("/oauth/authorize", "/oauth/confirm_access", "/message-service/api/messages", "/user-service/api/users")
                    .and()
                    .authorizeRequests()
                    .anyRequest().authenticated();

        }
    }
}
