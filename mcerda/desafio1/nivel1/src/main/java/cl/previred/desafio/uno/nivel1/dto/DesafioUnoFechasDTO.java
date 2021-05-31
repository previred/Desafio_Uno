package cl.previred.desafio.uno.nivel1.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import cl.previred.desafio.uno.nivel1.config.DesafioUnoConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DesafioUnoFechasDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@JsonDeserialize(using = LocalDateDeserializer.class)  
	@JsonSerialize(using = LocalDateSerializer.class) 
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DesafioUnoConstants.GENERAL_DATE_FORMAT)
	private LocalDate fechaCreacion;
	
	@JsonDeserialize(using = LocalDateDeserializer.class)  
	@JsonSerialize(using = LocalDateSerializer.class) 
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DesafioUnoConstants.GENERAL_DATE_FORMAT)
	private LocalDate fechaFin;
	
	private List<String> fechas;

}
