/**
 * 
 */
package com.previred.periodos.misolucion.errores;

/**
 * @author Leonardo Silva Bustos
 *
 */
public class MiSolucionError extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 720183477730955013L;

	public MiSolucionError() {
		super();
	}
	
	public MiSolucionError(String msg) {
		super(msg);
	}
	
	public MiSolucionError(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	public MiSolucionError(Throwable cause) {
		super(cause);
	}

}
