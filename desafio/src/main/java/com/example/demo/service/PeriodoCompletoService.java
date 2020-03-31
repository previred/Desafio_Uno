package com.example.demo.service;

import com.example.demo.client.PeriodoClient;
import com.example.demo.entity.Periodo;
import com.example.demo.entity.PeriodoCompleto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

@Service
public class PeriodoCompletoService implements IPeriodoCompletoService{

    @Autowired
    private PeriodoClient periodoClient;

    @Override
    public Periodo devolverPeriodo() {
        Periodo periodoRecibido=null;
        if(this.periodoClient.obtenerPeriodo()!=null){
            periodoRecibido= this.periodoClient.obtenerPeriodo();
        }
        return periodoRecibido;
    }

    @Override
    public PeriodoCompleto devolverPeriodoCompleto() {

        PeriodoCompleto resultado=new PeriodoCompleto();
        if (this.periodoClient.obtenerPeriodo() != null) {

            Periodo objRecibido = this.periodoClient.obtenerPeriodo();

            ArrayList<String> fechasString = objRecibido.getFechas();
            ArrayList<LocalDate> fechaLocal = new ArrayList<>();

            for (String fecha : fechasString) {
                LocalDate fechax = LocalDate.parse(fecha);
                fechaLocal.add(fechax);
            }
            LocalDate fFin = LocalDate.parse(objRecibido.getFechaFin());
            LocalDate fInicio = LocalDate.parse(objRecibido.getFechaCreacion());

            long dif = ChronoUnit.MONTHS.between(fInicio, fFin)+1;

            ArrayList<LocalDate> fechasCompletas = new ArrayList<>();

            for (int i = 0; i < dif; i++) {

                LocalDate nuevaFecha = fInicio.plusMonths(i);
                fechasCompletas.add(nuevaFecha);
            }

            for (LocalDate fecha : fechaLocal) {
               fechasCompletas.remove(fecha);
            }

            resultado.setId(objRecibido.getId().intValue());
            resultado.setFechaCreacion(fInicio);
            resultado.setFechaFin(fFin);
            resultado.setFechas(fechaLocal);
            resultado.setFechasFaltantes(fechasCompletas);
        }
        return resultado;
    }


}
