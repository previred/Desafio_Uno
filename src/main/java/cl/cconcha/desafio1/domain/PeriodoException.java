package cl.cconcha.desafio1.domain;

public class PeriodoException{

	private String codigo;
	private String mensaje;
	
	public PeriodoException(String codigo,String mensaje) {
		setCodigo(codigo);
		setMensaje(mensaje);
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	
}
