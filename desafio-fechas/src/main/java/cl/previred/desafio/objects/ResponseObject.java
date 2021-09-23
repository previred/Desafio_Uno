package cl.previred.desafio.objects;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Validated
@Getter
@Setter
public class ResponseObject {
	
	  @JsonProperty("id")
	  private Long id;

	  @JsonProperty("fechaCreacion")
	  private LocalDate fechaCreacion;

	  @JsonProperty("fechaFin")
	  private LocalDate fechaFin;

	  @JsonProperty("fechas")
	  @Valid
	  private List<LocalDate> fechas;
	  
	  @JsonProperty("fechasFaltantes")
	  @Valid
	  private List<LocalDate> fechasFaltantes;
	 

}
