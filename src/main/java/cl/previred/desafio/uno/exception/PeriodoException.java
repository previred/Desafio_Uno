package cl.previred.desafio.uno.exception;

import org.springframework.http.HttpStatus;

public class PeriodoException extends RuntimeException {

	private static final long serialVersionUID = -5453478936996048422L;

	private HttpStatus httpStatus;
	private int code;

	public PeriodoException(String message, Throwable cause, HttpStatus httpStatus, int code) {
		super(message, cause);
		this.httpStatus = httpStatus;
		this.code = code;
	}

	public PeriodoException(String message, HttpStatus httpStatus, int code) {
		super(message);
		this.httpStatus = httpStatus;
		this.code = code;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public int getCode() {
		return code;
	}

}
