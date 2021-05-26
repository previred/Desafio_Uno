package cl.previred.infrastructure.adapters.gdd.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;

class GDDHeadersBuilderTest {

    private GDDHeadersBuilder gddHeadersBuilder;

    @BeforeEach
    void setUp() {
        gddHeadersBuilder = new GDDHeadersBuilder();
    }

    @Test
    void shouldGetHttpHeaders() {
        HttpHeaders httpHeaders = gddHeadersBuilder.getHttpHeaders();
        assertThat(httpHeaders.getContentType()).isEqualTo(MediaType.APPLICATION_JSON);
        assertThat(httpHeaders.getAccept()).containsOnly(MediaType.APPLICATION_JSON);
    
    }
}