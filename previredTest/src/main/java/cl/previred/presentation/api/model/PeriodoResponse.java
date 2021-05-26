package cl.previred.presentation.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

import cl.previred.domain.model.PeriodoResult;

/**
 * Periodo Response
 * @author wmunoz
 */
@Validated
@Getter
@Setter
@Builder
@AllArgsConstructor
public class PeriodoResponse   {

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("fechaCreacion")
  private LocalDate fechaCreacion = null;

  @JsonProperty("fechaFin")
  private LocalDate fechaFin = null;

  @JsonProperty("fechas")
  @Valid
  private List<LocalDate> fechasRec = null;
  
  @JsonProperty("fechasFaltantes")
  @Valid
  private List<LocalDate> fechasFal = null;
  
  //Método que permite la conversión de objetos de dominio
  public static PeriodoResponse of(PeriodoResult periodoResult) {
      return PeriodoResponse.builder()
    			.id(periodoResult.getId())
    			.fechaCreacion(periodoResult.getFechaCreacion())
    			.fechaFin(periodoResult.getFechaFin())
    			.fechasRec(periodoResult.getFechasRec())
    			.fechasFal(periodoResult.getFechasFal())
    			.build();
      
  }
  
}