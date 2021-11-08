package cl.previred.infrastructurecross.application.agent.service.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import cl.previred.infrastructurecross.application.agent.dto.PeriodosPerdidosAgentResponseDto;
import cl.previred.infrastructurecross.application.agent.service.IGeneradorDatosAgentService;

@Service
public class GeneradorDatosAgentServiceImpl implements IGeneradorDatosAgentService{

	@Override
	public PeriodosPerdidosAgentResponseDto getGeneracionDatos(String url) throws IOException {
		PeriodosPerdidosAgentResponseDto periodosPerdidosAgentResponseDto = null;
		WebTarget webTarget = ClientBuilder.newClient().target(url);
		Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON_TYPE);
		Response response = invocationBuilder.get();
		// .readEntity(PeriodosPerdidosAgentResponseDto.class)
		String dataJson = response.readEntity(String.class);
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		periodosPerdidosAgentResponseDto = mapper.readValue(dataJson, PeriodosPerdidosAgentResponseDto.class);
        return periodosPerdidosAgentResponseDto;
	}

}
