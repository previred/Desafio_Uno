package cl.previred.ms.periodos.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class SalidaPeriodosDTO {

	@NonNull
	private Integer id;
	@NonNull
	private String fechaCreacion;
	@NonNull
	private String fechaFin;
	private List<String> fechas;
	private List<String> fechasFaltantes;
}
