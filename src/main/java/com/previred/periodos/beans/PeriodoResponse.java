package com.previred.periodos.beans;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PeriodoResponse extends Periodo {

	@JsonProperty("fechasFaltantes")
	@Valid
	private List<LocalDate> fechasFaltantes = null;

	public List<LocalDate> getFechasFaltantes() {
		return fechasFaltantes;
	}

	public void setFechasFaltantes(List<LocalDate> fechasFaltantes) {
		this.fechasFaltantes = fechasFaltantes;
	}

	@Override
	public String toString() {
		return super.toString() + "PeriodoResponse [fechasFaltantes=" + fechasFaltantes + "]";
	}
	
	
}
