package cl.previred.infrastructure.adapters.gdd.client;

import cl.previred.infrastructure.adapters.exception.SourceSystemClientErrorException;
import cl.previred.infrastructure.adapters.exception.SourceSystemServerErrorException;
import cl.previred.presentation.api.model.error.ApiErrorResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class GDDRestErrorHandlerTest {

    @Mock
    private GDDErrorMessageBuilder gddErrorMessageBuilder;

    @InjectMocks
    private GDDRestErrorHandler gddRestErrorHandler;

    @Test
    void shouldHandleHttpClientErrorExceptionWhen() {

        HttpClientErrorException httpClientErrorException = new HttpClientErrorException(HttpStatus.BAD_REQUEST, "some client error");

        ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
        given(gddErrorMessageBuilder.buildErrorMessage(httpClientErrorException, httpClientErrorException.getStatusCode(), "GDD")).willReturn(apiErrorResponse);

        SourceSystemClientErrorException exception = assertThrows(SourceSystemClientErrorException.class, () -> gddRestErrorHandler.handleHttpClientErrorException(httpClientErrorException, "source-uri"));

        assertThat(exception).hasMessage("400 some client error");
        assertThat(exception.getSystem()).isEqualTo("GDD");
        assertThat(exception.getSourceApiErrorResponse()).isEqualTo(apiErrorResponse);
        assertThat(exception.getSourceStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void shouldHandleHttpClientErrorException() {

        HttpClientErrorException httpClientErrorException =
                new HttpClientErrorException(HttpStatus.BAD_REQUEST, "some client error");

        ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
        given(gddErrorMessageBuilder.buildErrorMessage(httpClientErrorException, httpClientErrorException.getStatusCode(), "GDD"))
                .willReturn(apiErrorResponse);

        SourceSystemClientErrorException exception = assertThrows(SourceSystemClientErrorException.class,
                () -> gddRestErrorHandler.handleHttpClientErrorException(httpClientErrorException, "source-uri"));

        assertThat(exception).hasMessage("400 some client error");
        assertThat(exception.getSystem()).isEqualTo("GDD");
        assertThat(exception.getSourceApiErrorResponse()).isEqualTo(apiErrorResponse);
        assertThat(exception.getSourceStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void shouldHandleHttpClientErrorExceptionWhen412MeansResourceNotFound() {

        HttpClientErrorException httpClientErrorException =
                new HttpClientErrorException(HttpStatus.PRECONDITION_FAILED, "some status text",
                        "recurso no existe".getBytes(StandardCharsets.UTF_8),
                        Charset.defaultCharset());

        ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
        given(gddErrorMessageBuilder.buildErrorMessage(httpClientErrorException, HttpStatus.NOT_FOUND, "GDD"))
                .willReturn(apiErrorResponse);

 
        SourceSystemClientErrorException exception = assertThrows(SourceSystemClientErrorException.class,
                () -> gddRestErrorHandler.handleHttpClientErrorException(httpClientErrorException, "source-uri"));

        assertThat(exception).hasMessage("412 some status text");
        assertThat(exception.getSystem()).isEqualTo("GDD");
        assertThat(exception.getSourceApiErrorResponse()).isEqualTo(apiErrorResponse);
        assertThat(exception.getSourceStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void shouldHandleHttpClientErrorExceptionWhen412Means412() {

        HttpClientErrorException httpClientErrorException = new HttpClientErrorException(
        		        HttpStatus.PRECONDITION_FAILED, "some status text",
                        "cualquier cosa".getBytes(StandardCharsets.UTF_8),
                        Charset.defaultCharset());

        ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
        given(gddErrorMessageBuilder.buildErrorMessage(httpClientErrorException, httpClientErrorException.getStatusCode(), "GDD")).willReturn(apiErrorResponse);

        SourceSystemClientErrorException exception = assertThrows(SourceSystemClientErrorException.class,
                () -> gddRestErrorHandler.handleHttpClientErrorException(httpClientErrorException, "source-uri"));

        assertThat(exception).hasMessage("412 some status text");
        assertThat(exception.getSystem()).isEqualTo("GDD");
        assertThat(exception.getSourceApiErrorResponse()).isEqualTo(apiErrorResponse);
        assertThat(exception.getSourceStatusCode()).isEqualTo(HttpStatus.PRECONDITION_FAILED);
    }

    @Test
    void shouldHandleHttpServerErrorException() {

        HttpServerErrorException httpServerErrorException =
                new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "internal server error");

        ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
        given(gddErrorMessageBuilder.buildErrorMessage(httpServerErrorException, httpServerErrorException.getStatusCode(), "GDD"))
                .willReturn(apiErrorResponse);

        SourceSystemServerErrorException exception = assertThrows(SourceSystemServerErrorException.class,
                () -> gddRestErrorHandler.handleHttpServerErrorException(httpServerErrorException, "source-uri"));

        assertThat(exception).hasMessage("500 internal server error");
        assertThat(exception.getSystem()).isEqualTo("GDD");
        assertThat(exception.getSourceApiErrorResponse()).isEqualTo(apiErrorResponse);
        assertThat(exception.getSourceStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    void shouldHandleRestClientException() {

        RestClientException restClientException = new RestClientException("some rest error");

        ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
        given(gddErrorMessageBuilder.buildErrorMessage(restClientException, "GDD"))
                .willReturn(apiErrorResponse);

        SourceSystemServerErrorException exception = assertThrows(SourceSystemServerErrorException.class,
                () -> gddRestErrorHandler.handleRestClientException(restClientException));

        assertThat(exception).hasMessage("some rest error");
        assertThat(exception.getSystem()).isEqualTo("GDD");
        assertThat(exception.getSourceApiErrorResponse()).isEqualTo(apiErrorResponse);
        assertThat(exception.getSourceStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}