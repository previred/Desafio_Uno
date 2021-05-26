package cl.previred.infrastructure.adapters.gdd.client;

import cl.previred.presentation.api.model.error.ApiError;
import cl.previred.presentation.api.model.error.ApiErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;

import java.nio.charset.Charset;
import static org.assertj.core.api.Assertions.assertThat;

class GDDErrorMessageBuilderTest {

    private GDDErrorMessageBuilder gddErrorMessageBuilder;

    @BeforeEach
    void setUp() {
        ObjectMapper objectMapper = new ObjectMapper();
        gddErrorMessageBuilder = new GDDErrorMessageBuilder(objectMapper);
    }

    @Test
    void shouldBuildErrorMessageFromHttpStatusCode4xxException() {

        String errorResponse = "{" +
                "  \"date\": \"2018-11-21 13:21:52\"," +
                "  \"message\": \"Error desconocido\"," +
                "  \"ok\": false" +
                "}";

        HttpStatusCodeException e = new HttpClientErrorException(HttpStatus.BAD_REQUEST,
                "Bad Request", errorResponse.getBytes(), Charset.defaultCharset());

        ApiErrorResponse apiErrorResponse = gddErrorMessageBuilder.buildErrorMessage(e, e.getStatusCode(), "somePointer");

        assertErrorResponse(apiErrorResponse, "400", "Error desconocido");
    }

    @Test
    void shouldBuildErrorMessageFromHttpStatusCode5xxException() {

        String errorResponse = "{" +
                "  \"date\": \"2018-11-21 13:21:52\"," +
                "  \"message\": \"Error desconocido\"," +
                "  \"ok\": false" +
                "}";

        HttpStatusCodeException e = new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR,
                "Internal Server Error", errorResponse.getBytes(), Charset.defaultCharset());

        ApiErrorResponse apiErrorResponse = gddErrorMessageBuilder.buildErrorMessage(e, e.getStatusCode(), "somePointer");

        assertErrorResponse(apiErrorResponse, "500", "Error desconocido");
    }

    @Test
    void shouldBuildErrorMessageFromHttpStatusCodeExceptionWhenUnknownStructure() {

        String errorResponse = "{\"status\": \"Error\", \"message\": \"Resource not found\"}";

        HttpStatusCodeException e = new HttpClientErrorException(HttpStatus.NOT_FOUND,
                "Internal Server Error", errorResponse.getBytes(), Charset.defaultCharset());

        ApiErrorResponse apiErrorResponse = gddErrorMessageBuilder.buildErrorMessage(e, e.getStatusCode(), "somePointer");

        assertErrorResponse(apiErrorResponse, "404", errorResponse);
    }

    @Test
    void shouldBuildErrorMessageFromHttpStatusCodeExceptionWhenNoJson() {

        String errorResponse = "Oops!";

        HttpStatusCodeException e = new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR,
                "Internal Server Error", errorResponse.getBytes(), Charset.defaultCharset());

        ApiErrorResponse apiErrorResponse = gddErrorMessageBuilder.buildErrorMessage(e, e.getStatusCode(), "somePointer");

        assertErrorResponse(apiErrorResponse, "500", errorResponse);
    }


    @Test
    void shouldBuildErrorMessageFromRestClientException() {

        RestClientException e = new RestClientException("low level I/O error");

        ApiErrorResponse apiErrorResponse = gddErrorMessageBuilder.buildErrorMessage(e, "somePointer");

        assertErrorResponse(apiErrorResponse, "500", "low level I/O error");
    }

    private void assertErrorResponse(ApiErrorResponse apiErrorResponse, String status, String detail) {

        assertThat(apiErrorResponse.getErrors()).hasSize(1);

        ApiError apiError = apiErrorResponse.getErrors().get(0);
        assertThat(apiError.getStatus()).isEqualTo(status);
        assertThat(apiError.getTitle()).isEqualTo("somePointer inválido");
        assertThat(apiError.getDetail()).isEqualTo(detail);
        assertThat(apiError.getSource().getPointer()).isEqualTo("somePointer");
    }
}