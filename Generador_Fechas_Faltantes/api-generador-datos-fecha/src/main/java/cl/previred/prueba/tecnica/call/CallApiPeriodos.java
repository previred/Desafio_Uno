package cl.previred.prueba.tecnica.call;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import cl.previred.prueba.tecnica.entities.dto.PeriodosDTO;
import cl.previred.prueba.tecnica.providers.GetPeriodosProvider;
import lombok.Setter;

@Setter
@Service
public class CallApiPeriodos extends AbstractGenericCallService<Object> implements GetPeriodosProvider {

	protected CallApiPeriodos(@Value("${endpoint.periodos.protocolo}") String protocolo,
			@Value("${endpoint.periodos.host}") String host, @Value("${endpoint.periodos.port}") String port,
			@Value("${endpoint.periodos.path}") String path) {
		super(protocolo, host, port, path);
	}

	public PeriodosDTO getPeriods() throws IOException {
		this.invokeData = new StringBuilder();
		this.currentMethod = HttpMethod.GET;
		this.invokeData.append(protocolo).append(host)
				.append(port.startsWith(":") || EMPTY_STRING.equals(port) ? EMPTY_STRING : ":").append(port)
				.append(path).toString();

		return invoke(PeriodosDTO.class);
	}
}
