package com.cox.igdd.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class GenerarFechasFaltantes {

	
	public GenerarFechasFaltantes() {

	}

	public List<String> obtenerDiferencias(String fechaCreacion, String fechaFin, List<String> fechasIncompletas)
			throws ParseException {

		// creando un listado completo de fechas para despues ser comparada con el
		// listado fallo
		// proveniente del servicio GDD
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		List<String> arrayConFechasGeneradas = new ArrayList<String>();	
		List<String> arrayConFechasFaltantes = new ArrayList<String>();
		List<String> arrayConFechasGeneradasMasFechasIncompletas = new ArrayList<String>();

		// Creamos objectos calendario para construir listado completo de fechas
		Calendar calInicial = Calendar.getInstance();
		calInicial.setTime(formato.parse(fechaCreacion));
		Calendar calFinal = Calendar.getInstance();
		calFinal.setTime(formato.parse(fechaFin));

		// Construimos el listado completo de fechas segun rango en el listado del
		// servicio GDD
		while (calFinal.after(calInicial)) {
			if (calInicial.get(Calendar.DAY_OF_MONTH) == 1) {
				arrayConFechasGeneradas.add(formato.format(calInicial.getTime()));
			}
			calInicial.add(Calendar.DATE, 1);
		}

		// Acumulamos los 2 listados en un solo arreglo
		arrayConFechasGeneradasMasFechasIncompletas.addAll(arrayConFechasGeneradas);
		arrayConFechasGeneradasMasFechasIncompletas.addAll(fechasIncompletas);

		// ordenamos el arreglo para luego analizarlo y extraer los valores unicos
		// que representan las fechas faltantes en el listado ortorgado por el servicio
		// GDD
		arrayConFechasGeneradasMasFechasIncompletas.sort(String::compareToIgnoreCase);

		for (int x = 0; x < arrayConFechasGeneradasMasFechasIncompletas.size(); x++) {
			if (esValorUnico(arrayConFechasGeneradasMasFechasIncompletas.get(x),
					arrayConFechasGeneradasMasFechasIncompletas)) {
				arrayConFechasFaltantes.add(arrayConFechasGeneradasMasFechasIncompletas.get(x));
			}
		}


		return arrayConFechasFaltantes;
	}

	public static boolean esValorUnico(String valor, List<String> arreglo) {
		int contador = 0;
		for (int x = 0; x < arreglo.size(); x++) {
			if (valor.equals(arreglo.get(x)))
				contador++;
		}
		return contador == 1;
	}

}
