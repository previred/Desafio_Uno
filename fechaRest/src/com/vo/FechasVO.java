package com.vo;

import java.util.ArrayList;

public class FechasVO {

	private int id;
	private String fechaCreacion;
	private String fechaFin;
	private ArrayList<String> fechas;
	private ArrayList<String> fechasFaltantes;
	
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
	public ArrayList<String> getFechas() {
		return fechas;
	}
	public void setFechas(ArrayList<String> fechas) {
		this.fechas = fechas;
	}
	public ArrayList<String> getFechasFaltantes() {
		return fechasFaltantes;
	}
	public void setFechasFaltantes(ArrayList<String> fechasFaltantes) {
		this.fechasFaltantes = fechasFaltantes;
	}
	
}
