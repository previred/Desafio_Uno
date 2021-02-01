package com.previred.GDD.models;

import java.util.Date;
import java.util.List;

public class PeriodoServicio {

	private int id;
	private String fechaCreacion;
	private String fechaFin;
	private List<String> fechas;
	

	public PeriodoServicio() {
	}

	public PeriodoServicio(int id, String fechaCreacion, String fechaFinal, List<String> fechas) {
		super();
		this.id = id;
		this.fechaCreacion = fechaCreacion;
		this.fechaFin = fechaFinal;
		this.fechas = fechas;
		
	}

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
