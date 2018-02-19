package software.sigma.klym;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED;

/**
 * @author Andriy Klymenko
 */
public class PreFilter extends ZuulFilter {

    private static final String FILTERED_SERVICE = "user-service";

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
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        final HttpServletResponse response = ctx.getResponse();

        String url = request.getRequestURL().toString();
        if (url.contains(FILTERED_SERVICE) && request.getMethod().equals(RequestMethod.GET.toString())) {
            try {
                response.sendError(METHOD_NOT_ALLOWED.value(), METHOD_NOT_ALLOWED.getReasonPhrase());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
