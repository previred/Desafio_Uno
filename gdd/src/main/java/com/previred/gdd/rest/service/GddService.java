package com.previred.gdd.rest.service;

import com.previred.gdd.rest.model.GDD;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Service
public class GddService {

    private static final Logger logger = LoggerFactory.getLogger(GddService.class);
    private static final Random random = new Random();
    private static final Integer low = 1;
    private static final Integer high = 100;

    public GDD generateData(LocalDate fechaCreacion, LocalDate fechaFin){
        GDD gdd = new GDD();
        this.logger.info(String.format("Fecha Creacion %s", fechaCreacion));
        this.logger.info(String.format("Fecha Fin %s", fechaFin));

        List<String> fechasAll = this.generateDatesListBetweenDates(fechaCreacion, fechaFin);
        Integer size = fechasAll.size();

        List<String> fechas = new ArrayList<>();
        List<String> fechasFaltantes = new ArrayList<>();

        fechas.addAll(fechasAll.subList(0, size / 2 + size % 2));
        fechasFaltantes.addAll(fechasAll.subList(size / 2 + size % 2, size));

        gdd.setId(this.generateRandom());
        gdd.setFechaCreacion(fechaCreacion.toString());
        gdd.setFechaFin(fechaFin.toString());
        gdd.setFechas(fechas);
        gdd.setFechasFaltantes(fechasFaltantes);

        return gdd;
    }

    /**
     * generate dates list between two dates
     * @param fechaCreacion
     * @param fechaFin
     * @return
     */
    private List<String> generateDatesListBetweenDates(LocalDate fechaCreacion, LocalDate fechaFin) {
        long monthsBetween = ChronoUnit.MONTHS.between(fechaCreacion, fechaFin);
        return LongStream.iterate(0, i -> (i + 1L))
                .limit((monthsBetween + 1L))
                .mapToObj(i -> fechaCreacion.withDayOfMonth(1).plusMonths(i).toString())
                .collect(Collectors.toList());
    }

    /**
     * generate random id
     * @return
     */
    private Long generateRandom(){
        return Long.valueOf(random.nextInt(high-low) + low);
    }

}
