package cl.desafio.previred.service;

import cl.desafio.previred.dominio.Periodo;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PeriodosServiceImpl implements PeriodosService {

    private static final Logger logger = LoggerFactory.getLogger(PeriodosServiceImpl.class);

    @Override
    public Periodo consultaPeriodos(Periodo periodo) {

        List<LocalDate> fechas = new ArrayList<>();

        for (LocalDate date = periodo.getFechaCreacion();
             date.isBefore(periodo.getFechaFin());
             date = date.withDayOfMonth(1)) {

            if (!Arrays.asList(periodo.getFechas()).contains(date)) {

                fechas.add(date);
            }

            periodo.setFechasFaltantes(fechas);
        }


        return periodo;
    }
}
