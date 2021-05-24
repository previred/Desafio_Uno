package com.previred.uno.models.periodos;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * Clase que representa un Periodo en MS consumido ApiPeriodos,
 * utilizada para obtener respuesta de MS ApiPeriodos.
 *
 * @author pvillar
 */

public class PeriodosRest {

  @JsonProperty("id")
  private Long id;

  @JsonProperty("fechaCreacion")
  private LocalDate fechaCreacion;

  @JsonProperty("fechaFin")
  private LocalDate fechaFin;

  @JsonProperty("fechas")
  private List<LocalDate> fechas;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PeriodosRest that = (PeriodosRest) o;
    return Objects.equals(id, that.id) && Objects.equals(fechaCreacion, that.fechaCreacion) && Objects.equals(fechaFin, that.fechaFin) && Objects.equals(fechas, that.fechas);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, fechaCreacion, fechaFin, fechas);
  }

  @Override
  public String toString() {
    return "PeriodosRest{" +
            "id=" + id +
            ", fechaCreacion=" + fechaCreacion +
            ", fechaFin=" + fechaFin +
            ", fechas=" + fechas +
            '}';
  }
}

