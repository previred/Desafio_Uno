package cl.previred.arquitectura.seleccion.lagunas.exceptions;

/**
 * Excepcion que sera lanzada por los canales de salida
 * @author Juan Villablanca
 *
 */
public class SalidaException extends Exception {

	private static final long serialVersionUID = -2547862759122032603L;

	public SalidaException() {
	}

	public SalidaException(String message) {
		super(message);
	}

	public SalidaException(Throwable cause) {
		super(cause);
	}

	public SalidaException(String message, Throwable cause) {
		super(message, cause);
	}

	public SalidaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
