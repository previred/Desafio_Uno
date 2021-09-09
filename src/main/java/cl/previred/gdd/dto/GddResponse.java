package cl.previred.gdd.dto;

import java.util.List;

public class GddResponse extends GddGenericDto {

  private List<String> fechasFaltantes;

  public List<String> getFechasFaltantes() {
    return fechasFaltantes;
  }

  public void setFechasFaltantes(List<String> fechasFaltantes) {
    this.fechasFaltantes = fechasFaltantes;
  }
}
