package com.previred.periodos.util;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Funciones para rangos de fechas.
 * 
 * @author hhumeres
 *
 */
public class RangeUtils {
	
	/**
	 * 
	 * Entrega el complemento de una lista de fechas dado el rango.
	 * 
	 * @param start
	 * @param end
	 * @param dates
	 * @return
	 */
	public static List<LocalDate> getComplement(LocalDate start, LocalDate end, List<LocalDate> dates) {
		Set<LocalDate> datesSet = new HashSet<LocalDate>();
		List<LocalDate> datesComplement = new ArrayList<LocalDate>();
		
        int i = 0;
        while (datesSet.size() < dates.size()) {        
        	datesSet.add(dates.get(i));
        	i++;
        }
        
        while (start.isBefore(end) || start.isEqual(end)) {  
        	if(!datesSet.contains(start))
        		datesComplement.add(start);
        	start = start.with(TemporalAdjusters.firstDayOfNextMonth());
        }
		
		return datesComplement;
	}
	
}
