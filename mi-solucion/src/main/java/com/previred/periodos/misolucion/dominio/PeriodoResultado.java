/**
 * 
 */
package com.previred.periodos.misolucion.dominio;

import java.time.LocalDate;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Leonardo Silva Bustos
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(description = "Estructura para la respuesta de un periodo con las fechas faltantes.")
public class PeriodoResultado extends Periodo{

	@ApiModelProperty(notes = "Listado de fechas faltantes")
	private List<LocalDate> fechasFaltantes;
	
}
