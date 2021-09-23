package cl.previred.desafio.service;

import cl.previred.desafio.client.model.Periodo;
import cl.previred.desafio.objects.ResponseObject;

public interface PeriodosPerdidosService {
	
	public Periodo obtenerPeriodosGDD();
	
	public ResponseObject obtenerPeriodosPerdidos(Periodo dataGDD);

}
