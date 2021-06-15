package cl.pabloromero.model;

import java.util.Date;
import java.util.List;

import groovy.transform.ToString;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@ToString
public class Periodos {

	private int id;

	private Date fechaCreacion;
	
	private Date fechaFin;
	
	private List<Date> fechas;
	
}
