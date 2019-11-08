package com.previred.desafio.missingdates.model;

import java.time.LocalDate;
import java.util.List;

public class DateInfo {

	private String id;
	private LocalDate fechaCreacion;
	private LocalDate fechFin;
	private List<LocalDate> fechas;
	private List<LocalDate> fechasFaltantes;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public LocalDate getFechFin() {
		return fechFin;
	}
	public void setFechFin(LocalDate fechFin) {
		this.fechFin = fechFin;
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
}
