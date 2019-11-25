package com.cox.igdd.models;

import java.util.List;

import org.springframework.stereotype.Component;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Component
@ApiModel
public class OutListadoFechas { 

	@ApiModelProperty(notes = "Identificador fechaCreacion")
	private Integer id;
	@ApiModelProperty(notes = "Fecha de inicio de la secuencia")
	private String fechaCreacion;
	@ApiModelProperty(notes = "Fecha de fin de la secuencia")
	private String fechaFin;
	@ApiModelProperty(notes = "Lista de fechas que están en el rango de la fecha que se encuentra en “fechaCreacion” hasta la fecha “fechaFin”")
	private List<String> fechas;
	@ApiModelProperty(notes = "Lista de fechas faltantes")
	private List<String> fechasFaltantes;
	public Integer getId() {
		return id;
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
	public void setId(Integer id) {
		this.id = id;
	}
	

}
