package cl.previred.desafio.util;

import cl.previred.desafio.client.model.Periodo;

public class ValidateUtils {
	
	 private ValidateUtils() {
		    throw new IllegalStateException("Utility class");
		  }

	
	public static Boolean validateGDDResponse(Periodo input) {
		
		if(null == input) {
			return Boolean.FALSE;
		}
		
		if(null == input.getFechaCreacion() || null == input.getFechaFin() || null == input.getFechas() || input.getFechas().isEmpty()) {
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}

}
