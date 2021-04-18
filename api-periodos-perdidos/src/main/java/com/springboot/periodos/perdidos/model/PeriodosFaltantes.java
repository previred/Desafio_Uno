package com.springboot.periodos.perdidos.model;

import java.time.LocalDate;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author crist
 *
 */

public class PeriodosFaltantes {

	@ApiModelProperty(notes = "id de respuesta", name = "id")
	private Long id = null;

	@ApiModelProperty(notes = "fecha desde, del periodo de busqueda de fechas", name = "fechaCreacion")
	private LocalDate fechaCreacion = null;

	@ApiModelProperty(notes = "fecha hasta, del periodo de busqueda de fechas", name = "fechafin")
	private LocalDate fechaFin = null;

	@ApiModelProperty(notes = "lista de fechas entregadas por el servicio", name = "fechas")
	private List<LocalDate> fechas = null;

	@ApiModelProperty(notes = "lista de fechas faltantes", name = "fechasFaltantes")
	private List<LocalDate> fechasFaltantes = null;

	/**
	 * constructor
	 */
	public PeriodosFaltantes() {

	}

	/**
	 * constructor
	 * @param id
	 * @param fechaCreacion
	 * @param fechaFin
	 * @param fechas
	 * @param fechasFaltantes
	 */
	public PeriodosFaltantes(Long id, LocalDate fechaCreacion, LocalDate fechaFin, List<LocalDate> fechas,
			List<LocalDate> fechasFaltantes) {
		this.id = id;
		this.fechaCreacion = fechaCreacion;
		this.fechaFin = fechaFin;
		this.fechas = fechas;
		this.fechasFaltantes = fechasFaltantes;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the fechaCreacion
	 */
	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * @param fechaCreacion the fechaCreacion to set
	 */
	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	/**
	 * @return the fechaFin
	 */
	public LocalDate getFechaFin() {
		return fechaFin;
	}

	/**
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * @return the fechas
	 */
	public List<LocalDate> getFechas() {
		return fechas;
	}

	/**
	 * @param fechas the fechas to set
	 */
	public void setFechas(List<LocalDate> fechas) {
		this.fechas = fechas;
	}

	/**
	 * @return the fechasFaltantes
	 */
	public List<LocalDate> getFechasFaltantes() {
		return fechasFaltantes;
	}

	/**
	 * @param fechasFaltantes the fechasFaltantes to set
	 */
	public void setFechasFaltantes(List<LocalDate> fechasFaltantes) {
		this.fechasFaltantes = fechasFaltantes;
	}
	
	

}
