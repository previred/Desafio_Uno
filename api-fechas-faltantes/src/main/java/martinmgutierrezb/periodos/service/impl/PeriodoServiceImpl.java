package martinmgutierrezb.periodos.service.impl;


import martinmgutierrezb.periodos.dao.IPeriodoDao;
import martinmgutierrezb.periodos.models.Periodo;
import martinmgutierrezb.periodos.service.IPeriodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PeriodoServiceImpl implements IPeriodoService {

	@Autowired
	private IPeriodoDao periodoDao;

	/**
     * Metodo que consulta los periodos del servicio de periodos y luego busca las fechas faltantes a
     * traves de un elemento pivote para su comparación
     * @return Retorna el objeto original del servicio pero añadiendole la lista de fechas faltantes
     */
	public Periodo getPeriodosFaltantes() {
		List<LocalDate> fechasFaltantes = new ArrayList<LocalDate>();
		Periodo periodo = periodoDao.getPeriodos();
		LocalDate pivote = periodo.getFechaCreacion();
		while (pivote.compareTo(periodo.getFechaFin())<=0) {
			LocalDate finalPivote = pivote;
			Optional<LocalDate> optFecha = periodo.getFechas().stream()
					.filter(fecha -> fecha.equals(finalPivote))
					.findFirst();
			if (!optFecha.isPresent()) {
				fechasFaltantes.add(pivote);
			}
			pivote = pivote.plusMonths(1);
		}
		periodo.setFechasFaltantes(fechasFaltantes.stream()
				.sorted()
				.collect(Collectors.toList()));
		return periodo;
	}

}
