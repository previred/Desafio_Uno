package cl.previred.domain.error;

/**
 * @author wmunoz
 *
 */
public class InternalBussinessRuntimeException extends RuntimeException {

  
	private static final long serialVersionUID = 1L;

	public InternalBussinessRuntimeException(String message) {
        super(message);
    }
}
