package cl.previred.gdd.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class GddResponse extends GddGenericDto {

  @ApiModelProperty(notes = "Este campo es una lista de fechas en formato YYYY-MM-dd y corresponde a las fechas faltantes en un período", example = "[\"2017-12-30\", \"2018-01-15\"]")
  private List<String> fechasFaltantes;

  public List<String> getFechasFaltantes() {
    return fechasFaltantes;
  }

  public void setFechasFaltantes(List<String> fechasFaltantes) {
    this.fechasFaltantes = fechasFaltantes;
  }
}
