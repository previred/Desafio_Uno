package cl.previred.application.service.impl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cl.previred.application.dto.periodosperdidos.PeriodosPerdidosOutDto;
import cl.previred.application.dto.periodosperdidos.PeriodosPerdidosResponseDto;
import cl.previred.application.service.IPeriodosPerdidosService;
import cl.previred.infrastructurecross.application.EnvironmentException;
import cl.previred.infrastructurecross.application.agent.dto.PeriodosPerdidosAgentResponseDto;
import cl.previred.infrastructurecross.application.agent.service.IGeneradorDatosAgentService;
import cl.previred.infrastructurecross.application.util.ObjectMapper;

@Service 
public class PeriodosPerdidosService implements IPeriodosPerdidosService{
	@Value("${url.api.periodos}")
	private String urlApiPeriodos;
	@Autowired
	private IGeneradorDatosAgentService generadorDatosAgentService;
	@Autowired
	private EnvironmentException environmentException;
	
	private PeriodosPerdidosAgentResponseDto periodosPerdidosAgentResponseDto;
	@Override
	public PeriodosPerdidosResponseDto getPeriodosPerdidos() {
		
		try {
			periodosPerdidosAgentResponseDto = generadorDatosAgentService.getGeneracionDatos(urlApiPeriodos);
		}catch(Exception e) {
			e.printStackTrace();
			environmentException.error(0);
		}
		validaGeneracionDatos(periodosPerdidosAgentResponseDto);
		
		long mesesEnMedio = ChronoUnit.MONTHS.between(periodosPerdidosAgentResponseDto.getFechaCreacion(),
				periodosPerdidosAgentResponseDto.getFechaFin()) + 1; //agregamos el ultimo registro
		List<LocalDate> periodosFaltantesList = IntStream.iterate(0, i -> i + 1)//iteracion lambda
			      .limit(mesesEnMedio)//limite del for -> todos los primeros
			      .mapToObj(i -> periodosPerdidosAgentResponseDto.getFechaCreacion().plusMonths(i))//agregamos de mes a mes siempre el dia 1
			      .filter(periodoPerdido -> periodosPerdidosAgentResponseDto.getFechas()//nos aseguramos que los que vienen en fechas no vengan en lista de salida de fechas faltantes
							.stream()
							.noneMatch(periodoEncontrado -> periodoEncontrado.isEqual(periodoPerdido)))//agregamos los valores que no se encuentran en la otra lista
			      .collect(Collectors.toList());// lo retornamos como coleccion
		
		PeriodosPerdidosResponseDto periodosPerdidosResponseDto = new PeriodosPerdidosResponseDto();
		PeriodosPerdidosOutDto periodosPerdidosOutDto = ObjectMapper.map(periodosPerdidosAgentResponseDto, PeriodosPerdidosOutDto.class);
		
		periodosPerdidosOutDto.setFechasFaltantes(periodosFaltantesList);
		periodosPerdidosResponseDto.setPeriodosPerdidosOutDto(periodosPerdidosOutDto);
		
		return periodosPerdidosResponseDto;
	}
	
	private void validaGeneracionDatos(PeriodosPerdidosAgentResponseDto periodosPerdidosAgentResponseDto) {
		if(periodosPerdidosAgentResponseDto == null) {
			environmentException.error(1);
		}
		if(periodosPerdidosAgentResponseDto != null &&
				periodosPerdidosAgentResponseDto.getFechaCreacion() == null) {
			environmentException.error(2);
		}
		if(periodosPerdidosAgentResponseDto != null &&
				periodosPerdidosAgentResponseDto.getFechaFin() == null) {
			environmentException.error(3);
		}
		if(periodosPerdidosAgentResponseDto != null &&
				periodosPerdidosAgentResponseDto.getFechas() == null) {
			periodosPerdidosAgentResponseDto.setFechas(new ArrayList<>());
			
		}
	}

}
