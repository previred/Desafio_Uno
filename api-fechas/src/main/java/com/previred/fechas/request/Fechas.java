package com.previred.fechas.request;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonPropertyOrder({

	"id",
	"fechaCreacion",
	"fechaFin",
	"fechas"
	

})
@EntityScan
@Service
public class Fechas {

	  @JsonProperty("id")
	  private Long id = null;

	  @JsonProperty("fechaCreacion")
	  private LocalDate fechaCreacion = null;

	  @JsonProperty("fechaFin")
	  private LocalDate fechaFin = null;

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

	public List<LocalDate> getFechas() {
		return fechas;
	}

	public void setFechas(List<LocalDate> fechas) {
		this.fechas = fechas;
	}

 
	  
 }