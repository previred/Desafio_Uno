package cl.previred.arquitectura.seleccion.lagunas.salida;
import java.io.IOException;
import java.io.StringWriter;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import cl.previred.arquitectura.seleccion.lagunas.exceptions.SalidaException;
import cl.previred.arquitectura.seleccion.lagunas.proceso.OutputData;
import cl.previred.arquitectura.seleccion.lagunas.proceso.OutputVO;

/**
 * Clase que implementa una salida que puede ser utilizada por el buscador de lagunas.
 * @author Juan Villablanca
 *
 */
public class SalidaEstandard implements OutputData{


	/* (non-Javadoc)
	 * @see cl.previred.arquitectura.seleccion.lagunas.proceso.OutputData#putLagunas(cl.previred.arquitectura.seleccion.lagunas.proceso.OutputVO)
	 */
	@Override
	public void putLagunas(OutputVO datos) throws SalidaException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		
		StringWriter stringEmp = new StringWriter();
		try {
			objectMapper.writeValue(stringEmp, datos);
		} catch (JsonGenerationException|JsonMappingException e) {
			throw new SalidaException("Error al generar JSON",e);
		} catch (IOException e) {
			throw new SalidaException("Error IO",e);
		}
		System.out.println(stringEmp);
		
	}

}
