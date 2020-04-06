package com.previred.prueba.model;

import java.time.LocalDate;
import java.util.ArrayList;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Model FechasFaltantesType")
/**
 * Esta clase corresponde a la firma del servicio expuesto por esta solución
 * @author fcaballero
 *
 */
public class FechasFaltantesType {
	@ApiModelProperty(dataType = "Integer", example = "10")
	private int id;
	
	@ApiModelProperty(example = "1968-08-01")
    private LocalDate fechaCreacion;		// "1968-08-01"
	
	@ApiModelProperty(example = "1971-06-01")
    private LocalDate fechaFin; 			// "1971-06-01",
	
	@ApiModelProperty(dataType = "[Ljava.lang.String;", example = "[\"1968-08-01\", \"1968-08-03\", \"1968-08-05\",...]")
	private ArrayList<LocalDate> fechas;	// ["1968-08-01", "1968-08-02", "1968-08-03",...]
	
	@ApiModelProperty(dataType = "[Ljava.lang.String;", example = "[\"1968-08-02\", \"1968-08-04\",...]")
	private ArrayList<LocalDate> fechasFaltantes;
    
    // Constructor vacio
    public FechasFaltantesType() {
		this.fechasFaltantes = new ArrayList<LocalDate>();
	}
	
    // Constructor con campos
	public FechasFaltantesType(int id, LocalDate fechaCreacion, LocalDate fechaFin, ArrayList<LocalDate> fechas, ArrayList<LocalDate> fechasFaltantes) {
		this.id = id;
		this.fechaCreacion = fechaCreacion;
		this.fechaFin = fechaFin;
		this.fechas = fechas;
		this.fechasFaltantes = fechasFaltantes;
	}
	
	// Getters and setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public LocalDate getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}
	public ArrayList<LocalDate> getFechas() {
		return fechas;
	}
	public void setFechas(ArrayList<LocalDate> fechas) {
		this.fechas = fechas;
	}

	public ArrayList<LocalDate> getFechasFaltantes() {
		return fechasFaltantes;
	}

	public void setFechasFaltantes(ArrayList<LocalDate> fechasFaltantes) {
		this.fechasFaltantes = fechasFaltantes;
	}
	
}
