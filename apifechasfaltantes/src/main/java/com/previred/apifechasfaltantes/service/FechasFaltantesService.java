package com.previred.apifechasfaltantes.service;

import com.previred.apifechasfaltantes.dto.FechasFaltantesDTO;
import com.previred.apifechasfaltantes.dto.PeriodoDTO;
import com.previred.apifechasfaltantes.serviceclient.GeneradorDatosServiceClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class FechasFaltantesService {

    @GetMapping("/getFechasFaltantes")
    public FechasFaltantesDTO getFechasFaltantes() throws Exception{
        FechasFaltantesDTO fechasFaltantesDTO = new FechasFaltantesDTO();
        GeneradorDatosServiceClient generadorDatosServiceClient = new GeneradorDatosServiceClient();
        try{
            PeriodoDTO periodoDTO = generadorDatosServiceClient.getDatos();
            fechasFaltantesDTO.setId(periodoDTO.getId());
            fechasFaltantesDTO.setFechas(periodoDTO.getFechas());
            fechasFaltantesDTO.setFechaCreacion(periodoDTO.getFechaCreacion());
            fechasFaltantesDTO.setFechaFin(periodoDTO.getFechaFin());
            Period diff = Period.between(
                    LocalDate.parse(periodoDTO.getFechaCreacion().toString()).withDayOfMonth(1),
                    LocalDate.parse(periodoDTO.getFechaFin().toString()).withDayOfMonth(1));
            Set<LocalDate> fechasFaltantes = new HashSet();
            boolean encontrado = false;
            int cont = 0;
            LocalDate fecha = periodoDTO.getFechaCreacion();
            while (cont<=diff.toTotalMonths()){
                for(LocalDate actual:periodoDTO.getFechas()){
                    if(fecha.equals(actual)){
                        encontrado = true;
                    }
                }
                if (!encontrado) {
                    fechasFaltantes.add(fecha);
                }
                encontrado=false;
                fecha = fecha.plusMonths(1);
                cont = cont+1;
            }
            fechasFaltantesDTO.setFechasFaltantes(fechasFaltantes.stream()
                    .sorted()
                    .collect(Collectors.toList()));
        }catch(Exception e){

        }
        return fechasFaltantesDTO;
    }
}
