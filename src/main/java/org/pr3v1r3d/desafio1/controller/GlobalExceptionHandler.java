package org.pr3v1r3d.desafio1.controller;

import org.pr3v1r3d.desafio1.model.GlobalError;
import org.pr3v1r3d.desafio1.service.FechasFaltantesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	Logger logger = LoggerFactory.getLogger(FechasFaltantesService.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GlobalError> exceptionHandler(Exception ex){
    	GlobalError globalError = new GlobalError();
    	globalError.setMesage(ex.getMessage());
    	logger.error(ex.getMessage());
    	return new ResponseEntity<GlobalError>(globalError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}