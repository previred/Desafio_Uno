package com.desafiouno.nivel3.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ResponseFechasFaltantes {
	
	public String id;
	
	@JsonFormat(pattern = "yyyy/MM/dd")
	public LocalDate  fechaCreacion;
	
	@JsonFormat(pattern = "yyyy/MM/dd")
	public LocalDate  fechaFin;
	
	@JsonFormat(pattern = "yyyy/MM/dd")
	public List<LocalDate> fechas;
	
	@JsonFormat(pattern = "yyyy/MM/dd")
	public List<LocalDate> fechasFaltantes;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	public List<LocalDate> getFechasFaltantes() {
		return fechasFaltantes;
	}
	public void setFechasFaltantes(List<LocalDate> fechasFaltantes) {
		this.fechasFaltantes = fechasFaltantes;
	}
	
	public void setParametrosResponsePeriodos(ResponsePeriodos responsePeriodos) {
		this.id = responsePeriodos.getId();
		this.fechaCreacion = responsePeriodos.getFechaCreacion();
		this.fechaFin = responsePeriodos.getFechaFin();
		this.fechas = responsePeriodos.getFechas();
		this.fechasFaltantes = this.obtenerFechasFaltantes(responsePeriodos);
	}
	
	public List<LocalDate> obtenerFechasFaltantes(ResponsePeriodos responsePeriodos){
		List<LocalDate> fechasFaltantes = new ArrayList<LocalDate>();
		for (LocalDate i = this.fechaCreacion; i.isBefore(this.fechaFin.plusMonths(1)); i = i.plusMonths(1)) {
			if (!responsePeriodos.getFechas().contains(i)) {
				fechasFaltantes.add(i);
			}			
		}
		return fechasFaltantes;
	}

}
