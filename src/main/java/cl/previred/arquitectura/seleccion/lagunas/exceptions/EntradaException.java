package cl.previred.arquitectura.seleccion.lagunas.exceptions;

/**
 * Excepcion que sera lanzada por lo canales de entrada o por las validaciones a los datos entregados por el canal
 * @author Juan Villablanca
 *
 */
public class EntradaException extends Exception{


	private static final long serialVersionUID = 1L;


	public EntradaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public EntradaException(String message, Throwable cause) {
		super(message, cause);
	}

	public EntradaException(String message) {
		super(message);
	}

	public EntradaException(Throwable cause) {
		super(cause);
	}

}
