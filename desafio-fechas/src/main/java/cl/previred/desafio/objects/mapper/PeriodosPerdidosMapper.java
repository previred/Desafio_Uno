package cl.previred.desafio.objects.mapper;

import java.time.LocalDate;
import java.util.List;

import cl.previred.desafio.client.model.Periodo;
import cl.previred.desafio.objects.ResponseObject;

public class PeriodosPerdidosMapper {
	
	private PeriodosPerdidosMapper() {
	    throw new IllegalStateException("mapper class");
	  }

	
	
	public static ResponseObject mapperPeriodos(Periodo periodo, List<LocalDate> fechasFaltantes) {		
		ResponseObject response =  new ResponseObject();		
		response.setId(periodo.getId());
		response.setFechaFin(periodo.getFechaFin());
		response.setFechaCreacion(periodo.getFechaCreacion());
		response.setFechas(periodo.getFechas());
		response.setFechasFaltantes(fechasFaltantes);
		return response;		
	}

}
