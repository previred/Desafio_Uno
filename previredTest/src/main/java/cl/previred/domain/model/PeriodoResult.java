package cl.previred.domain.model;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * Periodo 	Result
 */
@Getter
@Builder
@AllArgsConstructor
public class PeriodoResult   {

  private Long id;
  private LocalDate fechaCreacion;
  private LocalDate fechaFin;
  private List<LocalDate> fechasRec;
  private List<LocalDate> fechasFal;
  
}