package cl.previred.app.data.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "modelo de respuesta")
public class ResponseDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8196251446849300219L;
	
	@ApiModelProperty(notes = "ID identificador recibido desde la API externa", name = "id", required = false, value = "1...n")
	private long id;
	
	@ApiModelProperty(notes = "Fecha inicial recibida desde la API externa", name = "fechaCreacion", required = true, value = "2013-02-01")
	private String fechaCreacion;

	@ApiModelProperty(notes = "Fecha final recibida desde la API externa", name = "fechaFin", required = true, value = "2019-12-01")
	private String fechaFin;
	
	@ApiModelProperty(notes = "Listado de fecha recibida de la API externa", name = "fechas", required = true, value = "{2013-02-01, 2016-05-01,2013-02-01}")
	private List<String> fechas;

	@ApiModelProperty(notes = "Listado de fecha que faltan en el rango entragado", name = "fechasFaltante", required = true, value = "{2013-03-01,2016-04-01,2013-01-01}")
	private List<String> fechasFaltante;
	
	public ResponseDto() {
		
	}
	
	public ResponseDto(long id, String fechaCreacion, String fechaFin, List<String> fechas) {
		this.id = id;
		this.fechaCreacion = fechaCreacion;
		this.fechaFin = fechaFin;
		this.fechas = fechas;
		this.fechasFaltante = new ArrayList<String>(0);
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	public List<String> getFechas() {
		return fechas;
	}
	public void setFechas(List<String> fechas) {
		this.fechas = fechas;
	}
	public List<String> getFechasFaltante() {
		return fechasFaltante;
	}
	public void setFechasFaltante(List<String> fechasFaltante) {
		this.fechasFaltante = fechasFaltante;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
