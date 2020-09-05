package cl.jose.huenul.periodos.perdidos.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import cl.jose.huenul.periodos.perdidos.app.domain.PeriodosApiResponse;

@Service
public class PeriodosApiServiceImpl implements PeriodosApiService {

	private static final Logger LOG = LoggerFactory.getLogger(PeriodosApiServiceImpl.class);

	@Value("${url.periodos.api.service}")
	private String urlPeriodosApi;

	@Override
	public PeriodosApiResponse obtenerPeriodosAleatorios() {
		PeriodosApiResponse periodosDto = new PeriodosApiResponse();
		try {
			ResponseEntity<PeriodosApiResponse> responsePeriodosApi = new RestTemplate().getForEntity(urlPeriodosApi,
					PeriodosApiResponse.class);
			periodosDto = responsePeriodosApi.getBody();
		} catch (RestClientException e) {
			LOG.error("Error al intentar obtener informacion del servicio PeriodosApi");
			LOG.error(e.getMessage());
		}
		return periodosDto;
	}

}
