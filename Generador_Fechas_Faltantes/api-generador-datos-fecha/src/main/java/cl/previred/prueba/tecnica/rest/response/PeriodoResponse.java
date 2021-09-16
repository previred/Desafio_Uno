package cl.previred.prueba.tecnica.rest.response;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@ApiModel
public class PeriodoResponse {
	@JsonProperty("id")
	@ApiModelProperty(notes = "id de fechas")
	private Long id;

	@JsonProperty("fechaCreacion")
	@ApiModelProperty(notes = "Fecha creacion de periodo")
	private LocalDate fechaCreacion;

	@JsonProperty("fechaFin")
	@ApiModelProperty(notes = "Fecha fin de periodo")
	private LocalDate fechaFin;

	@JsonProperty("fechas")
	@ApiModelProperty(notes = "Lista generada por API")
	private List<LocalDate> listaGenerada;

	@JsonProperty("fechasFaltantes")
	@ApiModelProperty(notes = "fechas Faltantes", value = "")
	private List<LocalDate> fechasFaltantes;
}
