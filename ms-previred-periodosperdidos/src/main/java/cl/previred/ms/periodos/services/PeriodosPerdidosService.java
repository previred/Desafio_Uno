package cl.previred.ms.periodos.services;

import cl.previred.ms.periodos.dtos.SalidaPeriodosDTO;
import cl.previred.ms.periodos.exceptions.PreviredException;

public interface PeriodosPerdidosService {

	SalidaPeriodosDTO periodosPerdidos() throws PreviredException;

}
