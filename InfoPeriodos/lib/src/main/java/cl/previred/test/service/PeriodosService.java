package cl.previred.test.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import cl.previred.test.model.EntradaDTO;
import cl.previred.test.utilidades.Util;


@Service
public class PeriodosService {

	private final Logger LOG = LogManager.getLogger(this.getClass());
	
	@Autowired
	Environment env;

	public EntradaDTO getDataPeriodos() {
		
		
		EntradaDTO entradaData = null;
		
		Long ini = System.currentTimeMillis();
		
		RestTemplate restTemplate = new RestTemplate();
		
		try {
			ResponseEntity<EntradaDTO> data = restTemplate.getForEntity(env.getProperty("servicio.gdd"), EntradaDTO.class);
		
			if(data.getStatusCode() == HttpStatus.OK) {
				return data.getBody();				
			}
			
		}catch(Exception e) {
			
			LOG.error("Ocurrio un error al intentar llamar al servicio: " + env.getProperty("servicio.gdd"));
			LOG.error(e);
						
		}finally {
			
			LOG.info("Ejecucion de la llamada al servicio GDD en " +  Util.tiempoEjecucion(ini));
		}
	
		return entradaData;
		
	}
	

		
	
	
}
