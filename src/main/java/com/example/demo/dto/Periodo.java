package com.example.demo.dto;

import java.util.Date;
import java.util.List;
import org.springframework.validation.annotation.Validated;

/**
 * Periodo
 */
@Validated

public class Periodo {
	private Long id;

	private Date fechaCreacion;

	private Date fechaFin;

	private List<Date> fechas;
	
	private List<Date> fechasFaltantes;

	public Periodo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public List<Date> getFechas() {
		return fechas;
	}

	public void setFechas(List<Date> fechas) {
		this.fechas = fechas;
	}
	
	public List<Date> getFechasFaltantes() {
		return fechasFaltantes;
	}

	public void setFechasFaltantes(List<Date> fechasFaltantes) {
		this.fechasFaltantes = fechasFaltantes;
	}

}
