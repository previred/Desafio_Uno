package martinmgutierrezb.periodos.dao.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import martinmgutierrezb.periodos.dao.IPeriodoDao;
import martinmgutierrezb.periodos.models.Periodo;

@Service
public class PeriodoDaoRest implements IPeriodoDao {
    String URI;

    RestTemplate restTemplate = new RestTemplate();

    
    /**
     * Constructor del PeriodoDaoRest - Se encarga de inicializar la uri del servicio a consultar
     * @param ip IP donde esta disponible el servicio
     * @param port Puerto donde esta disponible el servicio
     */
    public PeriodoDaoRest(@Value("${server.service.periodos.ip}") String ip,
    		@Value("${server.service.periodos.port}") String port) {
		this.URI = "http://"+ip+":"+port+"/periodos/api";
	}

    /**
     * Metodo que consulta los periodos del servicio de periodos
     * @return Retorna un objeto Periodo con la fecha creacion, fecha final y las fechas determinadas
     */
	@Override
    public Periodo getPeriodos() {
        return restTemplate.getForObject(URI, Periodo.class);
    }
}
