package com.previred.desafio.bjimenez.api.model;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class GddResponseModel {

	private Integer id;
	private String fechaCreacion;
	private String fechaFin;
	private List<String> fechas;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public String toString() {
		return "id: " + id + ", fechaCreacion: " + fechaCreacion + ", fechaFin: " + fechaFin + ", fechas: " + fechas;
	}

}
