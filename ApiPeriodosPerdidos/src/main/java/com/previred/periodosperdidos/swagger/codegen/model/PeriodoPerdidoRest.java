package com.previred.periodosperdidos.swagger.codegen.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Periodo Perdodo consumiendo Rest
 */
@Validated

public class PeriodoPerdidoRest extends Periodo {

  @JsonProperty("fechasPerdidas")
  @Valid
  private List<String> fechasPerdidas = new ArrayList<>();

  public PeriodoPerdidoRest fechasPerdidas(List<String> fechasPerdidas) {
    this.fechasPerdidas = fechasPerdidas;
    return this;
  }

  public PeriodoPerdidoRest addFechasPerdidasItem(String fechasPerdidasItem) {
    if (this.fechasPerdidas == null) {
      this.fechasPerdidas = new ArrayList<>();
    }
    this.fechasPerdidas.add(fechasPerdidasItem);
    return this;
  }

  /**
   * Get fechasPerdidas
   * @return fechasPerdidas
   **/
  @ApiModelProperty(value = "")

  @Valid

  public List<String> getFechasPerdidas() {
    return fechasPerdidas;
  }

  public void setFechasPerdidas(List<String> fechasPerdidas) {
    this.fechasPerdidas = fechasPerdidas;
  }

  public PeriodoPerdidoRest setPeriodo(Periodo periodo){
    if(periodo!=null){
      this.setId(periodo.getId());
      this.setFechaCreacion(periodo.getFechaCreacion());
      this.setFechaFin(periodo.getFechaFin());
      this.setFechas(periodo.getFechas());
    }
    return this;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PeriodoPerdidoRest periodo = (PeriodoPerdidoRest) o;
    return Objects.equals(this.getId(), periodo.getId()) &&
        Objects.equals(this.getFechaCreacion(), periodo.getFechaCreacion()) &&
        Objects.equals(this.getFechaFin(), periodo.getFechaFin()) &&
        Objects.equals(this.getFechas(), periodo.getFechas()) &&
        Objects.equals(this.fechasPerdidas, periodo.fechasPerdidas);
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getFechaCreacion(), getFechaFin(), getFechas(), fechasPerdidas);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PeriodoPerdidoRest {\n");
    
    sb.append("    id: ").append(toIndentedString(getId())).append("\n");
    sb.append("    fechaCreacion: ").append(toIndentedString(getFechaCreacion())).append("\n");
    sb.append("    fechaFin: ").append(toIndentedString(getFechaFin())).append("\n");
    sb.append("    fechas: ").append(toIndentedString(getFechas())).append("\n");
    sb.append("    fechasPerdidas: ").append(toIndentedString(fechasPerdidas)).append("\n");
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

