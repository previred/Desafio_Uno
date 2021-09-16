package cl.previred.prueba.tecnica.entities.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class PeriodosDTO {
	private Long id;
	private LocalDate fechaCreacion;
	private LocalDate fechaFin;
	private List<LocalDate> fechas;
}
