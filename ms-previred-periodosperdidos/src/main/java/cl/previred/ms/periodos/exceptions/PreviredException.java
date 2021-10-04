package cl.previred.ms.periodos.exceptions;

import lombok.NonNull;

public class PreviredException extends Exception {


    /**
	 * 
	 */
	private static final long serialVersionUID = 137026440594474119L;

	@NonNull
    private final int httpCode;

    @NonNull
    private final int internalCode;

    public PreviredException (final String message, final Throwable cause, final int httpCode, final int internalCode) {
        super(message,cause);
        this.httpCode = httpCode;
        this.internalCode = internalCode;
    }

    public PreviredException (final String message, final int httpCode, final int internalCode) {
        super(message);
        this.httpCode = httpCode;
        this.internalCode = internalCode;
    }


}
