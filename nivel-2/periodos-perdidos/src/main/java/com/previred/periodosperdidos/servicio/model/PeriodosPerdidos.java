package com.previred.periodosperdidos.servicio.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

/**
 * Contiene el resultado obtenido al determinar los Periodos que Faltan 
 * 
 * @author Jorge San Martin
 *
 */
@Data
@Builder
public class PeriodosPerdidos   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("fechaCreacion")
  private LocalDate fechaCreacion = null;

  @JsonProperty("fechaFin")
  private LocalDate fechaFin = null;

  @JsonProperty("fechas")  
  private List<LocalDate> fechas = null;

  @JsonProperty("fechasFaltantes")
  private List<LocalDate> fechasFaltantes = null;
}

