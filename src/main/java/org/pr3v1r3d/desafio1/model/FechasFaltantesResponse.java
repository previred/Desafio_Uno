package org.pr3v1r3d.desafio1.model;

import java.time.LocalDate;
import java.util.List;

public class FechasFaltantesResponse {

	private Long id;
	private LocalDate fechaCreacion; //yyyy-mm-dd
	private LocalDate fechaFin; //yyyy-mm-dd
	private List<LocalDate> fechas; //yyyy-mm-dd
	private List<LocalDate> fechasFaltantes; //yyyy-mm-dd

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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

}
