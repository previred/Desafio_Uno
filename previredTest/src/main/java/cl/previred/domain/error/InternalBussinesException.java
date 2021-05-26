package cl.previred.domain.error;
/**
 * @author wmunoz
 *
 */
public class InternalBussinesException extends Exception {

    public InternalBussinesException(String message) {
        super(message);
    }

    public InternalBussinesException(String message, Throwable cause) {
        super(message, cause);
    }
}
