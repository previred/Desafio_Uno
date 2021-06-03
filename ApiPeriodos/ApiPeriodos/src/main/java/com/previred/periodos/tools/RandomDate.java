package com.previred.periodos.tools;

import java.time.LocalDate;
import java.util.Random;

/**
 * Generador de fechas aleatorio
 * @author mgonzalez@previred.com
 */
public class RandomDate {
    private final LocalDate minDate;
    private final LocalDate maxDate;
    private final Random random;

    /**
     * Inicializa el rango de fecha a generar
     * @param minDate Fecha inicial
     * @param maxDate Fecha final
     */
    public RandomDate(LocalDate minDate, LocalDate maxDate) {
        this.minDate = minDate;
        this.maxDate = maxDate;
        this.random = new Random();
    }

    /**
     * Genera una fecha aleatoria entre la fecha inicializada como inicial y final.
     * La fecha generada es el primer día del mes seleccionado
     * @return 
     */
    public LocalDate nextDate() {
        int minDay = (int) minDate.toEpochDay();
        int maxDay = (int) maxDate.toEpochDay();
        long randomDay = minDay + random.nextInt(maxDay - minDay);
        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
        return randomDate.withDayOfMonth(1);
    }

    @Override
    public String toString() {
        return "RandomDate{" +
                "maxDate=" + maxDate +
                ", minDate=" + minDate +
                '}';
    }
}
