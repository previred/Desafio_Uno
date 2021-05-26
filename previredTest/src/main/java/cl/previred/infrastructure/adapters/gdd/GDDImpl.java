package cl.previred.infrastructure.adapters.gdd;


import org.springframework.stereotype.Component;

import cl.previred.domain.error.InternalBussinesException;
import cl.previred.domain.model.PeriodoResult;
import cl.previred.domain.port.GDDBackend;
import cl.previred.infrastructure.adapters.gdd.client.GDDClient;


/**
 * @author wmunoz
 *
 */
@Component
public class GDDImpl implements GDDBackend {

	private final GDDClient gddClient;
	
	public GDDImpl(GDDClient gddClient) {
		this.gddClient = gddClient;
	}
	
	@Override
	public PeriodoResult getRandomData() throws InternalBussinesException {	
		return gddClient.getRandomData();
	}
}
