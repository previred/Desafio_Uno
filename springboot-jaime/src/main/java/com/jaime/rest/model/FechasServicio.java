package com.jaime.rest.model;

import java.util.List;

public class FechasServicio {

    private String id;
    private String fechaCreacion; 
    private String fechaFin; 
    private List<String> fechas; 
	
    public FechasServicio() {

    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	@Override
	public String toString() {
		return "FechasServicio [id=" + id + ", fechaCreacion=" + fechaCreacion + ", fechaFin=" + fechaFin + ", fechas="
				+ fechas + "]";
	}

	
	
}
