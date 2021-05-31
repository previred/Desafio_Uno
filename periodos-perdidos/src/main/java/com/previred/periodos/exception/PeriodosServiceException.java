package com.previred.periodos.exception;

public class PeriodosServiceException extends Exception {

	/**
	 * serial uid.
	 */
	private static final long serialVersionUID = -7435269028073994504L;

	public PeriodosServiceException() {
		super();
	}

	public PeriodosServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PeriodosServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public PeriodosServiceException(String message) {
		super(message);
	}

	public PeriodosServiceException(Throwable cause) {
		super(cause);
	}

}
