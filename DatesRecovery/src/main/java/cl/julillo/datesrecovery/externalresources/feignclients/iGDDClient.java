package cl.julillo.datesrecovery.externalresources.feignclients;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import cl.julillo.datesrecovery.model.dto.PeriodoDTO;

@FeignClient(name = "gdd", url = "${previred.gdd-api.url}")
public interface iGDDClient {

	@GetMapping("/api")
	PeriodoDTO getPeriodos(@RequestHeader Map<String, String> header);

}
