package com.previred.vc.mejoraperiodos.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Periodo   {
	
  @JsonProperty("id")
  @ApiModelProperty(notes = "identificador de la solicitud api-periodos")
  private Long id = null;

  @JsonProperty("fechaCreacion")
  @ApiModelProperty(notes = "Fecha de Inicio del rango retornado por api-periodos")
  private LocalDate fechaCreacion = null;

  @JsonProperty("fechaFin")
  @ApiModelProperty(notes = "Fecha de Fin del rango retornado por api-periodos")
  private LocalDate fechaFin = null;

  @JsonProperty("fechas")
  @ApiModelProperty(notes = "Lista de Fechas retornadas por api-periodos")
  private List<LocalDate> fechas = null;

}

