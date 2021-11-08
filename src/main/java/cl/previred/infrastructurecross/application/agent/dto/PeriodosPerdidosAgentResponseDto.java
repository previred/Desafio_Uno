package cl.previred.infrastructurecross.application.agent.dto;

import java.time.LocalDate;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PeriodosPerdidosAgentResponseDto {
	private Long id;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) 
	private LocalDate fechaCreacion;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) 
	private LocalDate fechaFin;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) 
	private List<LocalDate> fechas;
}
