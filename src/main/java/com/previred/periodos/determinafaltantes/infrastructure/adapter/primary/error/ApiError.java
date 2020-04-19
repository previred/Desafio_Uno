package com.previred.periodos.determinafaltantes.infrastructure.adapter.primary.error;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

@Data
class ApiError {

    private HttpStatus status;
    private List<String> errors;

    ApiError(final HttpStatus status, final List<String> errors) {
        super();
        this.status = status;
        this.errors = errors;
    }

    ApiError(final HttpStatus status, final String error) {
        super();
        this.status = status;
        errors = Collections.singletonList(error);
    }
}
