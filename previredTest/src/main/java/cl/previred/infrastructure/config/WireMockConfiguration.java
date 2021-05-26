package cl.previred.infrastructure.config;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.common.ClasspathFileSource;
import com.github.tomakehurst.wiremock.common.ConsoleNotifier;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import javax.annotation.PreDestroy;
import java.net.URL;
import java.util.Optional;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

/**
 * @author wmunoz
 */
@Configuration
public class WireMockConfiguration {

    private final WireMockServer wireMockServer;

    public WireMockConfiguration() {
        wireMockServer = new WireMockServer(options()
                .notifier(new ConsoleNotifier(true))
                .fileSource(getFileSource("stubs"))
                .port(8181)
                .extensions(new ResponseTemplateTransformer(true))
        );
    }

    private ClasspathFileSource getFileSource(String defaultPath) {
        URL stubsURL = Thread.currentThread().getContextClassLoader().getResource(defaultPath);
        String stubsPath = Optional.ofNullable(stubsURL)
                .map(Object::toString)
                .filter(url -> url.contains("BOOT-INF/classes"))
                .map(s -> "BOOT-INF/classes/stubs")
                .orElse(defaultPath);
        return new ClasspathFileSource(stubsPath);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void start() {
        wireMockServer.start();
    }

    @PreDestroy
    public void stop() {
        wireMockServer.stop();
        wireMockServer.shutdown();
    }

}
