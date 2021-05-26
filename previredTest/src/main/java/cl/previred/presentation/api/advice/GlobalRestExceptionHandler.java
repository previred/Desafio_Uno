package cl.previred.presentation.api.advice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import cl.previred.infrastructure.adapters.exception.SourceSystemClientErrorException;
import cl.previred.infrastructure.adapters.exception.SourceSystemConstraintViolationsException;
import cl.previred.infrastructure.adapters.exception.SourceSystemServerErrorException;
import cl.previred.presentation.api.model.error.ApiError;
import cl.previred.presentation.api.model.error.ApiErrorResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * This class allows to handle the exceptions to be showed in a clear way
  
 * @author wmunoz
 *
 */
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class GlobalRestExceptionHandler {
	  public static final String INVALID_PARAMETER = "Parámetro inválido";

	    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
	    public ResponseEntity<Object> handle(HttpServletRequest request,
	                                         MethodArgumentNotValidException ex) {
	        log.error("handleMethodArgumentNotValidException, url={}, message={}",
	                request.getRequestURL(), ex.getMessage());
	        return buildResponse(HttpStatus.BAD_REQUEST, ex.getBindingResult().getAllErrors());
	    }

	    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
	    public ResponseEntity<ApiErrorResponse> handle(HttpServletRequest request,
	                                                   MethodArgumentTypeMismatchException ex) {
	        log.error("handleMethodArgumentTypeMismatchException, url={}, message={}",
	                request.getRequestURL(), ex.getMessage());
	        return buildResponse(HttpStatus.BAD_REQUEST, INVALID_PARAMETER, ex.getMessage(), ex.getName());
	    }

	    @ExceptionHandler(value = {ConstraintViolationException.class})
	    public ResponseEntity<ApiErrorResponse> handle(HttpServletRequest request,
	                                                   ConstraintViolationException ex) {
	        log.error("handleConstraintViolationException, url={}, message={}",
	                request.getRequestURL(), ex.getMessage());
	        return buildViolationResponse(HttpStatus.BAD_REQUEST, INVALID_PARAMETER, ex.getConstraintViolations());
	    }

	    @ExceptionHandler(value = {MissingRequestHeaderException.class})
	    public ResponseEntity<ApiErrorResponse> handle(HttpServletRequest request,
	                                                   MissingRequestHeaderException ex) {
	        log.error("handleMissingRequestHeaderException, url={}, message={}",
	                request.getRequestURL(), ex.getMessage());
	        return buildResponse(HttpStatus.BAD_REQUEST, INVALID_PARAMETER, ex.getMessage(), ex.getHeaderName());
	    }


	    @ExceptionHandler(value = {SourceSystemClientErrorException.class})
	    public ResponseEntity<ApiErrorResponse> handle(HttpServletRequest request,
	                                                   SourceSystemClientErrorException ex) {
	        log.error("handleSourceSystemClientErrorException, url={}, system={}, message={}",
	                request.getRequestURL(), ex.getSystem(), ex.getMessage());
	        return ResponseEntity.status(ex.getSourceStatusCode()).body(ex.getSourceApiErrorResponse());
	    }

	    @ExceptionHandler(value = {SourceSystemServerErrorException.class})
	    public ResponseEntity<ApiErrorResponse> handle(HttpServletRequest request,
	                                                   SourceSystemServerErrorException ex) {
	        log.error("handleSourceSystemServerErrorException, url={}, system={}, message={}",
	                request.getRequestURL(), ex.getSystem(), ex.getMessage());
	        return ResponseEntity.status(ex.getSourceStatusCode()).body(ex.getSourceApiErrorResponse());
	    }



	    @ExceptionHandler(value = {SourceSystemConstraintViolationsException.class})
	    public ResponseEntity<ApiErrorResponse> handle(HttpServletRequest request,
	                                                   SourceSystemConstraintViolationsException ex) {
	        log.error("handleSourceSystemConstraintViolationsException, url={}, system={}, message={}",
	                request.getRequestURL(), ex.getSystem(), ex.getMessage());
	        String title = String.format("Respuesta del servicio %s es inválida", ex.getSystem());
	        return buildViolationResponse(HttpStatus.UNPROCESSABLE_ENTITY, title, ex.getConstraintViolations());
	    }

	    @ExceptionHandler(value = {Exception.class})
	    public ResponseEntity<ApiErrorResponse> handleException(HttpServletRequest request,
	                                                            Exception ex) {
	        log.error("handleException, url={}, message={}, ex={}", request.getRequestURL(), ex.getMessage(), ex.getClass());
	        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno", ex.getMessage(), "/qr/validate");
	    }

	    private ResponseEntity<ApiErrorResponse> buildResponse(HttpStatus httpStatus,
	                                                           String title, String detail, String pointer) {
	        ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
	        ApiError apiError = buildApiError(httpStatus, title, detail, pointer);
	        apiErrorResponse.addError(apiError);
	        return ResponseEntity.status(httpStatus).body(apiErrorResponse);
	    }

	    private ResponseEntity<Object> buildResponse(HttpStatus httpStatus, List<ObjectError> allErrors) {
	        ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
	        allErrors.stream()
	                .map(objectError -> (FieldError) objectError)
	                .map(fieldError -> buildApiError(httpStatus, INVALID_PARAMETER,
	                        fieldError.getField().concat(" ").concat(Objects.requireNonNull(fieldError.getDefaultMessage())),
	                        fieldError.getField()))
	                .forEach(apiErrorResponse::addError);
	        return ResponseEntity.status(httpStatus).body(apiErrorResponse);
	    }

	    private ResponseEntity<ApiErrorResponse> buildViolationResponse(HttpStatus httpStatus, String title, Set<ConstraintViolation<?>> violations) {
	        ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
	        violations.stream()
	                .map(violation -> {
	                    String field = translateHeader(violation.getPropertyPath().toString());
	                    return buildApiError(httpStatus, title, field.concat(" ").concat(violation.getMessage()), field);
	                })
	                .forEach(apiErrorResponse::addError);
	        return ResponseEntity.status(httpStatus).body(apiErrorResponse);
	    }

	    private String translateHeader(String property) {
	        Map<String, String> headersTranslationMap = new HashMap<>();
	        return headersTranslationMap.getOrDefault(property, property);
	    }

	    private ApiError buildApiError(HttpStatus httpStatus, String title, String detail, String pointer) {
	        return new ApiError(httpStatus.value(), title, detail, pointer);
	    }

}