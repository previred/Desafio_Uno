package cl.previred.ms.periodos.services.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.previred.ms.periodos.dtos.SalidaAPIDTO;
import cl.previred.ms.periodos.dtos.SalidaPeriodosDTO;
import cl.previred.ms.periodos.exceptions.PreviredException;
import cl.previred.ms.periodos.repositories.PeriodosPerdidosRepository;
import cl.previred.ms.periodos.services.PeriodosPerdidosService;


@Service("PeriodosPerdidosService")
public class PeriodosPerdidosServiceImpl implements PeriodosPerdidosService {

    private static final Logger logger = LoggerFactory.getLogger(PeriodosPerdidosRepository.class);

    private final PeriodosPerdidosRepository periodosPerdidosRepository;

    @Autowired
    public PeriodosPerdidosServiceImpl(final PeriodosPerdidosRepository periodosPerdidosRepository) {
        this.periodosPerdidosRepository = periodosPerdidosRepository;
    }
    
	@Override
	public SalidaPeriodosDTO periodosPerdidos() throws PreviredException {
		SalidaAPIDTO Salida =  periodosPerdidosRepository.periodosPerdidos();
		
		logger.info("Salida exitosa APi con ID = " + Salida.getId());
		
		List<String> Periodos = Salida.getFechas();
		List<String> PeriodosFaltantes = new ArrayList<>();
		try {
			SimpleDateFormat Format = new SimpleDateFormat("yyyy-MM-dd");
			
			Date FechaInicio = Format.parse(Salida.getFechaCreacion());
			Date FechaTermino = Format.parse(Salida.getFechaFin());
			
			
			for(Date FechaTMP = FechaInicio;FechaTMP.compareTo(FechaTermino)!=0;
									FechaTMP = DateUtils.addMonths(FechaTMP, 1)) {
				
				if(!Periodos.contains(Format.format(FechaTMP))) {
					PeriodosFaltantes.add(Format.format(FechaTMP));
				}
			}

			logger.info("Fin Proceso Calculo Periodos Faltantes ");
			
			return new SalidaPeriodosDTO(Salida.getId(),Salida.getFechaCreacion(),
							Salida.getFechaFin(),Salida.getFechas(),PeriodosFaltantes);
			
		} catch (ParseException e) {
			logger.error("Error durante el Perseo de Fechas en Calculo de periodos Faltantes",e);
			throw new PreviredException("Error Interno durante calculo de periodos Faltantes ", 500, 950);
		} catch (Exception e) {
			logger.error("Error durante el Calculo de periodos Faltantes",e);
			throw new PreviredException("Error Interno durante calculo de periodos Faltantes ", 500, 900);
		}
	}



}
