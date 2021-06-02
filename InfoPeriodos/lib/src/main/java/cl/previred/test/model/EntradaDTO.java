package cl.previred.test.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntradaDTO {

	private Integer id;
	private LocalDate fechaCreacion;
	private LocalDate fechaFin;
	private List<LocalDate> fechas;


	public static EntradaDTO getEjemplo() {

		EntradaDTO dto = new EntradaDTO();
		
		dto.setId(6);
		dto.setFechaCreacion(LocalDate.of(1968, 8 ,1));
		dto.setFechaFin(LocalDate.of(1971, 6 ,1));
		dto.setFechas(new ArrayList<LocalDate>());
		dto.getFechas().add(LocalDate.of(1969, 3 , 1));
		dto.getFechas().add(LocalDate.of(1969, 5 , 1));
		dto.getFechas().add(LocalDate.of(1969, 9 , 1));
		dto.getFechas().add(LocalDate.of(1971, 5 , 1));
			
		return dto;
	}
}
