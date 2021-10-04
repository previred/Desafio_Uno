package cl.previred.ms.periodos.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cl.previred.ms.periodos.dtos.SalidaAPIDTO;
import cl.previred.ms.periodos.exceptions.PreviredException;
import cl.previred.ms.periodos.restclient.PeriodosPerdidosClient;

@Repository()
public class PeriodosPerdidosRepository {

    private PeriodosPerdidosClient periodosPerdidosClient;
    private static final Logger logger = LoggerFactory.getLogger(PeriodosPerdidosRepository.class);

    @Autowired
    public PeriodosPerdidosRepository(final PeriodosPerdidosClient periodosPerdidosClient) {
        this.periodosPerdidosClient = periodosPerdidosClient;
    };


	public SalidaAPIDTO periodosPerdidos() throws PreviredException {
		try {
			return periodosPerdidosClient.periodos();
		}catch(Exception e) {
			logger.error("Error invocando API de Periodos",e);
			throw new PreviredException("Error invocando API de Periodos", 500, 999);
		}
	}

}
