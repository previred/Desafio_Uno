package cl.previred.arquitectura.seleccion.lagunas.proceso;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import cl.previred.arquitectura.seleccion.lagunas.util.LocalDateDeserializer;
import cl.previred.arquitectura.seleccion.lagunas.util.LocalDateSerializer;

/**
 * Clase que representa los datos de entrada
 * @author Juan Villablanca
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"id",
"fechaCreacion",
"fechaFin",
"fechas"
})
public class InputVO implements Serializable{

	@JsonIgnore
	private static final long serialVersionUID = -7346978442817770130L;

	@JsonProperty("id")
	private Integer id;
	
	@JsonProperty("fechaCreacion")
	private LocalDate  fechaCreacion;
	
	@JsonProperty("fechaFin")
	private LocalDate  fechaFin;
	
	@JsonProperty("fechas")
	private List<LocalDate> fechas;

	@JsonProperty("id")
	public Integer getId() {
		return id;
	}
	
	@JsonProperty("id")
	public void setId(Integer id) {
		this.id = id;
	}
	
	@JsonProperty("fechaCreacion")
	@JsonSerialize(using = LocalDateSerializer.class)
	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}
	
	@JsonProperty("fechaCreacion")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	@JsonProperty("fechaFin")
	@JsonSerialize(using = LocalDateSerializer.class)
	public LocalDate getFechaFin() {
		return fechaFin;
	}
	
	@JsonProperty("fechaFin")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	@JsonProperty("fechas")
	@JsonSerialize(contentUsing = LocalDateSerializer.class)
	public List<LocalDate> getFechas() {
		return fechas;
	}
	
	@JsonProperty("fechas")
	@JsonDeserialize(contentUsing = LocalDateDeserializer.class)
	public void setFechas(List<LocalDate> fechas) {
		this.fechas = fechas;
	}
	
	
	
}
