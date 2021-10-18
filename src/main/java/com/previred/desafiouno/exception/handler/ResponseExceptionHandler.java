package com.previred.desafiouno.exception.handler;

import com.previred.desafiouno.dto.BaseResponseDTO;
import com.previred.desafiouno.exception.FechaCreacionNulaException;
import com.previred.desafiouno.exception.FechaFinNulaException;
import com.previred.desafiouno.exception.RestClientException;
import com.previred.desafiouno.type.ResultCode;
import com.previred.desafiouno.util.MessageSourceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler({ FechaCreacionNulaException.class })
	public final BaseResponseDTO<Void> handleFechaCreacionNulaException(FechaCreacionNulaException exception) {
		log.error("handleFechaCreacionNulaException", exception);
		return new BaseResponseDTO<>(ResultCode.ERROR, MessageSourceUtil.getValue("msge.error.fecha.creacion.nula.vacia"), null);
	}

	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler({ FechaFinNulaException.class })
	public final BaseResponseDTO<Void> handleFechaFinNulaException(FechaFinNulaException exception) {
		log.error("handleFechaFinNulaException", exception);
		return new BaseResponseDTO<>(ResultCode.ERROR, MessageSourceUtil.getValue("msge.error.fecha.fin.nula.vacia"), null);
	}

	@ResponseStatus(HttpStatus.BAD_GATEWAY)
	@ExceptionHandler({ RestClientException.class })
	public final BaseResponseDTO<Void> handleRestClientException(RestClientException exception) {
		log.error("handleRestClientException", exception);
		return new BaseResponseDTO<>(ResultCode.ERROR, MessageSourceUtil.getValue("msge.error.invocacion.servicio.gdd"), null);
	}
}
