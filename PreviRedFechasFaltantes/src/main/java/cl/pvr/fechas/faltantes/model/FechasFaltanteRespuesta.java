package cl.pvr.fechas.faltantes.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;

//Clase donde se maneja la respuesta del servicio (desafio1 nivel 3).	

@Validated

public class FechasFaltanteRespuesta {
	
	@JsonProperty("id")
	private Long id = null;

	@JsonProperty("fechaCreacion")
	private LocalDate fechaCreacion = null;

	@JsonProperty("fechaFin")
	private LocalDate fechaFin = null;

	@JsonProperty("fechas")
	private List<LocalDate> fechas = null;

	@JsonProperty("fechasFaltantes")
	private List<LocalDate> fechasFaltantes = null;

	public FechasFaltanteRespuesta id(Long id) {
		this.id = id;
		return this;
	}

	/**
	 * Get id
	 * 
	 * @return id
	 **/
	@ApiModelProperty(value = "")

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FechasFaltanteRespuesta fechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
		return this;
	}

	/**
	 * Get fechaCreacion
	 * 
	 * @return fechaCreacion
	 **/
	
	@ApiModelProperty(value = "")
	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public FechasFaltanteRespuesta fechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
		return this;
	}

	/**
	 * Get fechaFin
	 * 
	 * @return fechaFin
	 **/
	@ApiModelProperty(value = "")
	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	public FechasFaltanteRespuesta fechas(List<LocalDate> fechas) {
		this.fechas = fechas;
		return this;
	}

	public FechasFaltanteRespuesta addFechasItem(LocalDate fechasItem) {
		if (this.fechas == null) {
			this.fechas = new ArrayList<>();
		}
		this.fechas.add(fechasItem);
		return this;
	}

	/**
	 * Get fechas
	 * 
	 * @return fechas
	 **/
	
	@ApiModelProperty(value = "")
	public List<LocalDate> getFechas() {
		return fechas;
	}

	public void setFechas(List<LocalDate> fechas) {
		this.fechas = fechas;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		FechasFaltanteRespuesta periodo = (FechasFaltanteRespuesta) o;
		return Objects.equals(this.id, periodo.id) && Objects.equals(this.fechaCreacion, periodo.fechaCreacion)
				&& Objects.equals(this.fechaFin, periodo.fechaFin) && Objects.equals(this.fechas, periodo.fechas);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, fechaCreacion, fechaFin, fechas);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Periodo {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    fechaCreacion: ").append(toIndentedString(fechaCreacion)).append("\n");
		sb.append("    fechaFin: ").append(toIndentedString(fechaFin)).append("\n");
		sb.append("    fechas: ").append(toIndentedString(fechas)).append("\n");
	    sb.append("    fechasFaltantes: ").append(toIndentedString(fechasFaltantes)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
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

