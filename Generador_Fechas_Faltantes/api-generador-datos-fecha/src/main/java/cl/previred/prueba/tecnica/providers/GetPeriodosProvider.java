package cl.previred.prueba.tecnica.providers;

import java.io.IOException;

import cl.previred.prueba.tecnica.entities.dto.PeriodosDTO;

/**
 * 
 * @author jgajardo
 *
 */
public interface GetPeriodosProvider {
	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	public PeriodosDTO getPeriods() throws IOException;
}
