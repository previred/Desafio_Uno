package com.saros.prueba.dto;

import java.time.LocalDate;

import java.util.List;

import lombok.Getter;
import lombok.Setter;


/**
 * @author saros
 *
 */

@Getter 
@Setter

public class RespuestaDto{
	
	private Long id;
	private String fechaCreacion;
	private String fechaFin;
	List<LocalDate> fechas;
	List<LocalDate> fechasFaltantes;

}
