/**
 * Periodo Faltante
 */
package com.desafio.previred;

import java.time.LocalDate;
import java.util.List;


/**
 * @author Carlos Toledo
 *
 */
public class PeriodoFaltante {
	private long id;
	private LocalDate fechaCreacion;
	private LocalDate fechaFin;
	private List<LocalDate> fechas;
	private List<LocalDate> fechasFaltantes;
	public long getId() {
		return id;
	}
	public void setId(long id) {
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
	public List<LocalDate> getFechasFaltantes() {
		return fechasFaltantes;
	}
	public void setFechasFaltantes(List<LocalDate> fechasFaltantes) {
		this.fechasFaltantes = fechasFaltantes;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PeriodoFaltante [id=");
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
