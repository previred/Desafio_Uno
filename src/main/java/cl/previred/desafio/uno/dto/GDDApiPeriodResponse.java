package cl.previred.desafio.uno.dto;

import java.util.List;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GDDApiPeriodResponse {
	
	private int id;
	private LocalDate fechaCreacion;
	private LocalDate fechaFin;
	private List<LocalDate> fechas;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}
	
	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	public LocalDate getFechaFin() {
		return fechaFin;
	}
	
	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	public List<LocalDate> getFechas() {
		return fechas;
	}
	
	public void setFechas(List<LocalDate> fechas) {
		this.fechas = fechas;
	}

	@Override
	public String toString() {
		return "GDDApiPeriodResponse [id=" + id + ", fechaCreacion=" + fechaCreacion + ", fechaFin=" + fechaFin
				+ ", fechas=" + fechas + "]";
	}

}
