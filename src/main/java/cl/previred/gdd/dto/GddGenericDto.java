package cl.previred.gdd.dto;

import io.swagger.annotations.ApiModelProperty;

public abstract class GddGenericDto {

  private Integer id;

  @ApiModelProperty(notes = "Este campo es una fecha en formato YYYY-MM-dd que indica el inicio del período a calcular", example = "\"2017-12-30\"")
  private String fechaCreacion;

  @ApiModelProperty(notes = "Este campo es una fecha en formato YYYY-MM-dd que indica el fin del período a calcular", example = "\"2020-01-15\"")
  private String fechaFin;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getFechaCreacion() {
    return fechaCreacion;
  }

  public void setFechaCreacion(String fechaCreacion) {
    this.fechaCreacion = fechaCreacion;
  }

  public String getFechaFin() {
    return fechaFin;
  }

  public void setFechaFin(String fechaFin) {
    this.fechaFin = fechaFin;
  }
}
