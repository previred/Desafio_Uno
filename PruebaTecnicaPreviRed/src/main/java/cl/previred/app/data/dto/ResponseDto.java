package cl.previred.app.data.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResponseDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8196251446849300219L;
	
	private long id;
	private String fechaCreacion;
	private String fechaFin;
	private List<String> fechas;
	private List<String> fechasFaltante;
	
	public ResponseDto() {
		
	}
	
	public ResponseDto(long id, String fechaCreacion, String fechaFin, List<String> fechas) {
		this.id = id;
		this.fechaCreacion = fechaCreacion;
		this.fechaFin = fechaFin;
		this.fechas = fechas;
		this.fechasFaltante = new ArrayList<String>(0);
	}
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
	public List<String> getFechas() {
		return fechas;
	}
	public void setFechas(List<String> fechas) {
		this.fechas = fechas;
	}
	public List<String> getFechasFaltante() {
		return fechasFaltante;
	}
	public void setFechasFaltante(List<String> fechasFaltante) {
		this.fechasFaltante = fechasFaltante;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
