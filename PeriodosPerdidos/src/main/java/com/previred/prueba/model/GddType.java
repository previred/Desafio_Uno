package com.previred.prueba.model;

import java.time.LocalDate;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Esta clase corresponde a la firma del servicio GDD
 * 
 * @author fcaballero
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GddType {
	
	private int id;
    private LocalDate fechaCreacion;		// "1968-08-01"
    private LocalDate fechaFin; 			// "1971-06-01",
    private ArrayList<LocalDate> fechas;	// ["1968-08-01", "1968-08-02", "1968-08-03",...]
	
    // Constructor vacio
    public GddType() {
		
	}
	
    // Constructor con campos
	public GddType(int id, LocalDate fechaCreacion, LocalDate fechaFin, ArrayList<LocalDate> fechas) {
		super();
		this.id = id;
		this.fechaCreacion = fechaCreacion;
		this.fechaFin = fechaFin;
		this.fechas = fechas;
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
    
	@Override
	public String toString() {
		return "{id=" + this.getId() + ", fechaCreacion=" + this.getFechaCreacion() + "}";
	}
    
}
