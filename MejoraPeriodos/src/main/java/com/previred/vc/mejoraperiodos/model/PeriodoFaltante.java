package com.previred.vc.mejoraperiodos.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class PeriodoFaltante extends Periodo  {
	
  @JsonProperty("fechasFaltantes")
  private List<LocalDate> fechasFaltantes = null;
  
  public PeriodoFaltante (Periodo periodo) {
	  this.setId(periodo.getId());
	  this.setFechaCreacion(periodo.getFechaCreacion());
	  this.setFechaFin(periodo.getFechaFin());
	  this.setFechas(periodo.getFechas());
  }

}

