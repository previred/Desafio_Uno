package cl.rodrigo.misolucion.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.rodrigo.misolucion.bean.ResponseGDD;
import cl.rodrigo.misolucion.bean.ResponseMiSolucion;

@RestController
@RequestMapping(value="/ws")
@CrossOrigin(origins = "*")
public class MiSolucionService {
	private static final String FECHA_CREACION_ERROR ="Sin información por parte del servicio GDD";
	private static final String FECHA_FIN_ERROR ="Sin información por parte del servicio GDD";
	private static final List<String> FECHAS_FALTANTES_ERROR = new ArrayList<>() ;
	private static final List<String> FECHAS_ERROR = new ArrayList<>();
	private static final int ID_ERROR =0;
	private static final String GUION = "-";
	private static final String ZERO = "0";
	private static final String FECHA_FALTANTE_FORMATO="%d-%s-%s";

	
	
	@GetMapping(path="/misolucion")
	public ResponseMiSolucion detAgrup() {
		ResponseMiSolucion response = new ResponseMiSolucion();
		ResponseGDD respGDD = GddClient.cliGDD();
		List<String> fechasFaltantes= new ArrayList<>();
		if(respGDD.getFechas()!=null) {
			//fechaCreacion year=0, mounth=1,day =2
			String[] fechaCreacion= respGDD.getFechaCreacion().split(GUION);
			String[] fechaFin= respGDD.getFechaFin().split(GUION);
			
			for( int year=Integer.parseInt(fechaCreacion[0]); year<=Integer.parseInt(fechaFin[0]);year++) {
				for(int mounth=1; mounth<=12 ;mounth++) {
					String mounthString =mounth<10?ZERO+mounth:String.valueOf(mounth);
					if(!respGDD.getFechas().contains(String.format(FECHA_FALTANTE_FORMATO, year,mounthString,fechaCreacion[2]))) {
						fechasFaltantes.add(String.format(FECHA_FALTANTE_FORMATO, year,mounthString,fechaCreacion[2]));
					}
				}
			}
			
			
			response = new ResponseMiSolucion(respGDD.getId(),respGDD.getFechaCreacion(),respGDD.getFechaFin(),respGDD.getFechas(),fechasFaltantes);
		}else {
			response = new ResponseMiSolucion(ID_ERROR,FECHA_CREACION_ERROR,FECHA_FIN_ERROR,FECHAS_ERROR,FECHAS_FALTANTES_ERROR);
		}
		return response;
	}
}
