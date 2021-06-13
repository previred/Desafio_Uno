package com.desafio.object;

import java.time.LocalDate;
import java.util.List;
/**
 * Clase utilizada para Deserializar respuesta de api GDD
 * se agrega el campo error para poder obtener los errores que existan en tiempo de ejecución 
 * error = null si no existe error 
 * @author alejandro.cabezas05@gmail.com
 */
public class PeriodosDeserializer {
	private Long id;
	private LocalDate fechaCreacion;
	private LocalDate fechaFin;
	private List<LocalDate> fechas;
	private String error;	
	public PeriodosDeserializer() {				
	}	
	public PeriodosDeserializer(Long id, LocalDate fechaCreacion, LocalDate fechaFin, List<LocalDate> fechas, String error) {	
		this.id = id;
		this.fechaCreacion = fechaCreacion;
		this.fechaFin = fechaFin;
		this.fechas = fechas;
		this.error = error;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}	
}
