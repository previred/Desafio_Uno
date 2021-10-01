package com.nibor.examen.lostperiod.services;

import com.nibor.examen.lostperiod.dtos.PeriodoGeneradoDTO;
import com.nibor.examen.lostperiod.dtos.PeriodoPerdidoDTO;
import com.nibor.examen.lostperiod.exceptions.GeneralServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PeriodoService {

    @Autowired
    private GeneradorDatosService generadorDatosService;

    public PeriodoPerdidoDTO getPeriodosPerdidos() {

        try {
            PeriodoGeneradoDTO periodoGeneradoDTO = generadorDatosService.getPeriodosGenerado();
            return generaPeriodoPerdidos( periodoGeneradoDTO );
        }catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    private PeriodoPerdidoDTO generaPeriodoPerdidos( PeriodoGeneradoDTO periodoGeneradoDTO ) {
        List<String> periodosDelRango = getTodosLosPeriodosDelRangoFecha( periodoGeneradoDTO );
        return getSoloPeriodoPerdidos(periodoGeneradoDTO, periodosDelRango);
    }

    private List<String> getTodosLosPeriodosDelRangoFecha(PeriodoGeneradoDTO periodoGeneradoDTO ) {

        List<LocalDate> periodosDelRangoFecha = new ArrayList<>();

        LocalDate dateCreacion = LocalDate.parse(periodoGeneradoDTO.getFechaCreacion());
        LocalDate dateFin = LocalDate.parse(periodoGeneradoDTO.getFechaFin());
        Period periodosEntreCreacionFin = Period.between(dateCreacion, dateFin);

        for (int i = 0; i <= periodosEntreCreacionFin.toTotalMonths(); i++) {
            periodosDelRangoFecha.add(dateCreacion.plusMonths(i));
        }
        return periodosDelRangoFecha.stream().map(String::valueOf).collect(Collectors.toList());
    }

    private PeriodoPerdidoDTO getSoloPeriodoPerdidos( PeriodoGeneradoDTO periodoGeneradoDTO,
                                                      List<String> periodosDelRangoFecha ) {

        PeriodoPerdidoDTO periodoPerdidoDTO = new PeriodoPerdidoDTO();

        periodoPerdidoDTO.setId(periodoGeneradoDTO.getId());
        periodoPerdidoDTO.setFechaCreacion(periodoGeneradoDTO.getFechaCreacion());
        periodoPerdidoDTO.setFechaFin(periodoGeneradoDTO.getFechaFin());
        periodoPerdidoDTO.setFechas(periodoGeneradoDTO.getFechas());

        List<String> periodosPerdidos = new ArrayList<>();
        for (String periodo: periodosDelRangoFecha) {
            if(!periodoGeneradoDTO.getFechas().contains(periodo)){
                periodosPerdidos.add(periodo);
            }
        }
        periodoPerdidoDTO.setFechasFaltantes(periodosPerdidos);
        return periodoPerdidoDTO;
    }
}