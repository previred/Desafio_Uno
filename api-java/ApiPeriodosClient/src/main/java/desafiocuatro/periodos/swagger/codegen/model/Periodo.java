/*
 * API periodos
 * Prueba para desafío de implementación 
 *
 * OpenAPI spec version: 1.0.0
 * Contact: mgonzalez@previred.com
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package desafiocuatro.periodos.swagger.codegen.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.threeten.bp.LocalDate;

/**
 * Periodo
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2021-10-10T18:13:48.450-05:00")
public class Periodo {
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

  public Periodo id(Long id) {
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

  public Periodo fechaCreacion(LocalDate fechaCreacion) {
    this.fechaCreacion = fechaCreacion;
    return this;
  }

   /**
   * Get fechaCreacion
   * @return fechaCreacion
  **/
  @ApiModelProperty(value = "")
  public LocalDate getFechaCreacion() {
    return fechaCreacion;
  }

  public void setFechaCreacion(LocalDate fechaCreacion) {
    this.fechaCreacion = fechaCreacion;
  }

  public Periodo fechaFin(LocalDate fechaFin) {
    this.fechaFin = fechaFin;
    return this;
  }

   /**
   * Get fechaFin
   * @return fechaFin
  **/
  @ApiModelProperty(value = "")
  public LocalDate getFechaFin() {
    return fechaFin;
  }

  public void setFechaFin(LocalDate fechaFin) {
    this.fechaFin = fechaFin;
  }

  public Periodo fechas(List<LocalDate> fechas) {
    this.fechas = fechas;
    return this;
  }

  public Periodo addFechasItem(LocalDate fechasItem) {
    if (this.fechas == null) {
      this.fechas = new ArrayList<LocalDate>();
    }
    this.fechas.add(fechasItem);
    return this;
  }

   /**
   * Get fechas
   * @return fechas
  **/
  @ApiModelProperty(value = "")
  public List<LocalDate> getFechas() {
    return fechas;
  }

  public void setFechas(List<LocalDate> fechas) {
    this.fechas = fechas;
  }

  public Periodo fechasFaltantes(List<LocalDate> fechasFaltantes) {
    this.fechasFaltantes = fechasFaltantes;
    return this;
  }

  public Periodo addFechasFaltantesItem(LocalDate fechasFaltantesItem) {
    if (this.fechasFaltantes == null) {
      this.fechasFaltantes = new ArrayList<LocalDate>();
    }
    this.fechasFaltantes.add(fechasFaltantesItem);
    return this;
  }

   /**
   * Get fechasFaltantes
   * @return fechasFaltantes
  **/
  @ApiModelProperty(value = "")
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
    Periodo periodo = (Periodo) o;
    return Objects.equals(this.id, periodo.id) &&
        Objects.equals(this.fechaCreacion, periodo.fechaCreacion) &&
        Objects.equals(this.fechaFin, periodo.fechaFin) &&
        Objects.equals(this.fechas, periodo.fechas) &&
        Objects.equals(this.fechasFaltantes, periodo.fechasFaltantes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, fechaCreacion, fechaFin, fechas, fechasFaltantes);
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

