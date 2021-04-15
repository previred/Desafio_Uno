package com.previred.desafio.periodo;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PeriodoService {

    public Periodo getPeriodoConFechasFaltantes() {

        RestTemplate restTemplate = new RestTemplate();

        String urlGDD = "http://localhost:8080/periodos/api";

        //Se obtiene un Periodo a través del servicio proporcionado
        Periodo periodo = restTemplate.getForEntity(urlGDD, Periodo.class).getBody();

        System.out.println("Fecha creación: " + periodo.getFechaCreacion());
        System.out.println("Fecha fin: " + periodo.getFechaFin());

        long cantidadMeses = ChronoUnit.MONTHS.between(periodo.getFechaCreacion(), periodo.getFechaFin()) + 1;

        //Se determinan cuáles son las fechas faltantes
        List<LocalDate> fechasFaltantes = Stream.iterate(periodo.getFechaCreacion(), f -> f.plusMonths(1))
                .limit(cantidadMeses)
                .filter(f -> periodo.getFechas().stream().noneMatch(p -> p.isEqual(f)))
                .collect(Collectors.toList());

        System.out.println("Cantidad de fechas faltantes: " + fechasFaltantes.size());
        System.out.println("Cantidad de días = fechas proporcionadas + fechas faltantes: "
                + (cantidadMeses == periodo.getFechas().size() + fechasFaltantes.size()));

        periodo.setFechasFaltantes(fechasFaltantes);

        return periodo;
    }

}
