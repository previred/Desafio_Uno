package com.desafio1.model;

import java.util.List;


public class Cont {

	private long id;
	private String fechaCreacion;
	private String fechaFin;
	private List<String> fechas;
	private List<String> fechasFaltantes;
	
	public long getId() { return this.id; }
	public String getFechaCreacion() { return this.fechaCreacion; }
	public String getFechaFin() { return this.fechaFin; }
	public List<String> getFechas() { return this.fechas; }
	public List<String> getFechasFaltantes() { return this.fechasFaltantes; }
	
	public void setId(long id) {  this.id = id; }
	public void setFechaCreacion(String fechaCreacion) {  this.fechaCreacion = fechaCreacion; }
	public void setFechaFin(String fechaFin) {  this.fechaFin = fechaFin; }
	public void setFechas(List<String> fechas) {  this.fechas = fechas; }
	public void setFechasFaltantes(List<String> fechasFaltantes) {  this.fechasFaltantes = fechasFaltantes; }
}
