	package com.luisnunez.springboot.api.periodos.faltantes.models;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;


public class PeriodosFaltantes {

	private Periodos periodos;
	
	@JsonProperty("fechasFaltantes")
	@Valid
	private List<LocalDate> fechasFaltantes;
	
	public PeriodosFaltantes() {
	}
	
	public PeriodosFaltantes(Periodos periodo, List<LocalDate> fechas) {
		this.periodos = periodo;
		this.fechasFaltantes = fechas;
	}

	public Periodos getPeriodos() {
		return periodos;
	}

	public void setPeriodos(Periodos periodos) {
		this.periodos = periodos;
	}

	public List<LocalDate> getFechasFaltantes() {
		return fechasFaltantes;
	}

	public void setFechasFaltantes(List<LocalDate> fechasFaltantes) {
		this.fechasFaltantes = fechasFaltantes;
	}
}
