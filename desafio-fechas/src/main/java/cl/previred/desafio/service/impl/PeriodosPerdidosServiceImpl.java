package cl.previred.desafio.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.previred.desafio.client.GDDFeignClient;
import cl.previred.desafio.client.model.Periodo;
import cl.previred.desafio.objects.ResponseObject;
import cl.previred.desafio.objects.mapper.PeriodosPerdidosMapper;
import cl.previred.desafio.service.PeriodosPerdidosService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service("periodosPerdidosService")
public class PeriodosPerdidosServiceImpl implements PeriodosPerdidosService {
	
	@Autowired
	private GDDFeignClient gddClient;

	@Override
	public Periodo obtenerPeriodosGDD() {		
		Periodo resp = null;		
		try {
			resp = gddClient.periodos("application/json").getBody();
			log.info("Respuesta servicio GDD : " + resp.toString());
		} catch (Exception e) {
			log.error("Error al obtener periodos generados desde servicio GDD : ... " + e);
		}		
		return resp;
	}

	@Override
	public ResponseObject obtenerPeriodosPerdidos(Periodo dataGDD) {			
		ResponseObject ret = null;
		try {
			List<LocalDate> fechasPerdidas = calculaPeriodosPerdidos(dataGDD.getFechaFin(), dataGDD.getFechaCreacion(), dataGDD.getFechas());		
			ret = PeriodosPerdidosMapper.mapperPeriodos(dataGDD, fechasPerdidas);
		} catch (Exception e) {
			log.error("Error al calcular periodos Perdidos..." + e);
		}
	
		return ret;
	}
	
	private List<LocalDate> calculaPeriodosPerdidos(LocalDate lastDate, LocalDate startDate, List<LocalDate> periodos){
		
		LocalDate aux = startDate;
		List<LocalDate> periodosPerdidos = new ArrayList<LocalDate>();
		
		while(aux.isBefore(lastDate)) {			
			LocalDate auxCompare = aux.plusMonths(1);			
			if(!periodos.contains(auxCompare)) {
				periodosPerdidos.add(auxCompare);
			}		
			aux = auxCompare;
		}
		
		return periodosPerdidos;		
	}

}
