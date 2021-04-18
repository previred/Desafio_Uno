package cl.previred.desafio.uno.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import cl.previred.desafio.uno.constants.DesafioUnoConstants;
import cl.previred.desafio.uno.dto.Periodo;

@FeignClient(name = "GDD", url = DesafioUnoConstants.PERIODO_SERVICE_URL)
public interface PeriodoRestClient {

	@GetMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Periodo> periodos();

}
