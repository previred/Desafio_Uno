package com.previred.periodos.servicio;

import com.previred.periodos.swagger.codegen.model.Periodo;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

/**
 *
 * @author mgonzalez@previred.com
 */
@Service
public class PeriodosAgregadoService {
	Random aleatorio;

	public PeriodosAgregadoService() {
		aleatorio = new Random();
	}
	
    /**
     * Genera un Objetos periodos, los rangos de fechas van de 1980 a
     * 2019 el rango de lista de fechas en el periodo va desde 90 a 100
     *
     * @return
     */
    public Periodo getPeriodos(Periodo periodo) {
    	
        periodo.setId(Long.valueOf(aleatorio.nextInt()));

        List<LocalDate> dates = Stream.
        		iterate(periodo.getFechaCreacion(), date -> date.plusMonths(1)).
        		limit(ChronoUnit.MONTHS.between(periodo.getFechaCreacion(), periodo.getFechaFin())).
        		collect(Collectors.toList());
        
        dates.removeAll(periodo.getFechas());
        
        periodo.setFechasFaltantes(dates.stream()
                .sorted()
                .collect(Collectors.toList()));

        return periodo;
    }
}
