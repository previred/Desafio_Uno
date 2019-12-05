package cl.previred.app.bean;

import java.io.Serializable;
import java.util.List;

public class PeriodoBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String fechaCreacion;
	private String fechaFin;
	private List<String> fechas;
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
	
	
}
