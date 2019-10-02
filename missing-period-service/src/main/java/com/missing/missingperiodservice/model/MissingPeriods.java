package com.missing.missingperiodservice.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Periodo
 */
@Validated

public class MissingPeriods   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("fechaCreacion")
  private LocalDate fechaCreacion = null;

  @JsonProperty("fechaFin")
  private LocalDate fechaFin = null;

  @JsonProperty("fechasFaltantes")
  @Valid
  private List<LocalDate> fechasFaltantes = new LinkedList<LocalDate>();
  
  
  @JsonProperty("fechas")
  @Valid
  private List<LocalDate> fechas = null;


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


public List<LocalDate> getFechasFaltantes() {
	return fechasFaltantes;
}


public void setFechasFaltantes(List<LocalDate> fechasFaltantes) {
	this.fechasFaltantes = fechasFaltantes;
}


public List<LocalDate> getFechas() {
	return fechas;
}


public void setFechas(List<LocalDate> fechas) {
	this.fechas = fechas;
}


@Override
public String toString() {
	return "MissingPeriods [id=" + id + ", fechaCreacion=" + fechaCreacion + ", fechaFin=" + fechaFin
			+ ", fechasFaltantes=" + fechasFaltantes + ", fechas=" + fechas + "]";
}


public MissingPeriods(Long id, LocalDate fechaCreacion, LocalDate fechaFin, @Valid List<LocalDate> fechasFaltantes,
		@Valid List<LocalDate> fechas) {
	super();
	this.id = id;
	this.fechaCreacion = fechaCreacion;
	this.fechaFin = fechaFin;
	this.fechasFaltantes = fechasFaltantes;
	this.fechas = fechas;
}


public MissingPeriods() {
	super();
	// TODO Auto-generated constructor stub
}
  

 

}





