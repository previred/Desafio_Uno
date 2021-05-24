package org.pr3v1r3d.desafio1.util;

import java.time.LocalDate;
import java.util.List;

public class DateUtil {

	public static List<LocalDate> findMissingDates(
			LocalDate initDate,
			LocalDate finalDate,
			List<LocalDate> existingDates,
			List<LocalDate> missinggDates,
			int indice) {
		if(!initDate.isAfter(finalDate)) {
			if(indice < existingDates.size()  && initDate.isEqual(existingDates.get(indice))) {
				indice++;
				initDate = initDate.plusMonths(1L);
				return findMissingDates(initDate, finalDate, existingDates, missinggDates, indice);
			}
			missinggDates.add(initDate);
			initDate = initDate.plusMonths(1L);
			return findMissingDates(initDate, finalDate, existingDates, missinggDates, indice);
		}else {
			return missinggDates;
		}
	}

}
