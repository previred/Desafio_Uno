package com.previred.periodos.exception;

import com.previred.periodos.exception.dto.ResponseErrorDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionManager {

    @ResponseBody
    @ExceptionHandler(FechaInicioMayorException.class)
    private ResponseEntity<Object> mensajeException(FechaInicioMayorException exception) {
        ResponseErrorDto errorDto = ResponseErrorDto.builder()
                .codigo(HttpStatus.BAD_REQUEST.toString())
                .mensaje(exception.getMessage())
                .build();
        return new ResponseEntity<>(errorDto, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(ServerErrorException.class)
    private ResponseEntity<Object> mensajeException(ServerErrorException exception) {
        ResponseErrorDto errorDto = ResponseErrorDto.builder()
                .codigo(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                .mensaje(exception.getMessage())
                .build();
        return new ResponseEntity<>(errorDto, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
