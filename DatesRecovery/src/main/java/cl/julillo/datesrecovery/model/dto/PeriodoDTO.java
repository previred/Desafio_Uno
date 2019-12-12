package cl.julillo.datesrecovery.model.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class PeriodoDTO {

	@ApiModelProperty(example = "69")
	private Integer id;
	@ApiModelProperty(example = "1969-03-01")
	private LocalDate fechaCreacion;
	@ApiModelProperty(example = "1970-01-01")
	private LocalDate fechaFin;
	@ApiModelProperty(example = "['1969-03-01', '1969-05-01', '1969-09-01', '1970-01-01']")
	private List<LocalDate> fechas;
	@ApiModelProperty(example = "['1969-04-01', '1969-06-01, '1969-07-01', '1969-08-01', '1969-10-01', '1969-11-01', '1969-12-01']")
	private List<LocalDate> fechasFaltantes;

}
