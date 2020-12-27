package com.devru.desafiouno.dto;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Periodo Model")
public class Periodo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7269280299344433288L;
	
    @ApiModelProperty(notes = "id del perido")
    private int id;
    
    @ApiModelProperty(notes = "fecha creacion del perido")
    private String fechaCreacion;
    
    @ApiModelProperty(notes = "fecha fin del perido")
    private String fechaFin;
    
    @ApiModelProperty(notes = "Lista de fecha por GDD")
    private List<String> fechas;
    
    @ApiModelProperty(notes = "Lista de fecha faltantes (calculadas)")
    private List<String> fechasFaltantes;
    
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the fechaCreacion
	 */
	public String getFechaCreacion() {
		return fechaCreacion;
	}
	/**
	 * @param fechaCreacion the fechaCreacion to set
	 */
	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	/**
	 * @return the fechaFin
	 */
	public String getFechaFin() {
		return fechaFin;
	}
	/**
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	/**
	 * @return the fechas
	 */
	public List<String> getFechas() {
		return fechas;
	}
	/**
	 * @param fechas the fechas to set
	 */
	public void setFechas(List<String> fechas) {
		this.fechas = fechas;
	}
	/**
	 * @return the fechasFaltantes
	 */
	public List<String> getFechasFaltantes() {
		return fechasFaltantes;
	}
	/**
	 * @param fechasFaltantes the fechasFaltantes to set
	 */
	public void setFechasFaltantes(List<String> fechasFaltantes) {
		this.fechasFaltantes = fechasFaltantes;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{\"id\":");
		builder.append(id);
		builder.append(", \"fechaCreacion\":\"");
		builder.append(fechaCreacion);
		builder.append("\", \"fechaFin\":\"");
		builder.append(fechaFin);
		builder.append("\", \"fechas\":");
		builder.append(fechas);
		builder.append(", \"fechasFaltantes\":");
		builder.append(fechasFaltantes);
		builder.append("}");
		return builder.toString();
	}
    
    
}
