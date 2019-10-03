package cl.periodos.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class PeriodoTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String fechaCreacion;
	private String fechaFin;
	private List fechas;
	private List fechasFaltantes;
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
	public List getFechas() {
		return fechas;
	}
	public void setFechas(List fechas) {
		this.fechas = fechas;
	}
	public List getFechasFaltantes() {
		return fechasFaltantes;
	}
	public void setFechasFaltantes(List fechasFaltantes) {
		this.fechasFaltantes = fechasFaltantes;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PeriodoTO [id=");
		builder.append(id);
		builder.append(", fechaCreacion=");
		builder.append(fechaCreacion);
		builder.append(", fechaFin=");
		builder.append(fechaFin);
		builder.append(", fechas=");
		builder.append(fechas);
		builder.append(", fechasFaltantes=");
		builder.append(fechasFaltantes);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
}
