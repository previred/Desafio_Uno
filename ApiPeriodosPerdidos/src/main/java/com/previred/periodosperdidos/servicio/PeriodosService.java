package com.previred.periodosperdidos.servicio;

import com.previred.periodosperdidos.periodos.PeriodoCliente;
import com.previred.periodosperdidos.swagger.codegen.api.ApiException;
import com.previred.periodosperdidos.swagger.codegen.model.PeriodoPerdidoRest;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Se toma como base una funcion de previred
 */
@Service
public class PeriodosService {
    private static final String URL_PERIODOS = "http://127.0.0.1:8080/";
    private static final String CONTEXT_PATCH = "periodos";

    /**
     * Genera un Objetos PeriodoPerdidoRest, el cual se obtiene de la llamada al api de periodos y obtiene las fechas
     * perdidas
     *
     * @return Objeto PeriodoPerdidoRest el cual contiene la lista de periodos más las fechas perdidas
     */
    public PeriodoPerdidoRest getPeriodosPerdidos() throws ApiException, IOException {
        PeriodoCliente periodoCliente = new PeriodoCliente(URL_PERIODOS,CONTEXT_PATCH);

        PeriodoPerdidoRest periodo = new PeriodoPerdidoRest();
        periodo.setPeriodo(periodoCliente.getPeriodo());
        getFechasPerdidas(periodo);

        return periodo;
    }

    /**
     * Metodo para agregar la lista de fechas perdidas
     * @param periodoPerdidoRest Objeto que contiene el Periodo mas las Fechas Perdidas
     */

    private void getFechasPerdidas(PeriodoPerdidoRest periodoPerdidoRest){
        if(periodoPerdidoRest.getFechaCreacion()!=null && periodoPerdidoRest!=null){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate fechaPivote = LocalDate.parse(periodoPerdidoRest.getFechaCreacion(), formatter);
            LocalDate fechaFin = LocalDate.parse(periodoPerdidoRest.getFechaFin(), formatter);
            Map<String,String> map = convertFechasToHashMap(periodoPerdidoRest.getFechas());
            while (fechaFin.isAfter(fechaPivote) || fechaFin.isEqual(fechaPivote)){
                if(!map.containsKey(fechaPivote.toString())){
                    periodoPerdidoRest.getFechasPerdidas().add(fechaPivote.toString());
                }
                fechaPivote = fechaPivote.plusMonths(1);
            }
        }

    }

    /**
     * Metodo para convertir Lista de Fechas en HashMap, asi es mas facil la busqueda para las fechas perdidas
     * @param fechas Lista de Fechas
     * @return Map de la conversion de la lista de fechas
     */

    private Map<String,String> convertFechasToHashMap(List<String> fechas){
        Map<String,String> map = new HashMap<>();
        if(fechas!=null){
            for(String fecha : fechas){
                map.put(fecha,fecha);
            }
        }
        return map;
    }
}
