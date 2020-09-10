package com.desafio.previred.apidesafio.generador;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FechasFaltantesRequest {

	 @JsonProperty("id")
	private int id;
	 @JsonProperty("fechaCreacion")
	private LocalDate fechaCreacion;
	 @JsonProperty("fechaFin")
	private LocalDate fechaFin;
	 @JsonProperty("fechas")
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
	
	
}
