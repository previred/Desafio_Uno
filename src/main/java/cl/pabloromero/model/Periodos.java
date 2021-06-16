package cl.pabloromero.model;

import java.time.LocalDate;
import java.util.List;

import groovy.transform.ToString;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Periodos{

	private int id;

	private LocalDate fechaCreacion;
	
	private LocalDate fechaFin;
	
	private List<LocalDate> fechas;
	
}
