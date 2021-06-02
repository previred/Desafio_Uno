package cl.previred.test.business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import cl.previred.test.model.EntradaDTO;
import cl.previred.test.model.SalidaDTO;
import cl.previred.test.service.PeriodosService;

public class PeriodosBusiness {

	@Autowired
	PeriodosService service;
	
	
	public SalidaDTO procesar() {
		
	  SalidaDTO dto = new SalidaDTO();		
		
	  EntradaDTO data = service.getDataPeriodos();
	  
	  
	  if(data != null) {
		  
		  List<LocalDate> mesesFaltantes = getListaPeriodosFaltantes(data);
		  
		  dto.setFechaCreacion(data.getFechaCreacion());
		  dto.setFechaFin(data.getFechaFin());
		  dto.setFechas(data.getFechas());
		  dto.setFechasFaltantes(mesesFaltantes);
	  }
	  
	  
	  return dto;
	}
		
	
	public static synchronized List<LocalDate> getListaPeriodosFaltantes(final EntradaDTO data){

		List<LocalDate> periodoEvaluado =  new ArrayList<LocalDate>();
		
		int mesIni = data.getFechaCreacion().getMonthValue();		
		
		for(int a = data.getFechaCreacion().getYear(); a <= data.getFechaFin().getYear(); a++) {
		
			for(int m = mesIni; m <=12 ; m++) {
		
				if(data.getFechaFin().getMonthValue() == m && data.getFechaFin().getYear() == a ) {
					
					System.out.println(periodoEvaluado);
					
					return periodoEvaluado;
				}

				LocalDate ld = LocalDate.of(a, m, 1);
				
				if(!data.getFechas().contains(ld)) {
					
					periodoEvaluado.add(ld);
				}

			}
			mesIni = 1;			
		}
		
		return periodoEvaluado;
	}
	
	
}
