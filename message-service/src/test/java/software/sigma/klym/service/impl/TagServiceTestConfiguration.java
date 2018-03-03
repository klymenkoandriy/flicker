package software.sigma.klym.service.impl;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import software.sigma.klym.messageservice.service.TagService;
import software.sigma.klym.messageservice.service.impl.TagServiceImpl;

/**
 * Configuration class for testing the {@link TagServiceTest}.
 *
 * @author Andriy Klymenko
 */
@TestConfiguration
public class TagServiceTestConfiguration {

    @Bean
    public TagService tafService() {
        return new TagServiceImpl();
    }
}
