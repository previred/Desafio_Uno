package cl.fsoto1.servicio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PeriodoService {
	
	/**
	 * Metodo que devuelve los periodos faltantes de una lista de periodos intermitentes
	 * @param fechaCreacion
	 * @param fechaFin
	 * @param periodos
	 * @return lista de periodos faltantes
	 */
	public List<LocalDate> periodosFaltantes(LocalDate fechaCreacion, LocalDate fechaFin, List<LocalDate> periodos) {
		List<LocalDate> periodosFaltantes = new ArrayList<LocalDate>();
		while(!fechaCreacion.isAfter(fechaFin)) {
			if (!periodos.contains(fechaCreacion)) {
				periodosFaltantes.add(fechaCreacion);
			}
			fechaCreacion = fechaCreacion.plusMonths(1L);
		}
		return periodosFaltantes;
	}
}
