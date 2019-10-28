package com.previred.desafio.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * Periodos
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-26T00:07:05.808Z")

public class Periodos   {

  @JsonProperty("id")
  private Long id = null;

  @JsonFormat(pattern = "yyyy-MM-dd")
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

  public Periodos id(Long id) {
    this.id = id;
    return this;
  }

  public Periodos fechaFin(LocalDate fechaFin) {
    this.fechaFin = fechaFin;
    return this;
  }

  public Periodos fechas(List<LocalDate> fechas) {
    this.fechas = fechas;
    return this;
  }

  public Periodos addFechasItem(LocalDate fechasItem) {
    if (this.fechas == null) {
      this.fechas = new ArrayList<>();
    }
    this.fechas.add(fechasItem);
    return this;
  }

  public Periodos fechasFaltantes(List<LocalDate> fechasFaltantes) {
    this.fechasFaltantes = fechasFaltantes;
    return this;
  }

  public Periodos addFechasFaltantesItem(LocalDate fechasFaltantesItem) {
    if (this.fechasFaltantes == null) {
      this.fechasFaltantes = new ArrayList<>();
    }
    this.fechasFaltantes.add(fechasFaltantesItem);
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
    Periodos periodos = (Periodos) o;
    return  Objects.equals(this.id, periodos.id) &&
            Objects.equals(this.fechaCreacion, periodos.fechaCreacion) &&
            Objects.equals(this.fechaFin, periodos.fechaFin) &&
            Objects.equals(this.fechas, periodos.fechas) &&
            Objects.equals(this.fechasFaltantes, periodos.fechasFaltantes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, fechaCreacion, fechaFin, fechas, fechasFaltantes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Periodos {\n");

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

