package cl.previred.desafio.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cl.previred.desafio.client.model.Periodo;
import cl.previred.desafio.config.FeignConfig;

@FeignClient(value="gddFeignClient", url="http://localhost:9191", configuration = FeignConfig.class)
public interface GDDFeignClient {
	
	@RequestMapping(value = "/periodos/api",  produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<Periodo> periodos(@RequestHeader("Accept") String accept);

}
