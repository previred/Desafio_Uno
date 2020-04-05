package com.previred.periodos.swagger.codegen.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.previred.periodos.swagger.codegen.model.Periodo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * PeriodosFaltantes
 */
@Validated

public class PeriodosFaltantes   {
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
  @Valid
  private List<LocalDate> fechasFaltantes = null;

  public PeriodosFaltantes id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public PeriodosFaltantes fechaCreacion(LocalDate fechaCreacion) {
    this.fechaCreacion = fechaCreacion;
    return this;
  }

  /**
   * Get fechaCreacion
   * @return fechaCreacion
  **/
  @ApiModelProperty(value = "")

  @Valid

  public LocalDate getFechaCreacion() {
    return fechaCreacion;
  }

  public void setFechaCreacion(LocalDate fechaCreacion) {
    this.fechaCreacion = fechaCreacion;
  }

  public PeriodosFaltantes fechaFin(LocalDate fechaFin) {
    this.fechaFin = fechaFin;
    return this;
  }

  /**
   * Get fechaFin
   * @return fechaFin
  **/
  @ApiModelProperty(value = "")

  @Valid

  public LocalDate getFechaFin() {
    return fechaFin;
  }

  public void setFechaFin(LocalDate fechaFin) {
    this.fechaFin = fechaFin;
  }

  public PeriodosFaltantes fechas(List<LocalDate> fechas) {
    this.fechas = fechas;
    return this;
  }

  public PeriodosFaltantes addFechasItem(LocalDate fechasItem) {
    if (this.fechas == null) {
      this.fechas = new ArrayList<>();
    }
    this.fechas.add(fechasItem);
    return this;
  }

  /**
   * Get fechas
   * @return fechas
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<LocalDate> getFechas() {
    return fechas;
  }

  public void setFechas(List<LocalDate> fechas) {
    this.fechas = fechas;
  }

  public PeriodosFaltantes fechasFaltantes(List<LocalDate> fechasFaltantes) {
    this.fechasFaltantes = fechasFaltantes;
    return this;
  }

  public PeriodosFaltantes addFechasFaltantesItem(LocalDate fechasFaltantesItem) {
    if (this.fechasFaltantes == null) {
      this.fechasFaltantes = new ArrayList<>();
    }
    this.fechasFaltantes.add(fechasFaltantesItem);
    return this;
  }

  /**
   * Get fechasFaltantes
   * @return fechasFaltantes
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<LocalDate> getFechasFaltantes() {
    return fechasFaltantes;
  }

  public void setFechasFaltantes(List<LocalDate> fechasFaltantes) {
    this.fechasFaltantes = fechasFaltantes;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PeriodosFaltantes periodosFaltantes = (PeriodosFaltantes) o;
    return Objects.equals(this.id, periodosFaltantes.id) &&
        Objects.equals(this.fechaCreacion, periodosFaltantes.fechaCreacion) &&
        Objects.equals(this.fechaFin, periodosFaltantes.fechaFin) &&
        Objects.equals(this.fechas, periodosFaltantes.fechas) &&
        Objects.equals(this.fechasFaltantes, periodosFaltantes.fechasFaltantes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, fechaCreacion, fechaFin, fechas, fechasFaltantes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PeriodosFaltantes {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    fechaCreacion: ").append(toIndentedString(fechaCreacion)).append("\n");
    sb.append("    fechaFin: ").append(toIndentedString(fechaFin)).append("\n");
    sb.append("    fechas: ").append(toIndentedString(fechas)).append("\n");
    sb.append("    fechasFaltantes: ").append(toIndentedString(fechasFaltantes)).append("\n");
    sb.append("}");
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

