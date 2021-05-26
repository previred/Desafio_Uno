package cl.previred.infrastructure.adapters.gdd.client;

import cl.previred.infrastructure.adapters.gdd.model.Periodo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.validation.Validation;
import javax.validation.Validator;
import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class GDDProxyTest {

    @Mock
    private RestTemplate restTemplate;
    @Mock
    private GDDRestErrorHandler gddRestErrorHandler;

    private GDDProxy gddProxy;

    @BeforeEach
    void setUp() {
        Validator beanValidator = Validation.buildDefaultValidatorFactory().getValidator();
        gddProxy = new GDDProxy(restTemplate, beanValidator, gddRestErrorHandler);
    }

    @Test
    void shouldCallPostEndpoint() {

        URI uri = URI.create("http://mock");
        HttpHeaders headers = HttpHeaders.EMPTY;

        Periodo periodo = Periodo.builder()
                .build();

        ArgumentCaptor<HttpEntity<?>> requestEntityArgumentCaptor = ArgumentCaptor.forClass(HttpEntity.class);

        given(restTemplate.exchange(eq(uri), eq(HttpMethod.GET), requestEntityArgumentCaptor.capture(), eq(Periodo.class)))
                .willReturn(ResponseEntity.ok(periodo));

        Periodo responseBody = gddProxy.get(uri, headers);

        HttpEntity<?> requestEntity = requestEntityArgumentCaptor.getValue();
        assertThat(requestEntity.getHeaders()).isEqualTo(headers);

        assertThat(responseBody).isEqualTo(periodo);
    }


    @Test
    void shouldHandle400() {
        testClientException(HttpStatus.BAD_REQUEST, "some 400 message");
    }

    @Test
    void shouldHandle401() {
        testClientException(HttpStatus.UNAUTHORIZED, "some 401 message");
    }

    @Test
    void shouldHandle403() {
        testClientException(HttpStatus.FORBIDDEN, "some 403 message");
    }

    @Test
    void shouldHandle404() {
        testClientException(HttpStatus.NOT_FOUND, "some 404 message");
    }

    @Test
    void shouldHandle412() {
        testClientException(HttpStatus.PRECONDITION_FAILED, "some 412 message");
    }

    @Test
    void shouldHandle500() {
        testServerException(HttpStatus.INTERNAL_SERVER_ERROR, "internal server error");
    }

    @Test
    void shouldHandle504() {
        testServerException(HttpStatus.GATEWAY_TIMEOUT, "gateway timeout");
    }

    @Test
    void shouldHandleRestClientException() {
        URI uri = URI.create("http://mock");
        RestClientException exception = new RestClientException("some rest error");
        testCallWhen(uri, exception);
        then(gddRestErrorHandler).should().handleRestClientException(exception);
    }

    @Test
    void shouldHandleException() {

        URI uri = URI.create("http://mock");

        Throwable runtimeException = new RuntimeException("runtime error");

        given(restTemplate.exchange(eq(uri), eq(HttpMethod.GET), any(), eq(Periodo.class)))
                .willThrow(runtimeException);

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> gddProxy.get(uri, HttpHeaders.EMPTY));

        assertThat(exception).hasMessage("runtime error");
        then(gddRestErrorHandler).shouldHaveNoInteractions();
    }

    private void testClientException(HttpStatus httpStatus, String message) {
        URI uri = URI.create("http://mock");
        HttpClientErrorException exception = new HttpClientErrorException(httpStatus, message);
        testCallWhen(uri, exception);
        then(gddRestErrorHandler).should().handleHttpClientErrorException(exception, uri.toString());
    }

    private void testServerException(HttpStatus httpStatus, String message) {
        URI uri = URI.create("http://mock");
        HttpServerErrorException exception = new HttpServerErrorException(httpStatus, message);
        testCallWhen(uri, exception);
        then(gddRestErrorHandler).should().handleHttpServerErrorException(exception, uri.toString());
    }

    private void testCallWhen(URI uri, Exception exception) {
        given(restTemplate.exchange(eq(uri), eq(HttpMethod.GET), any(), eq(Periodo.class)))
                .willThrow(exception);
        gddProxy.get(uri, HttpHeaders.EMPTY);
    }
}