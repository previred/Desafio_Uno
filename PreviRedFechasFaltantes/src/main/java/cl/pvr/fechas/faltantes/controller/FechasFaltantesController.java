package cl.pvr.fechas.faltantes.controller;


import cl.pvr.fechas.faltantes.model.FechasFaltanteRespuesta;
import cl.pvr.fechas.faltantes.model.Periodo;
import cl.pvr.fechas.faltantes.util.ProcesamientoFaltantes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class FechasFaltantesController {
	
	Logger logger = LoggerFactory.getLogger(FechasFaltantesController.class);

	public FechasFaltantesController() {
		super();
	}

	@GetMapping("/periodos/obtFaltantes")
	public FechasFaltanteRespuesta getFechFalt(){
		
		Periodo periodo = null;
		try {
			RestTemplate jsonModel = new RestTemplate();
			logger.info("Se Consume servicio GDD");
			periodo = jsonModel.getForObject("http://localhost:8080/periodos/api", Periodo.class);
			
		} catch (Exception e) {
			logger.error("Ha ocurrido un error al consumir el servicio GDD" + e);
		}	
		logger.info("Lo obtenido por el servicio GDD será procesado");
		return ProcesamientoFaltantes.obtenerFechasFaltantes(periodo);
	}
	
}


