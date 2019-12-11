package com.previred.periodos.servicio;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.previred.periodos.swagger.codegen.model.Periodo;
import com.previred.periodos.swagger.codegen.model.PeriodoFaltantes;

/**
 *
 * @author mc.record1@gmail.com
 */
@Service
public class PeriodosService {
	
	private static final Logger logger = Logger.getLogger(PeriodosService.class);

    /**
     * Genera un Objetos periodos, los rangos de fechas van de 1980 a
     * 2019 el rango de lista de fechas en el periodo va desde 90 a 100
     *
     * @return
     */
    public PeriodoFaltantes getPeriodos(Periodo periodo) {
    	
    	logger.info("getPeriodos ::: INIT");
    	
    	PeriodoFaltantes periodoFalt = new PeriodoFaltantes();
    	
    	if(periodo != null) {
        	periodoFalt.setId(periodo.getId());
        	periodoFalt.setFechaCreacion(periodo.getFechaCreacion());
        	periodoFalt.setFechaFin(periodo.getFechaCreacion());
        	
        	logger.info("getPeriodos");
        	logger.info("Id: "+periodoFalt.getId());
        	logger.info("FechaCreacion: "+periodoFalt.getFechaCreacion());
        	logger.info("FechaFin: "+periodoFalt.getFechaFin());
        	
        	int mesIni = periodo.getFechaCreacion().getMonth().getValue();
        	int anoIni = periodo.getFechaCreacion().getYear();
        	
        	int mesFin = periodo.getFechaFin().getMonthValue();
        	int anoFin = periodo.getFechaFin().getYear();
        	
        	int ite = 0;
        	int mesIniCont = 0;
        	int mesFinCont = 0;
        	int cantPeriodos = 0;
        	
        	if(anoIni < anoFin) {
        		ite = anoFin - anoIni;
        	}
        	
        	if(mesIni < 12) {
        		mesIniCont = 12 - mesIni;
        	}
        	
        	if(mesFin < 12) {
        		mesFinCont = 12 - mesFin;
        	}
        	
        	if(mesIniCont != 0) {
        		ite = ite - 1;
        	}
        	
        	if(mesFinCont != 0) {
        		ite = ite - 1;
        	}
        	
        	cantPeriodos = mesIniCont + mesFinCont;
        	
        	ite = ite * 12;
        	
        	if(cantPeriodos > 0) {
        		ite = ite + cantPeriodos;
        	}
        	
            Set<LocalDate> fechas = new HashSet<LocalDate>();
            while (fechas.size() <= cantPeriodos && anoIni < anoFin) {
            	
            	if(mesIni < 12) {
            		LocalDate fecha = LocalDate.of(anoIni, mesIni, 1);
            		if(!periodo.getFechas().contains(fecha)) {
            			fechas.add(fecha);
            		}
            		mesIni++;
            	}else {
            		LocalDate fecha = LocalDate.of(anoIni, mesIni, 1);
            		if(!periodo.getFechas().contains(fecha)) {
            			fechas.add(fecha);
            		}
            		mesIni = 1;
            		anoIni++;
            	}
            }
            
            periodoFalt.setFechasFaltantes(fechas.stream()
                    .sorted()
                    .collect(Collectors.toList()));
    	}
    	
        return periodoFalt;
    }
}
