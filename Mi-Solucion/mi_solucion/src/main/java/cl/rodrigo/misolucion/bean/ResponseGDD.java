package cl.rodrigo.misolucion.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseGDD {
	@JsonProperty("id")
	private int id;
	@JsonProperty("fechaCreacion")
	private String fechaCreacion;
	@JsonProperty("fechaFin")
	private String fechaFin;
	@JsonProperty("fechas")
	private List<String>fechas;
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public List<String> getFechas() {
		return fechas;
	}
	public void setFechas(List<String> fechas) {
		this.fechas = fechas;
	}
	
	
	
	
}
