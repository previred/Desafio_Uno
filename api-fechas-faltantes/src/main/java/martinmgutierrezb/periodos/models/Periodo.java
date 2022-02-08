package martinmgutierrezb.periodos.models;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * Periodo
 */
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
  @Valid
  private List<LocalDate> fechasFaltantes = null;

  public List<LocalDate> getFechasFaltantes() {
    return fechasFaltantes;
  }

  public void setFechasFaltantes(List<LocalDate> fechasFaltantes) {
    this.fechasFaltantes = fechasFaltantes;
  }


  public Periodo id(Long id) {
    this.id = id;
    return this;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public List<LocalDate> getFechas() {
    return fechas;
  }

  public void setFechas(List<LocalDate> fechas) {
    this.fechas = fechas;
  }

  @Override
  public boolean equals(Object o) {
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
        Objects.equals(this.fechas, periodo.fechas)&&
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
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

