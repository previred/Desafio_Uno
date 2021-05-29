package com.desafio1.service;

import org.springframework.stereotype.*;
import com.desafio1.repository.*;
import com.desafio1.model.*;
import org.springframework.beans.factory.annotation.Autowired; 
import java.util.*;
import java.util.function.*;
import java.time.*;
import java.time.temporal.*;
import java.util.stream.*;

@Service
public class ClientService {
	
	@Autowired
	ClientGDD clientgdd;

	public Cont completar() throws Exception {
		
		Cont cont = new Cont();
		Periodo periodo = clientgdd.getperiodos();
		
		if (periodo!=null) {
			
			LocalDate f1 = LocalDate.parse( periodo.getFechaCreacion() );
			LocalDate f2 =  LocalDate.parse( periodo.getFechaFin() );
 
			long numOfDaysBetween = ChronoUnit.MONTHS.between(YearMonth.from(f1), YearMonth.from(f2.plusMonths(1))); 
 
  
			List<String> rangoCompleto =   Stream.iterate(f1, x -> x.plusMonths(1))
                .limit(numOfDaysBetween).map(s -> s.toString())
                .collect(Collectors.toList());
				
				               
				rangoCompleto.removeIf( s -> periodo.getFechas().contains( LocalDate.parse( s ) ) );
                
				 cont.setId( periodo.getId() );
                 cont.setFechaCreacion( periodo.getFechaCreacion() ); 
				 cont.setFechaFin( periodo.getFechaFin() ); 
				 cont.setFechas( periodo.getFechas().stream().map( s -> s.toString() ).collect(Collectors.toList()) ); 
				 cont.setFechasFaltantes( rangoCompleto );
			
		}
		
		
		return cont;
	}
	

}
