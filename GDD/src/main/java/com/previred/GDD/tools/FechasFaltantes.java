package com.previred.GDD.tools;

import java.time.LocalDate;
import java.util.Random;
import java.time.Period;

public class FechasFaltantes {
	
	private final LocalDate minDate;
    private final LocalDate maxDate;
	private final Random random;
	
	public FechasFaltantes(LocalDate minDate, LocalDate maxDate) {
        this.minDate = minDate;
        this.maxDate = maxDate;
        this.random = new Random();
    }
	
	//Obtiene fechas aleatoriamente
	public LocalDate nextDate() {
        int minDay = (int) minDate.toEpochDay();
        int maxDay = (int) maxDate.toEpochDay();
        long randomDay = minDay + random.nextInt(maxDay - minDay);
        LocalDate randomDate = LocalDate.ofEpochDay(randomDay).withDayOfMonth(1);
        return randomDate;
    }
	
	//Obtiene la cantidad de meses entre dos fechas
	public int totalDates() {
		int totalDates = 100;
		Period difference = Period.between(minDate, maxDate);
		int months = difference.getMonths() + difference.getYears()*12;
		if(months >= 100) return totalDates;
		else return months;
	}

}
