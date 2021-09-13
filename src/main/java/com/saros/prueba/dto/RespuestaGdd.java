package com.saros.prueba.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;


/**
 * @author saros
 *
 */

@Setter
@Getter
public class RespuestaGdd{

	private Long id;
	private String fechaCreacion;
	private String fechaFin;
	private List<LocalDate> fechas;
	
}
