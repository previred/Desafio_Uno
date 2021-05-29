package com.desafio1.model;

import java.util.List;
import java.time.LocalDate;

public class Periodo {

	private long id;
	private String fechaCreacion;
	private String fechaFin;
	private List<LocalDate> fechas;
	
	
	public long getId() { return this.id; }
	public String getFechaCreacion() { return this.fechaCreacion; }
	public String getFechaFin() { return this.fechaFin; }
	public List<LocalDate> getFechas() { return this.fechas; }
	
	
	public void setId(long id) {  this.id = id; }
	public void setFechaCreacion(String fechaCreacion) {  this.fechaCreacion = fechaCreacion; }
	public void setFechaFin(String fechaFin) {  this.fechaFin = fechaFin; }
	public void setFechas(List<LocalDate> fechas) {  this.fechas = fechas; }
	
}
