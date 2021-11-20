package vo;

import java.util.ArrayList;
import java.util.List;

public class Fechas {
	
	/** id */
	Long id = 0L;
	
	/** fecha creacion */
	String fechaCreacion = "";
	
	/** fecha fin */
	String fechaFin = "";
	
	/** lista de fechas, usar para fechas iniciales o faltantes */ 
	List<String> fechas = new ArrayList<String>();
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
	
	
}
