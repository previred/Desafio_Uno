package com.previred.periodos.servicio;

import com.previred.periodos.cliente.RestClientHelper;
import com.previred.periodos.swagger.codegen.model.Periodo;
import com.previred.periodos.swagger.codegen.model.PeriodoCompleto;
import com.previred.periodos.util.RangeUtils;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author hhumeres
 */
@Service
public class PeriodosCompletosService {

    public PeriodoCompleto getPeriodos() {
    	RestClientHelper client = new RestClientHelper();
    	
    	Periodo periodo = client.gePeriodo();

        PeriodoCompleto periodoCompleto = new PeriodoCompleto();
        periodoCompleto.setId(periodo.getId());
        periodoCompleto.setFechaCreacion(periodo.getFechaCreacion());
        periodoCompleto.setFechaFin(periodo.getFechaFin());
        periodoCompleto.setFechas(periodo.getFechas());
        
        List<LocalDate> fechasComplemento = 
        		RangeUtils.getComplement(periodo.getFechaCreacion(), periodo.getFechaFin(), 
        		periodo.getFechas());
        
        periodoCompleto.setFechasFaltantes(fechasComplemento);

        return periodoCompleto;
    }
    
}