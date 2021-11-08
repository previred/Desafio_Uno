package cl.previred.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import cl.previred.application.service.IPeriodosPerdidosService;
import cl.previred.infrastructurecross.application.PeriodosPerdidosException;

@CrossOrigin(origins = "*", methods= {RequestMethod.GET})
@RequestMapping("PeriodosPerdidos")
@RestController
public class PeriodosPerdidosController {
	
	@Autowired
	private IPeriodosPerdidosService periodosPerdidosService;
	
	@GetMapping
	public ResponseEntity<Object> getPeriodosPerdidos(){
		try {
			return new ResponseEntity<>(periodosPerdidosService.getPeriodosPerdidos(),HttpStatus.OK);
		} catch (PeriodosPerdidosException e) {
			return new ResponseEntity<>(PeriodosPerdidosException.generarErrorDto(e), HttpStatus.valueOf(e.getHttpCode()));
		} catch (Exception e) {	
			return new ResponseEntity<>(PeriodosPerdidosException.generarErrorNoControladoDto(e), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
