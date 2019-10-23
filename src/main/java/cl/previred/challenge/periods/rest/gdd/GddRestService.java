package cl.previred.challenge.periods.rest.gdd;

import cl.previred.challenge.periods.rest.RestService;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

/**
 * @author Luis Riveros - luis.riveros_ex@scotiabank.cl
 * @version 1.0.0 - 22-10-2019
 * @since 1.0.0 - 22-10-2019
 */
@Service
@ConfigurationProperties(prefix = "rest.api.gdd")
public class GddRestService extends RestService<GddResponse> {

    @Override
    protected Class<GddResponse> type() {
        return GddResponse.class;
    }
}
