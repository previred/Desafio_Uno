package cl.previred.gdd.dto;

import java.util.HashSet;

public class GddRequest extends GddGenericDto {

  /** Habitualmente se una implementación de interfaz java.util.List para listas,
   * pero se utilizará un Hashset para acelerar búsquedas en un eventual set enorme
   * de datos **/
  private HashSet<String> fechas;

  public HashSet<String> getFechas() {
    return fechas;
  }

  public void setFechas(HashSet<String> fechas) {
    this.fechas = fechas;
  }
}
