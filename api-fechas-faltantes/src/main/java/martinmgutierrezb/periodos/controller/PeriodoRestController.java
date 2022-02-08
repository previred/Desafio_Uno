package martinmgutierrezb.periodos.controller;

import martinmgutierrezb.periodos.models.Periodo;
import martinmgutierrezb.periodos.service.IPeriodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin({"*"})
@RestController
@RequestMapping("periodofaltante/api")
public class PeriodoRestController {

	@Autowired
	private IPeriodoService periodoService;

	/**
     * Servicio encargado de disponibilizar el objeto Periodo con sus atritubos, incluyendo las fechas 
     * faltantes que no vinieron del servicio de periodos
     * @return Retorna un objeto Periodo con la fecha creacion, fecha final, las fechas determinadas y fechas faltantes
     */
	@GetMapping
	public ResponseEntity<?> getPeriodosFaltantes(){
		Map<String, String> response = new HashMap<String, String>();
		try {
			return new ResponseEntity<Periodo>(periodoService.getPeriodosFaltantes(),HttpStatus.OK);
		} catch (RestClientException e){
			response.put("mensaje", "Error al consultar servicio de periodos, favor asegurarse de que este disponible.");
			response.put("error", e.getMessage()+": "+e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, String>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
