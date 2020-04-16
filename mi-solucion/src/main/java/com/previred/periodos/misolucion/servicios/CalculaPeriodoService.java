/**
 * 
 */
package com.previred.periodos.misolucion.servicios;

import java.time.LocalDate;
import java.util.List;

import com.previred.periodos.misolucion.dominio.Periodo;

/**
 * @author Leonardo Silva Bustos
 *
 */
public interface CalculaPeriodoService {

	List<LocalDate> fechasFaltantes(Periodo periodo);
	
}
