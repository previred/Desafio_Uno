package cl.previred.periodos.exception;

public class PreviredTechnicalException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	private final String codigo;

    public PreviredTechnicalException(String codigo, String mensaje){
    	super(mensaje);
        this.codigo = codigo;
    }

	public String getCodigo() {
		return codigo;
	}
    
    public String getMensaje() {
    	return getMessage();
    }
}
