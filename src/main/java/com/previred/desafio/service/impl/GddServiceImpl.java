package com.previred.desafio.service.impl;

import com.previred.desafio.client.GddClient;
import com.previred.desafio.dto.GddDto;
import com.previred.desafio.service.GddService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class GddServiceImpl implements GddService {

    private final GddClient gddClient;

    public GddServiceImpl(GddClient gddClient) {
        this.gddClient = gddClient;
    }

    @Override
    public GddDto genDates(){
        try {
            log.info("[genDates][INI_OK]");
            GddDto data = gddClient.getDataDesafio();
            return this.fillDates(data);
        }catch(Exception ex ){
            log.error("[getMessages] [INI_OK]", ex);
            return null;
        }
    }

    private GddDto fillDates(GddDto entry){
        log.info("[fillDates][INI_OK]");
        LocalDate fechaCreacion = entry.getFechaCreacion();
        LocalDate fechaFin = entry.getFechaFin();
        List<LocalDate> lstInputFecha = entry.getFechas();

        long numOfMonths = ChronoUnit.MONTHS.between(fechaCreacion, fechaFin) <= 100 ? ChronoUnit.MONTHS.between(fechaCreacion, fechaFin) : 100;

        List<LocalDate> lstAllFechas = Stream.iterate(fechaCreacion, date -> date.plusMonths(1))
                .limit(numOfMonths+lstInputFecha.size())
                .collect(Collectors.toList());

        List<LocalDate> fechasFaltantes =
                lstAllFechas.stream()
                .filter(e -> !lstInputFecha.contains(e))
                .limit(numOfMonths)
                .collect(Collectors.toList());
        log.info("[fillDates][fechasFaltantes: {}]",fechasFaltantes.size());

        entry.setFechasFaltantes(fechasFaltantes);

        return entry;
    }
    public LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
}
