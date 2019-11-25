package com.previred.periodosperdidos.exception;

/**
 * Se genera cuando hay un problema en la generacion del archivo 
 * 
 * @author Jorge San Martìn
 *
 */
public class GeneracionArchivoException extends Exception {
	
	
	private static final long serialVersionUID = 3024887755832811735L;

	public GeneracionArchivoException(String mensaje) {
		super(mensaje);
	}
	
	public GeneracionArchivoException() {
		super();
	}
}
