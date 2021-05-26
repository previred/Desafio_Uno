package cl.previred.infrastructure.adapters.gdd.model;

import java.time.LocalDate;
import java.util.List;
import javax.validation.Valid;
import org.springframework.validation.annotation.Validated;
import cl.previred.domain.model.PeriodoResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Periodo
 */
@Validated
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Periodo {
	
  private Long id = null;

  private LocalDate fechaCreacion = null;

  private LocalDate fechaFin = null;

  @Valid
  private List<LocalDate> fechas = null;

  // Metodo para pasar a objeto de la capa de dominio
  public PeriodoResult toDomain(List<LocalDate> fechasFaltantes) {
     
      return PeriodoResult
		.builder()
		.id(this.id)
		.fechaCreacion(this.fechaCreacion)
		.fechaFin(this.fechaFin)
		.fechasRec(this.fechas)
		.fechasFal(fechasFaltantes)
		.build();
  }



}