package example.micronaut;

import io.micronaut.core.order.Ordered;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.annotation.RequestFilter;
import io.micronaut.http.annotation.ServerFilter;
import io.micronaut.http.filter.ServerFilterPhase;
import io.micronaut.http.util.HttpHeadersUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ServerFilter("/**")
public class LoggingFilter implements Ordered {
    private static final Logger LOG = LoggerFactory.getLogger(LoggingFilter.class);
    @RequestFilter
    public void filterRequest(HttpRequest<?> request) {
        LOG.trace("{} {}", request.getMethod(), request.getPath());
        HttpHeadersUtil.trace(LOG, request.getHeaders());
    }

    @Override
    public int getOrder() {
        return ServerFilterPhase.METRICS.before();
    }
}
