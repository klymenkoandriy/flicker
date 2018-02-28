package software.sigma.klym.messageservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * Class to create validation beans.
 *
 * @author Andriy Klymenko
 */
@Configuration
public class MongoValidator {

    /**
     * Creates a new event listener.
     *
     * @return listener
     */
    @Bean
    public ValidatingMongoEventListener validatingMongoEventListener() {
        return new ValidatingMongoEventListener(validator());
    }

    /**
     * Creates a new factory bean.
     *
     * @return factory bean
     */
    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }
}
