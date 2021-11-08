package cl.previred.infrastructurecross.application.agent.service;

import java.io.IOException;
import cl.previred.infrastructurecross.application.agent.dto.PeriodosPerdidosAgentResponseDto;

public interface IGeneradorDatosAgentService {
	PeriodosPerdidosAgentResponseDto getGeneracionDatos(String url)  throws IOException;
}
