/**
 * 
 */
package com.previred.periodos.misolucion.dominio;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

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
@ToString(of={"id","fechaCreacion","fechaFin","fechas"})
@ApiModel(description = "Estructura para la respuesta GDD  de un periodo. ")
public class Periodo {

	@ApiModelProperty(notes = "Id del periodo")
	private Long id;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@ApiModelProperty(notes = "Fecha de inicio del periodo")
	private LocalDate fechaCreacion;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@ApiModelProperty(notes = "Fecha de fin del periodo")
	private LocalDate fechaFin;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@ApiModelProperty(notes = "Listado de fechas")
	private List<LocalDate> fechas;
	
}
