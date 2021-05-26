package cl.previred.infrastructure.adapters.gdd.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;

/**
 * @author wmunoz
 */
@Slf4j
@Component
public class GDDUriBuilder {

    private final String getGDDUrl;

    public GDDUriBuilder(@Value("${gdd.service.path-url-random:}") String getGDDUrl) {
        this.getGDDUrl = getGDDUrl;
    }
    
    public URI build() {
        log.info("action=GetGDDUrL, url={}", getGDDUrl);
        return URI.create(getGDDUrl);
    }
}
