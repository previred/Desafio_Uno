package cl.previred.prueba.tecnica.services;

import org.springframework.http.ResponseEntity;

import cl.previred.prueba.tecnica.rest.response.PeriodoResponse;
/**
 * 
 * @author jgajardo
 *
 */
public interface GetPeriodosService {
	/**
	 * 
	 * @return
	 */
	public ResponseEntity<PeriodoResponse> getPeriodos();
}
