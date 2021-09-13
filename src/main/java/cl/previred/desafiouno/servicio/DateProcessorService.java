package cl.previred.desafiouno.servicio;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import cl.previred.desafiouno.swagger.codegen.model.MissingDateRequest;
import cl.previred.desafiouno.swagger.codegen.model.MissingDateResponse;

/**
 * Clase que procesa las fechas
 */
@Service
public class DateProcessorService{




/**
 * Procesa un listado de fechas ingresadas como parámetro y retorna las fechas que faltan.
 * @param input Objeto con el listado de fechas.
 * @return Un objeto con el listado de fechas faltantes ademas de los datos de entrada.
 */
public MissingDateResponse getMissingDates(MissingDateRequest input){
    MissingDateResponse result = new MissingDateResponse();
    List<LocalDate> missingDates = new ArrayList<>();

    //agrega las fechas faltantes entre la fecha de inicio y la primera fecha informada en el arreglo
    missingDates.addAll(getDatesBetweenPeriod(input.getFechaCreacion(),input.getFechas().get(0)));

    //agrega las fechas faltantes en el arreglo
    for (int i = 0; i < input.getFechas().size() - 1 ; i++) {
        missingDates.addAll(getDatesBetweenPeriod(input.getFechas().get(i),input.getFechas().get(i+1)));
    }
    
    //agrega las fechas faltantes entre la última fecha del arreglo y la fecha de fin informada
    missingDates.addAll(getDatesBetweenPeriod(input.getFechas().get(input.getFechas().size() -1 ),input.getFechaFin()));

    result.setId(input.getId());
    result.setFechaCreacion(input.getFechaCreacion());
    result.setFechaFin(input.getFechaFin());
    result.setFechas(input.getFechas());
    result.setFechasFaltantes(missingDates);


    return result;
}

/**
 * Retorna la lista de todos los dias entre el periodo determinado por los 
 * parámetros start y end
 * @param start Fecha inicial.
 * @param end Fecha Final.
 * @return Listado de fechas entre el periodo.
 */
public List<LocalDate> getDatesBetweenPeriod(LocalDate start, LocalDate end){
    return Stream.iterate(start.plusMonths(1),e -> e.isBefore(end),  e -> e.plusMonths(1))
    .collect(Collectors.toList());

}



}