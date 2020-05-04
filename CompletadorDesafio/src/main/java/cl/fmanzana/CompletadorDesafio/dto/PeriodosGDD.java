package cl.fmanzana.CompletadorDesafio.dto;

public class PeriodosGDD {
	public long id;
	public String fechaCreacion;
	public String fechaFin;
	public String[] fechas;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	public String[] getFechas() {
		return fechas;
	}
	public void setFechas(String[] fechas) {
		this.fechas = fechas;
	}
	
	
}
