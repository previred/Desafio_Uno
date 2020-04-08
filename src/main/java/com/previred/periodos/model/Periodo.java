package com.previred.periodos.model;

import java.io.Serializable;
import java.util.List;

/**
*
* @author arojas
*/
public class Periodo implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String fechaCreacion;
	private String fechaFin;
	private List<String> fechas;
	private List<String> fechasFaltantes;
	
	public Periodo() {
		super();
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

	public List<String> getFechasFaltantes() {
		return fechasFaltantes;
	}

	public void setFechasFaltantes(List<String> fechasFaltantes) {
		this.fechasFaltantes = fechasFaltantes;
	}

	@Override
	public String toString() {
		return "Periodo [id=" + id + ", fechaCreacion=" + fechaCreacion + ", fechaFin=" + fechaFin + "]";
	}
}
