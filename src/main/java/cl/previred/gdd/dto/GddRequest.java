package cl.previred.gdd.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.HashSet;

public class GddRequest extends GddGenericDto {

  /** Habitualmente se una implementación de interfaz java.util.List para listas,
   * pero se utilizará un Hashset para acelerar búsquedas en un eventual set enorme
   * de datos **/
  @ApiModelProperty(notes = "Este campo es una lista de fechas en formato YYYY-MM-dd", example = "[\"2017-12-30\", \"2018-01-15\"]")
  private HashSet<String> fechas;

  public HashSet<String> getFechas() {
    return fechas;
  }

  public void setFechas(HashSet<String> fechas) {
    this.fechas = fechas;
  }
}
