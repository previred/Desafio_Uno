package com.previred.periodosperdidos.servicio.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
/**
 * Contiene la respuesta del GDD
 * 
 * @author Jorge San Martin
 *
 */
@Data
public class Periodo {

	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("fechaCreacion")
	private LocalDate fechaCreacion;
	
	@JsonProperty("fechaFin")
	private LocalDate fechaFin;
	
	@JsonProperty("fechas")
	private List<LocalDate> fechas;	
	
}
