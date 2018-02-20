package software.sigma.klym;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED;

/**
 * @author Andriy Klymenko
 */
public class PreFilter extends ZuulFilter {

    private static final String FILTERED_PREFIX = "/api/users";

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

        if (request.getRequestURL().toString().contains(FILTERED_PREFIX) && request.getMethod().equals(RequestMethod.GET.toString())) {
            try {
                context.getResponse().sendError(METHOD_NOT_ALLOWED.value(), METHOD_NOT_ALLOWED.getReasonPhrase());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
