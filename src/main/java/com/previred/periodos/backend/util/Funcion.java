package com.previred.periodos.backend.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;

/**
*
* @author arojas
*/
public class Funcion {

	final static Logger logger = Logger.getLogger(Funcion.class);
	final static SimpleDateFormat sdf = new SimpleDateFormat(Constante.FORMATO_FECHA_DEFECTO);
	
	static public List<String> generarFechas(String fIni, String fFin, List<String> fechas) {
		List<String> fechasFaltantes = new ArrayList<String>();
		try {
			String f = "";
			Calendar c1 = Calendar.getInstance();
		    c1.setTime(sdf.parse(fIni));
		    Calendar c2 = Calendar.getInstance();
		    c2.setTime(sdf.parse(fFin));
		    while (!c1.after(c2)) {
		    	f = sdf.format(c1.getTime());
		    	if (!f.equals(fIni) && !f.equals(fFin) && !fechas.contains(f))
		    		fechasFaltantes.add(f);
		        c1.add(Calendar.MONTH, 1);
		    }
		} catch (ParseException e) {
			logger.error("ERROR: " + e.getMessage());
		}
		return fechasFaltantes;
	}
}
