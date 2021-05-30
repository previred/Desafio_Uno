package cl.cmoscoso.controller;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import cl.cmoscoso.dto.ErrorMessageDTO;

@RestControllerAdvice
public class HandlerExecptionController {
	private Logger LOG = LoggerFactory.getLogger(this.getClass().getName());

	@ExceptionHandler(value = { Exception.class })
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorMessageDTO resourceNotFoundException(Exception ex, WebRequest request) {
		LOG.error(ex.getMessage(), ex);

		ErrorMessageDTO message = new ErrorMessageDTO(HttpStatus.BAD_REQUEST.value(), LocalDateTime.now().toString(),
				ex.getMessage());

		return message;
	}
}
