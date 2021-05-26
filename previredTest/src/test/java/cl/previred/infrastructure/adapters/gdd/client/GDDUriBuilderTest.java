package cl.previred.infrastructure.adapters.gdd.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

class GDDUriBuilderTest {

    private GDDUriBuilder gddUriBuilder;

    @BeforeEach
    void setUp() {
        gddUriBuilder = new GDDUriBuilder("http://host/context");
    }

    @Test
    void shouldBuildUri() {

        URI uri = gddUriBuilder.build();
        assertThat(uri).hasToString("http://host/context");
    }
}