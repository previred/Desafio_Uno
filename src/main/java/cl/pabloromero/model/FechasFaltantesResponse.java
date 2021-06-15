package cl.pabloromero.model;

import java.util.Date;
import java.util.List;

import groovy.transform.ToString;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FechasFaltantesResponse {

	private int id;

	private Date fechaCreacion;
	
	private Date fechaFin;
	
	private List<Date> fechas;
	
	private List<Date> fechasFaltantes;
}
