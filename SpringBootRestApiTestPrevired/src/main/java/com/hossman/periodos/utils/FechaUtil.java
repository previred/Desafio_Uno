package com.hossman.periodos.utils;
import static java.time.temporal.ChronoUnit.MONTHS;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FechaUtil {
	
/***
 *  @author Hossman Escobar (H.E)
 *  @param fechaInicio = fecha de inicio para generar fechas
 *  @param	fechaFin = fecha de fin para generar fechas
 *  @param	fechaFin = Numero del intervalo que quieren generar la fecha, por ejemplo: 1=dia a dia, 2= de a 2 en 2 dias.
 * 
 * Genera una lista de fechas entre dos rangos de fecha, considerando modalidad de meses/dias, y el intervalo que se requiere entre ellos
 * 
 * @return todasLasFechas = todasLasFechas generadas entre la fecha de inicio y fin .
 */
	 public static List<LocalDate> generadorDeFechasMeses(LocalDate fechaInicio, LocalDate fechaFin, int cantidadAsumar ) {		
		/*H.E: Genero todas las fechas desde la fecha de inicio hasta la fecha de fin */
		List<LocalDate> todasLasFechas = new ArrayList<LocalDate>();

		if(fechaInicio.isBefore(fechaFin)) {
			/*HE Orden de complejidad (n) */
			todasLasFechas = Stream.iterate(fechaInicio, date -> date.plusMonths(cantidadAsumar))
									.limit(MONTHS.between(fechaInicio,fechaFin)+1) /*H.E: El limite lo deje dinamico, ya que no se habla de limites en el enunciado desafio 3 */
									.collect(Collectors.toList());
		}
		/*H.E En caso que la fecha de fin sea mayor a la de inicio, se asume una lista vacía, es decir, no hay fechas en ese rango*/
		return todasLasFechas;
		
	 }

}
