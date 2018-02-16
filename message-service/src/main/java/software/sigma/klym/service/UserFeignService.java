package software.sigma.klym.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import software.sigma.klym.model.User;

import java.util.List;

/**
 * @author Andriy Klymenko
 */
@FeignClient(name = "user-service")
public interface UserFeignService {

    @RequestMapping(method = RequestMethod.GET, value = "api/users/get-by-username")
    User getByUsername(@RequestParam(value = "username") String name);

    @RequestMapping(method = RequestMethod.POST, value = "api/users")
    User saveUser(@RequestBody User user);

    @RequestMapping(method = RequestMethod.GET, value = "api/users")
    List<User> getAll();
}
