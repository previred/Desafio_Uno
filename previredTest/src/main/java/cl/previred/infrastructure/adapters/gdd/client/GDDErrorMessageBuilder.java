package cl.previred.infrastructure.adapters.gdd.client;

import com.fasterxml.jackson.databind.ObjectMapper;

import cl.previred.infrastructure.adapters.gdd.model.GDDError;
import cl.previred.presentation.api.model.error.ApiError;
import cl.previred.presentation.api.model.error.ApiErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;

import java.io.IOException;

/**
 * @author wmunoz
 */
@Slf4j
@Component
public class GDDErrorMessageBuilder {

    private final ObjectMapper objectMapper;

    public GDDErrorMessageBuilder(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public ApiErrorResponse buildErrorMessage(HttpStatusCodeException e, HttpStatus httpStatus, String pointer) {
        String responseBodyAsString = e.getResponseBodyAsString();
        log.info("action=buildGDDErrorMessageStarted, responseBodyAsString={}", responseBodyAsString);
        ApiErrorResponse apiErrorResponse;
        try {
            GDDError getQrError = objectMapper.readValue(responseBodyAsString, GDDError.class);
            apiErrorResponse = buildApiErrorResponse(httpStatus, getQrError.getMessage(), pointer);
            if (CollectionUtils.isEmpty(apiErrorResponse.getErrors())) {
                log.error("action=buildGDDErrorMessageWithoutMapper");
                apiErrorResponse = buildApiErrorResponse(httpStatus, responseBodyAsString, pointer);
            }
        } catch (IOException ioException) {
            log.error("action=buildGDDErrorMessageFailed, error={}", e.getMessage());
            apiErrorResponse = buildApiErrorResponse(httpStatus, responseBodyAsString, pointer);
        }
        log.info("action=buildGDDErrorMessageSucceed, apiErrorResponse={}", apiErrorResponse);
        return apiErrorResponse;
    }

    public ApiErrorResponse buildErrorMessage(RestClientException e, String system) {
        String errorMessage = e.getMessage();
        log.info("action=buildGDDErrorMessageStarted, errorMessage={}", errorMessage);
        ApiErrorResponse apiErrorResponse = buildApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, errorMessage, system);
        log.info("action=buildGDDErrorMessageSucceed, apiErrorResponse={}", apiErrorResponse);
        return apiErrorResponse;
    }

    private ApiErrorResponse buildApiErrorResponse(HttpStatus httpStatus, String detail, String pointer) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
        String title = String.format("%s inválido", pointer);
        ApiError apiError = new ApiError(httpStatus.value(), title, detail, pointer);
        apiErrorResponse.addError(apiError);
        return apiErrorResponse;
    }
}
