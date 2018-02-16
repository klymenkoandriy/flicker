package software.sigma.klym;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@SpringBootApplication
//@EnableResourceServer
@EnableEurekaClient
public class UserResourceApplication extends ResourceServerConfigurerAdapter {

//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/api/**").access("#oauth2.hasScope('read')")
//                .antMatchers(HttpMethod.POST, "/api/**").access("#oauth2.hasScope('write')");
//    }

    public static void main(String[] args) {
        SpringApplication.run(UserResourceApplication.class, args);
    }
}
