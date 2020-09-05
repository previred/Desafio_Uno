package cl.jose.huenul.periodos.perdidos.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.jose.huenul.periodos.perdidos.app.domain.PeriodosApiResponse;
import cl.jose.huenul.periodos.perdidos.app.dto.PeriodosPerdidosDTO;
import cl.jose.huenul.periodos.perdidos.app.service.PeriodosApiService;
import cl.jose.huenul.periodos.perdidos.app.util.PeriodosPerdidosUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/periodos-perdidos")
public class PeriodosPerdidosController {
	
	private static final Logger LOG = LoggerFactory.getLogger(PeriodosPerdidosController.class);
	
	@Autowired
	private PeriodosApiService periodosApiService;
	@Autowired
	private PeriodosPerdidosUtil periodosPerdidosUtil;

	@GetMapping("/obtener-periodos-perdidos")
	@ApiOperation(value = "Obtener el listado de periodos perdidos en base al servicio de PeriodosApi")
	@ApiResponses(value = {@ApiResponse(code = 200,
		    message = "Ok", response = PeriodosPerdidosDTO.class), @ApiResponse(code = 204,
		    message = "No Content - Servicio se ejecuta correctamente, pero pudo haber un problema en llamar al servicio PeriodosApi", response = PeriodosPerdidosDTO.class)})
	private ResponseEntity<PeriodosPerdidosDTO> obtenerPeriodosPerdidos(){
		LOG.info("Inicio - obtenerPeriodosPerdidos");
		ResponseEntity<PeriodosPerdidosDTO> response = ResponseEntity.noContent().build();
		try {
			PeriodosApiResponse periodosResponse = periodosApiService.obtenerPeriodosAleatorios();
			if(periodosResponse.getFechas() != null && periodosResponse.getFechas().size()>0) {
				List<String> periodosPerdidosList = periodosPerdidosUtil.obtenerPeriodosPerdidos(periodosResponse.getFechas());
				PeriodosPerdidosDTO periodosPerdidosDto = periodosPerdidosUtil.fillPeriodosPerdidosDto(periodosResponse, periodosPerdidosList); 
				response = ResponseEntity.ok().body(periodosPerdidosDto);
			}
		}catch(Exception e) {
			response = ResponseEntity.noContent().build();
		}
		
		LOG.info("Fin - obtenerPeriodosPerdidos");
		return response;
		
	}
}
