package cl.fsoto1.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import cl.fsoto1.modelo.Periodo;
import cl.fsoto1.servicio.PeriodoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RequestMapping(value = "/api")
@Api(value="api", description="Consumidor de GDD")
@RestController
public class PeriodoRestController {

	//url del servicio definida en properties
	@Value("${consumer.url.service}")
	private String urlService;
	
	//inyeccion de dependencia
	@Autowired
	private PeriodoService periodoService;
	
	//api expuesta por metodo GET
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Endpoint para recibir periodos faltantes", response = Periodo.class)
	public Periodo index(){
		RestTemplate restTemplate = new RestTemplate();
        Periodo periodo = restTemplate.getForObject(urlService, Periodo.class);
        periodo.setFechasFaltantes(periodoService.periodosFaltantes(periodo.getFechaCreacion(),periodo.getFechaFin(),periodo.getFechas()));
        return(periodo);
	}
}
