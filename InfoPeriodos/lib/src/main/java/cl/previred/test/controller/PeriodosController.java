package cl.previred.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import cl.previred.test.business.PeriodosBusiness;
import cl.previred.test.model.SalidaDTO;

public class PeriodosController implements IPeriodosController{

	@Autowired
	private PeriodosBusiness periodosBusiness;
	
	
	
	public ResponseEntity<SalidaDTO> obtenerPeriodosFaltantes(){
		
		SalidaDTO ret = periodosBusiness.procesar();
		
		return new ResponseEntity<SalidaDTO>(ret, HttpStatus.OK);
		
		
	}
	
	
	
}
