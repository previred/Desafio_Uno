package cl.previred.application.dto.periodosperdidos;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PeriodosPerdidosResponseDto {
	@JsonProperty("salida")
	private PeriodosPerdidosOutDto periodosPerdidosOutDto;
}
