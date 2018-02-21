package software.sigma.klym.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import software.sigma.klym.model.UserAuth;

/**
 * @author Andriy Klymenko
 */
@FeignClient(name = "user-service")
public interface UserFeignService {

    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/users")
    UserAuth getByUsername(@RequestParam(value = "username") String username);

}
