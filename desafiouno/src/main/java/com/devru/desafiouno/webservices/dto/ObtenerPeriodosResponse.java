package com.devru.desafiouno.webservices.dto;

import java.util.List;

import com.devru.desafiouno.webservices.WebServiceResponse;

public class ObtenerPeriodosResponse implements WebServiceResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8844407811379273248L;
	
    private int id;
    private String fechaCreacion;
    private String fechaFin;
    private List<String> fechas;
    private String razon;
    private int codigoRespuesta;

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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Periodo {\"id\":");
		builder.append(id);
		builder.append(", \"fechaCreacion\":\"");
		builder.append(fechaCreacion);
		builder.append("\", \"fechaFin\":\"");
		builder.append(fechaFin);
		builder.append("\", \"fechas\":");
		builder.append(fechas);
		builder.append("}");
		return builder.toString();
	}
	
	/**
	 * @param razon the razon to set
	 */
	public void setRazon(String razon) {
		this.razon = razon;
	}
	/**
	 * @param codigoRespuesta the codigoRespuesta to set
	 */
	public void setCodigoRespuesta(int codigoRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
	}
	
	@Override
	public String getRazon() {
		return this.razon;
	}
	
	@Override
	public int getCodigoRespuesta() {
		return this.codigoRespuesta;
	}	
}
