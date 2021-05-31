package cl.previred.desafio.uno.nivel3.api.service.impl;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import cl.previred.desafio.uno.nivel3.api.dto.DesafioUnoFechasDTO;
import cl.previred.desafio.uno.nivel3.api.exceptions.DesafioUnoInformedException;
import cl.previred.desafio.uno.nivel3.api.service.OneLevelCallGDDService;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class OneLevelCallGDDServiceImpl implements OneLevelCallGDDService {

	@Value("${desafio.uno.gdd.url}")
	private String baseUrl;

	@Override
	public DesafioUnoFechasDTO callGetGDD() throws DesafioUnoInformedException {
		log.info("llamando");
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>("body", headers);
		String url = baseUrl + "/api";
		log.info(url);
		RestTemplate restTemplate = new RestTemplate();
		try {
			ResponseEntity<DesafioUnoFechasDTO> gddResponse = restTemplate.exchange(url, HttpMethod.GET, entity,
					DesafioUnoFechasDTO.class);
			ObjectMapper mapper = new ObjectMapper();
			String data = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(gddResponse.getBody());
			log.info("Resultado:\n {}", data);
			return gddResponse.getBody();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		throw new DesafioUnoInformedException();
	}

}
