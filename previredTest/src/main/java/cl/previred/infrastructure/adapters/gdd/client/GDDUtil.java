package cl.previred.infrastructure.adapters.gdd.client;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import cl.previred.domain.error.InternalBussinesException;
import cl.previred.infrastructure.adapters.gdd.model.Periodo;

@Component
public class GDDUtil {
	
	public List<LocalDate> giveMeTheMissingDates(Periodo periodo) throws InternalBussinesException{
		
		return this.findDates(
				periodo.getFechaCreacion(),
				periodo.getFechaFin(),
				periodo.getFechas(),
     		      new ArrayList<LocalDate>(),
   	    		  0);	
	}
	 
	public List<LocalDate> findDates(LocalDate startDate,LocalDate endDate,List<LocalDate> receivedDates, List<LocalDate> filteredDates, int count) throws InternalBussinesException {

    	if(!startDate.isAfter(endDate)) {
			if(count < receivedDates.size()  && startDate.isEqual(receivedDates.get(count))) {
				count++;
				startDate = startDate.plusMonths(1L);
				return findDates(startDate, endDate, receivedDates, filteredDates, count);
			}
			filteredDates.add(startDate);
			startDate = startDate.plusMonths(1L);
			return findDates(startDate, endDate, receivedDates, filteredDates, count);
		}else {
			return filteredDates;
		}
	    	
	 }

}
