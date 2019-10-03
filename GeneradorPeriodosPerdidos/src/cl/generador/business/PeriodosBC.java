package cl.generador.business;

import java.util.ArrayList;
import java.util.List;

import cl.periodos.conector.ConectorRest;
import cl.periodos.model.PeriodoTO;

public class PeriodosBC {
	
	private static final int MESES_DEL_ANIO = 12;
	
	/**
	 * Metodo que obtiene todos los periodos existentes y tambien periodos faltantes. 
	 * @return PeriodoTO Objeto con los datos obtenidos desde servicio Rest.
	 */
	public PeriodoTO obtenerTotalPeriodos(){
		PeriodoTO periodos = new PeriodoTO(); 
		ConectorRest conector = new ConectorRest();
		
		periodos = conector.obtenerFechas();
		
		if(periodos != null && periodos.getFechaCreacion()!= null && periodos.getFechaFin() != null && periodos.getFechas()!= null){
			obtenerTodosLosPeriodos(periodos);
		}
		
		return periodos;
	}

	/**
	 * Metodo que obtiene todos los periodos entre dos fechas.
	 * @param periodos
	 */
	private void obtenerTodosLosPeriodos(PeriodoTO periodos) {
		int anioInicio = Integer.parseInt(periodos.getFechaCreacion().substring(0, periodos.getFechaCreacion().length()-6));
		int anioTermino = Integer.parseInt(periodos.getFechaFin().substring(0, periodos.getFechaFin().length()-6));
		int mesPeriodoInicial = Integer.parseInt(periodos.getFechaCreacion().substring(5, periodos.getFechaCreacion().length()-3));
		String periodosFaltanteFormateado = "";
		int contadorPrimerCiclo = 1;
		
		List todosLosPeriodos = new ArrayList();
		
		if(anioInicio > 0 && anioTermino > 0 && mesPeriodoInicial > 0){
		for(int i = anioInicio ; i <= anioTermino ; i++){
			if (contadorPrimerCiclo == 1)
				periodosFaltanteFormateado = String.valueOf(i);
			for(int j = mesPeriodoInicial ; j <= MESES_DEL_ANIO ; j++){		
					if(j<10){
						periodosFaltanteFormateado = periodosFaltanteFormateado + "-0" + j + "-01";
					}
					else{
						periodosFaltanteFormateado = periodosFaltanteFormateado + "-" + j + "-01";
					}
					todosLosPeriodos.add(periodosFaltanteFormateado);
					periodosFaltanteFormateado = "";
					periodosFaltanteFormateado = String.valueOf(i);
			}
			mesPeriodoInicial = 1;
		}
		obtenerPeriodosFaltantes(todosLosPeriodos , periodos.getFechas(), periodos);
		}
		
	}

	/**
	 * Metodo que obtiene periodos faltantes entre dos fechas.
	 * @param todosLosPeriodos
	 * @param periodosExistentes
	 * @param periodos
	 */
	private void obtenerPeriodosFaltantes(List todosLosPeriodos, List periodosExistentes, PeriodoTO periodos) {
		List periodosFaltantes = new ArrayList() ;

		
		for (Object todosPeriodos : todosLosPeriodos) 
		{ 
		 if(!periodosExistentes.contains(todosPeriodos)){
			 periodosFaltantes.add(todosPeriodos);
		 }
		}
		
		if(periodosFaltantes != null && periodosFaltantes.size() > 0){
			periodos.setFechasFaltantes(periodosFaltantes);
		}
		System.out.println("Periodos faltantes: " + periodos.toString());
	}
}
