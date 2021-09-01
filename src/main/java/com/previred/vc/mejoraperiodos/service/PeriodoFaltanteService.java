package com.previred.vc.mejoraperiodos.service;

import com.previred.vc.mejoraperiodos.model.Periodo;
import com.previred.vc.mejoraperiodos.model.PeriodoFaltante;

public interface PeriodoFaltanteService {

	public PeriodoFaltante getPeriodosFaltantes(Periodo periodo);
	
	public Periodo callAPIPrevired();
	
}
