package cl.previred.desafio.uno.nivel3.api.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import cl.previred.desafio.uno.nivel3.api.config.DesafioUnoConstants;
import cl.previred.desafio.uno.nivel3.api.config.SwaggerApiDocConstants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Representa los periodos")
public class DesafioUnoFechasDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DesafioUnoConstants.GENERAL_DATE_FORMAT)
	@ApiModelProperty(notes = SwaggerApiDocConstants.MCERDA_PERIODOS_FECHAS_MODEL_FECHA_CREACION)
	private LocalDate fechaCreacion;

	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DesafioUnoConstants.GENERAL_DATE_FORMAT)
	@ApiModelProperty(notes = SwaggerApiDocConstants.MCERDA_PERIODOS_FECHAS_MODEL_FECHA_FIN)
	private LocalDate fechaFin;

	@ApiModelProperty(notes = SwaggerApiDocConstants.MCERDA_PERIODOS_FECHAS_MODEL_FECHAS)
	private List<String> fechas;

}
