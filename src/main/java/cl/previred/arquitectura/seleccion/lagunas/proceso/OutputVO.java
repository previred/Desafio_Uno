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
 * Clase de tipo Value Object para contener los datos resultantes del proceso
 * @author Juan Villablanca
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"id",
"fechaCreacion",
"fechaFin",
"fechasFaltantes"
})
public class OutputVO implements Serializable{

	@JsonIgnore
	private static final long serialVersionUID = -7491213403421357846L;

	@JsonProperty("id")
	private Integer id;
	
	@JsonProperty("fechaCreacion")
	private LocalDate  fechaCreacion;
	
	@JsonProperty("fechaFin")
	private LocalDate  fechaFin;
	
	@JsonProperty("fechasFaltantes")
	private List<LocalDate> fechasFaltantes;
	
	
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
	
	@JsonProperty("fechasFaltantes")
	@JsonSerialize(contentUsing = LocalDateSerializer.class)
	public List<LocalDate> getFechasFaltantes() {
		return fechasFaltantes;
	}
	
	@JsonProperty("fechasFaltantes")
	@JsonDeserialize(contentUsing= LocalDateDeserializer.class)
	public void setFechasFaltantes(List<LocalDate> fechasFaltantes) {
		this.fechasFaltantes = fechasFaltantes;
	}
	
	
	
	
}
