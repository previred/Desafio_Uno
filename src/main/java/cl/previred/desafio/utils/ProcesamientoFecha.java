package cl.previred.desafio.utils;

import cl.previred.desafio.service.Periodo;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProcesamientoFecha {

    int LIMITE_FECHAS_RESTANTES = 100;

    /**
     * A partir de la lista Fechas del objeto Periodo, se genera la lista FechasFaltantes
     * @param periodo
     * @return
     */
    public List<LocalDate> obtenerFechasFaltantes(Periodo periodo){
        List<LocalDate> fechasFaltantes = new ArrayList<LocalDate>();
        List<LocalDate> fechas = periodo.getFechas();
        LocalDate fechaFin = periodo.getFechaFin();
        LocalDate fechaParaProcesar = periodo.getFechaCreacion();
        boolean fueEncontrado;

        while(fechasFaltantes.size()<LIMITE_FECHAS_RESTANTES
                && fechaParaProcesar.isBefore(fechaFin)
                || fechaParaProcesar.isEqual(fechaFin)){
            fueEncontrado = fechaFueEncontrada(fechaParaProcesar,fechas);
            if(!fueEncontrado)
                fechasFaltantes.add(fechaParaProcesar);
            fechaParaProcesar = fechaParaProcesar.plusMonths(1);

        };

        return fechasFaltantes;
    }

    /**
     * Determina si una fecha fue encontrada en una lista de fechas
     * @param fechaParaEncontrar
     * @param fechasParaComparar
     * @return
     */
    public boolean fechaFueEncontrada(LocalDate fechaParaEncontrar, List<LocalDate> fechasParaComparar ){
        int numeroVecesEncontrado = 0;
        boolean fueEncontrado = false;
        for(LocalDate fechaItem: fechasParaComparar){
            if (fechaParaEncontrar.isEqual(fechaItem)){
                numeroVecesEncontrado++;
            }
        }
        fueEncontrado = numeroVecesEncontrado>0?true:false;
        return fueEncontrado;
    }

}
