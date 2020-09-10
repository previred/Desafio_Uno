package com.desafio.previred.apidesafio.fechasfaltantes;

import java.time.LocalDate;
import java.util.List;

public class FechasFaltantesResponse {

	private int id;
	private LocalDate fechaCreacion;
	private LocalDate fechaFin;
	private List<LocalDate> fechas;
	private List<LocalDate> fechasFaltantes;

	public FechasFaltantesResponse(int id, LocalDate fechaCreacion, LocalDate fechaFin, List<LocalDate> fechas,
			List<LocalDate> fechasFaltantes) {
		this.id = id;
		this.fechaCreacion = fechaCreacion;
		this.fechaFin = fechaFin;
		this.fechas = fechas;
		this.fechasFaltantes = fechasFaltantes;
	}

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

	public List<LocalDate> getFechasFaltantes() {
		return fechasFaltantes;
	}

	public void setFechasFaltantes(List<LocalDate> fechasFaltantes) {
		this.fechasFaltantes = fechasFaltantes;
	}

}
