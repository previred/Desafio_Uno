package cl.periodos.rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import cl.generador.business.PeriodosBC;
import cl.periodos.model.PeriodoTO;

@Path("/generadorPeriodosRest/")
public class PeriodosRest {
	
	@POST
	@Path("obtenerPeriodosFaltantes")
	@Produces(MediaType.APPLICATION_JSON)
	public PeriodoTO obtenerPeriodosFaltantes() {
		PeriodoTO periodos = new PeriodoTO();
		PeriodosBC servicioPeriodos = new PeriodosBC(); 
		
		periodos = servicioPeriodos.obtenerTotalPeriodos();
		return periodos;
	}
}
