package com.previred.vc.mejoraperiodos.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@ApiModel
public class PeriodoFaltante extends Periodo  {
	
  @JsonProperty("fechasFaltantes")
  @ApiModelProperty(notes = "Lista de Fechas faltantes calculadas por api-faltantes")
  private List<LocalDate> fechasFaltantes = null;
  
  public PeriodoFaltante (Periodo periodo) {
	  this.setId(periodo.getId());
	  this.setFechaCreacion(periodo.getFechaCreacion());
	  this.setFechaFin(periodo.getFechaFin());
	  this.setFechas(periodo.getFechas());
  }

}

