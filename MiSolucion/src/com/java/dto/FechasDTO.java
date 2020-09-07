package com.java.dto;

import java.util.ArrayList;
import java.util.Date;

public class FechasDTO {
	
	private String id;
	private String fechaInicio;
	private String fechaFin;
	private ArrayList<String> fechas;
	private ArrayList<String> fechasSalientes;
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the fechaInicio
	 */
	public String getFechaInicio() {
		return fechaInicio;
	}
	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	/**
	 * @return the fechaFin
	 */
	public String getFechaFin() {
		return fechaFin;
	}
	/**
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	/**
	 * @return the fechas
	 */
	public ArrayList<String> getFechas() {
		return fechas;
	}
	/**
	 * @param fechas the fechas to set
	 */
	public void setFechas(ArrayList<String> fechas) {
		this.fechas = fechas;
	}
	/**
	 * @return the fechasSalientes
	 */
	public ArrayList<String> getFechasSalientes() {
		return fechasSalientes;
	}
	/**
	 * @param fechasSalientes the fechasSalientes to set
	 */
	public void setFechasSalientes(ArrayList<String> fechasSalientes) {
		this.fechasSalientes = fechasSalientes;
	}
	
}
