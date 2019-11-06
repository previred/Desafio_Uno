package com.previred.entity;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Respuesta que entrega el servicio")
public class ServiceResponse {
	
	@ApiModelProperty(notes = "id")
	private int id;
	@ApiModelProperty(notes = "fecha de creacion, inicio secuencia")
	private String fechaCreacion;
	@ApiModelProperty(notes = "fecha final, fin de la secuencia")
	private String fechaFin;
	@ApiModelProperty(notes = "fechas entregadas dentro de el rango de la secuencia")
	private List<String> fechas;
	@ApiModelProperty(notes = "fechas que no nos entrega el CDD")
	private List<String> fechasFaltantes;

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

	public void setFechasFaltantes(List<String> fechas) {
		this.fechasFaltantes = fechas;
	}

}
