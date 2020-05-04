package com.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

public class Periodo implements Serializable{

	private static final long serialVersionUID = 8214107695916681811L;
	@JsonDeserialize(using = LocalDateDeserializer.class) 
	@JsonSerialize(using = LocalDateSerializer.class)  
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaCreacion;
	@JsonDeserialize(using = LocalDateDeserializer.class) 
	@JsonSerialize(using = LocalDateSerializer.class)  
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaFin;
//	@JsonDeserialize(using = LocalDateArrayDeserializer.class) 
	@JsonFormat(pattern = "yyyy-MM-dd")
	private List<Date> fechas;
	private Integer id;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private List<Date> fechasFaltantes;
//	
	
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

	public List<Date> getFechas() {
		return fechas;
	}

	public void setFechas(List<Date> fechas) {
		this.fechas = fechas;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Date> getFechasFaltantes() {
		return fechasFaltantes;
	}

	public void setFechasFaltantes(List<Date> fechasFaltantes) {
		this.fechasFaltantes = fechasFaltantes;
	}	
}
