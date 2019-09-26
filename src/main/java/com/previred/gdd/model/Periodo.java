package com.previred.gdd.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

@Validated
public class Periodo   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("fechaCreacion")
  private LocalDate fechaCreacion = null;

  @JsonProperty("fechaFin")
  private LocalDate fechaFin = null;

  @JsonProperty("fechas")
  @Valid
  private List<LocalDate> fechas = null;

  @JsonProperty("fechasFaltantes")
  private List<LocalDate> fechasFaltantes = null;
  
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public Periodo fechaCreacion(LocalDate fechaCreacion) {
    this.fechaCreacion = fechaCreacion;
    return this;
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
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Periodo periodo = (Periodo) o;
    return Objects.equals(this.id, periodo.id) &&
        Objects.equals(this.fechaCreacion, periodo.fechaCreacion) &&
        Objects.equals(this.fechaFin, periodo.fechaFin) &&
        Objects.equals(this.fechas, periodo.fechas);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, fechaCreacion, fechaFin, fechas);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Periodo {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    fechaCreacion: ").append(toIndentedString(fechaCreacion)).append("\n");
    sb.append("    fechaFin: ").append(toIndentedString(fechaFin)).append("\n");
    sb.append("    fechas: ").append(toIndentedString(fechas)).append("\n");
    sb.append("    fechasFaltantes: ").append(toIndentedString(fechasFaltantes)).append("\n");
    sb.append("}");
    return sb.toString();
  }
  public String toText() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("fecha creacion: ").append(toIndentedString(fechaCreacion)).append("\n");
	    sb.append("fecha Fin: ").append(toIndentedString(fechaFin)).append("\n");
	    sb.append("fechas Recibidas: ").append(toIndentedString(fechas)).append("\n");
	    sb.append("fechas Faltantes: ").append(toIndentedString(fechasFaltantes)).append("\n");
	    return sb.toString();
	  }
  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

