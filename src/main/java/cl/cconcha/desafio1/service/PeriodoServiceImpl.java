package cl.cconcha.desafio1.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cl.cconcha.desafio1.domain.Periodo;
import cl.cconcha.desafio1.factory.PeriodoFactory;

@Service
public class PeriodoServiceImpl implements PeriodoService{

	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${ggd-periodo.url}")
	private String gddUrl;
	
	@Override
	public Periodo fillPeriodos() {
		Periodo periodoOriginal = restTemplate.getForObject(gddUrl, Periodo.class);
		//obtiene una copia del periodo original
		Periodo periodoCopia = PeriodoFactory.getInstance(periodoOriginal);
		//metodo recursivo que obtiene las fechas faltantes
		periodoOriginal.setFechasFaltantes(getMissingDates(periodoCopia,0));
		return periodoOriginal;
	}
	private List<LocalDate> getMissingDates(Periodo periodo,Integer indiceFecha){
		//valida que la fecha de creacion no sea mayor a la final
		if(!periodo.getFechaCreacion().isAfter(periodo.getFechaFin())) {
			//si la fecha de creacion es igual a una fecha generada aumenta el mes
			if(indiceFecha < periodo.getFechas().size()  && periodo.getFechaCreacion().isEqual(periodo.getFechas().get(indiceFecha))) {
				indiceFecha++;
				periodo.setFechaCreacion(periodo.getFechaCreacion().plusMonths(1L));
				return getMissingDates(periodo,indiceFecha);
			}
			periodo.getFechasFaltantes().add(periodo.getFechaCreacion());
			periodo.setFechaCreacion(periodo.getFechaCreacion().plusMonths(1L));
			return getMissingDates(periodo,indiceFecha);
		}else {
			//salida de la recursividad
			return periodo.getFechasFaltantes();
		}
		
	}
}
