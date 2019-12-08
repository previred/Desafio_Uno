package com.desafioPrevired.consumingRest.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class PeriodoOut {
	
	private Long id = null;
	private LocalDate fechaCreacion = null;
	private LocalDate fechaFin = null;
	private List<LocalDate> fechas = null;
	private List<LocalDate> fechasFaltantes = null;
	
	public PeriodoOut() {
		this.id = null;
		this.fechaCreacion = null;
		this.fechaFin = null;
		this.fechas = null;
		this.fechasFaltantes = null;
	}

	public PeriodoOut(Long id, LocalDate fechaCreacion, LocalDate fechaFin, List<LocalDate> fechas,
			List<LocalDate> fechasFaltantes) {
		super();
		this.id = id;
		this.fechaCreacion = fechaCreacion;
		this.fechaFin = fechaFin;
		this.fechas = fechas;
		this.fechasFaltantes = fechasFaltantes;
	}

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

	@Override
	public String toString() {
		return "PeriodoOut [id=" + id + ", fechaCreacion=" + fechaCreacion + ", fechaFin=" + fechaFin + ", fechas="
				+ fechas + ", fechasFaltantes=" + fechasFaltantes + "]";
	}
}
