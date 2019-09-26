package com.previred.gdd.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class GeneradorDatosException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public GeneradorDatosException(String message) {
        super(message);
    }
}
