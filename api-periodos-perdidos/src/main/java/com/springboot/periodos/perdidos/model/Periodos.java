package com.springboot.periodos.perdidos.model;

import java.time.LocalDate;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author crist
 *
 */

public class Periodos {

	@ApiModelProperty(notes = "id de respuesta", name = "id")
	private Long id = null;

	@ApiModelProperty(notes = "fecha desde, del periodo de busqueda de fechas", name = "fechaCreacion")
	private LocalDate fechaCreacion = null;

	@ApiModelProperty(notes = "fecha hasta, del periodo de busqueda de fechas", name = "fechafin")
	private LocalDate fechaFin = null;

	@ApiModelProperty(notes = "lista de fechas entregadas por el servicio", name = "fechas")
	private List<LocalDate> fechas = null;

	/**
	 * constructor
	 */
	public Periodos() {
	}

	/**
	 * constructor
	 * 
	 * @param id
	 * @param fechaCreacion
	 * @param fechaFin
	 * @param fechas
	 */
	public Periodos(Long id, LocalDate fechaCreacion, LocalDate fechaFin, List<LocalDate> fechas) {
		super();
		this.id = id;
		this.fechaCreacion = fechaCreacion;
		this.fechaFin = fechaFin;
		this.fechas = fechas;
	}

	/**
	 * 
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 
	 * @return
	 */
	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * 
	 * @param fechaCreacion
	 */
	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	/**
	 * 
	 * @return
	 */
	public LocalDate getFechaFin() {
		return fechaFin;
	}

	/**
	 * 
	 * @param fechaFin
	 */
	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * 
	 * @return
	 */
	public List<LocalDate> getFechas() {
		return fechas;
	}

	/**
	 * 
	 * @param fechas
	 */
	public void setFechas(List<LocalDate> fechas) {
		this.fechas = fechas;
	}

}
