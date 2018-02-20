package software.sigma.klym;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED;

/**
 * @author Andriy Klymenko
 */
@Log
@Configuration
@PropertySource("classpath:application.yml")
public class PreFilter extends ZuulFilter {

    @Value("${application.filter.prefix}")
    private String prefix;

    @Value("${application.filter.method}")
    private String method;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();

        if (request.getRequestURL().toString().contains(prefix) && request.getMethod().equals(method)) {
            try {
                context.getResponse().sendError(METHOD_NOT_ALLOWED.value(), METHOD_NOT_ALLOWED.getReasonPhrase());
            }
            catch (IOException e) {
                log.warning("Error updating response. " + e);
            }
        }
        return null;
    }

}
