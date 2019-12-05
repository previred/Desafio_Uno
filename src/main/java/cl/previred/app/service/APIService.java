package cl.previred.app.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cl.previred.app.bean.PeriodoBean;

@Service
public class APIService {

	public String getMissingPeriods(PeriodoBean obj) throws JsonProcessingException {

		// Obtenemos todas las fechas entre el intervalo de tiempo indicado
		List<String> listaEntreFechas = getListaEntreFechas(obj.getFechaCreacion(), obj.getFechaFin());

		// Arreglo que contiene las fechas proporcionadas por el GDD
		List<String> fechasEntregadas = obj.getFechas();

		// Permite recorrer el arreglo con las fechas reportadas
		for (String value : fechasEntregadas) {
			// Se evalua si la fecha proporcionada por el GDD se encuentra en el arreglo de
			// fechas obtenidas por el intervalo de tiempo indicado
			if (listaEntreFechas.contains(value)) {
				// Si la fecha esta en contenida en nuestro arreglo, se elimina.
				// Esto permite reportar solo las fechas que no proporciono el GDD.
				listaEntreFechas.remove(value);
			}
		}

		// Recorremos la lista
		for (String date : listaEntreFechas) {
			// Imprimimos en consola la fechas que no fueron enteradas en el servicio GDD
			System.out.println(date);
		}
		
		obj.setFechasFaltantes(listaEntreFechas);
		
		return new ObjectMapper().writeValueAsString(obj);
	}

	/**
	 * Método para obtener una lista con fechas en el intervalo indicado
	 *
	 * @param fechaInicio Fecha inicial
	 * @param fechaFin    Fecha final
	 *
	 * @return List<String> periodos
	 */
	public List<String> getListaEntreFechas(String fechaInicio, String fechaFin) {

		LocalDate start = LocalDate.parse(fechaInicio);
		LocalDate end = LocalDate.parse(fechaFin);
		List<String> periodos = new ArrayList<>();

		while (!start.isAfter(end)) {

			start = start.plusMonths(1);
			if (start.isBefore(end)) {
				periodos.add(start.toString());
			}

		}

		return periodos;
	}
}
