package cl.previred.infrastructure.adapters.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import cl.previred.presentation.api.model.error.ApiErrorResponse;

/**
 * @author wmunoz
 */
public class SourceSystemServerErrorException extends RuntimeException {

    @Getter
    private final String system;
    @Getter
    private final ApiErrorResponse sourceApiErrorResponse;
    @Getter
    private final HttpStatus sourceStatusCode;

    public SourceSystemServerErrorException(Exception cause, String system,
                                            ApiErrorResponse apiErrorResponse, HttpStatus statusCode) {
        super(cause.getMessage(), cause);
        this.system = system;
        this.sourceApiErrorResponse = apiErrorResponse;
        this.sourceStatusCode = statusCode;
    }
    
}
