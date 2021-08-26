package me.jics.exception.handler;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import io.micronaut.http.server.exceptions.response.ErrorContext;
import io.micronaut.http.server.exceptions.response.ErrorResponseProcessor;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import me.jics.exception.DateRangeNotValidException;

/**
 * Exception that could be triggered when the delivered date range is not valid.
 *
 * @author Juan Cuzmar
 * @version 1.0
 */
@Slf4j
@Produces
@Singleton
@Requires(classes = {DateRangeNotValidException.class, ExceptionHandler.class})
public class DateRangeNotValidExceptionHandler implements ExceptionHandler<DateRangeNotValidException, HttpResponse<?>> {

    private final ErrorResponseProcessor<?> errorResponseProcessor;

    public DateRangeNotValidExceptionHandler(ErrorResponseProcessor<?> errorResponseProcessor) {
        this.errorResponseProcessor = errorResponseProcessor;
    }

    /**
     * method handling the exception and detailing the error
     * @param request the whole http request.
     * @param exception my custom exception
     * @return a response
     */
    @Override
    public HttpResponse<?> handle(HttpRequest request, DateRangeNotValidException exception) {
        return errorResponseProcessor.processResponse(ErrorContext.builder(request)
                .cause(exception)
                .errorMessage(exception.getMessage())
                .build(), HttpResponse.serverError());
    }
}
