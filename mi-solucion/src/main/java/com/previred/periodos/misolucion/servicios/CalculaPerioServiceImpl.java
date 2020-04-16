/**
 * 
 */
package com.previred.periodos.misolucion.servicios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.previred.periodos.misolucion.dominio.Periodo;

/**
 * @author Leonardo Silva Bustos
 *
 */
@Service
public class CalculaPerioServiceImpl implements CalculaPeriodoService{

	private static final Logger LOGGER = LoggerFactory.getLogger(CalculaPerioServiceImpl.class);
	
	@Override
	public List<LocalDate> fechasFaltantes(Periodo periodo) {
		List<LocalDate> fechasFaltantes = new ArrayList<LocalDate>();
		boolean seguir = true;
		LocalDate temporal = periodo.getFechaCreacion();
		LOGGER.info("Fecha inicio: {}  - Fecha Fin: {}", periodo.getFechaCreacion(), periodo.getFechaFin());
		while(seguir) {
			if(!periodo.getFechas().contains(temporal)) {
				fechasFaltantes.add(temporal);
			}
			temporal = temporal.plusMonths(1);
			seguir = temporal.isBefore(periodo.getFechaFin());
		}
		return fechasFaltantes;
	}
	
	
}
