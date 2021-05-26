package cl.previred.infrastructure.adapters.gdd.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;

import cl.previred.infrastructure.adapters.exception.SourceSystemClientErrorException;
import cl.previred.infrastructure.adapters.exception.SourceSystemServerErrorException;
import cl.previred.presentation.api.model.error.ApiErrorResponse;

/**
 * @author wmunoz
 */
@Slf4j
@Component
public class GDDRestErrorHandler {

    private final String system;
    private final GDDErrorMessageBuilder gddErrorMessageBuilder;

    public GDDRestErrorHandler(GDDErrorMessageBuilder gddErrorMessageBuilder) {
    this.system = "GDD";
    this.gddErrorMessageBuilder = gddErrorMessageBuilder;
}

public void handleHttpClientErrorException(HttpClientErrorException e, String sourceUrl) {
    log.error("action=handleHttpClientErrorException, sourceUrl={}, exception={}",
            sourceUrl, e.getMessage());
    HttpStatus httpStatus = translateStatusCode(e);
    ApiErrorResponse apiErrorResponse = gddErrorMessageBuilder.buildErrorMessage(e, httpStatus, system);
    throw new SourceSystemClientErrorException(e, system, apiErrorResponse, httpStatus);
}

public void handleHttpServerErrorException(HttpServerErrorException e, String sourceUrl) {
    log.error("action=handleHttpServerErrorException, sourceUrl={}, exception={}", sourceUrl, e.getMessage());
    ApiErrorResponse apiErrorResponse = gddErrorMessageBuilder.buildErrorMessage(e, e.getStatusCode(), system);
    throw new SourceSystemServerErrorException(e, system, apiErrorResponse, e.getStatusCode());
}

public void handleRestClientException(RestClientException e) {
    log.error("action=handleHttpClientErrorException, exception={}", e.getMessage());
    ApiErrorResponse apiErrorResponse = gddErrorMessageBuilder.buildErrorMessage(e, system);
    throw new SourceSystemServerErrorException(e, system, apiErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
}

private HttpStatus translateStatusCode(HttpClientErrorException e) {
    return (isResourceNotFound(e.getStatusCode(), e.getResponseBodyAsString()))
            ? HttpStatus.NOT_FOUND : e.getStatusCode();
}

private boolean isResourceNotFound(HttpStatus httpStatus, String rawResponse) {
    return httpStatus.equals(HttpStatus.PRECONDITION_FAILED)
            && rawResponse.contains("recurso no existe");
}

}