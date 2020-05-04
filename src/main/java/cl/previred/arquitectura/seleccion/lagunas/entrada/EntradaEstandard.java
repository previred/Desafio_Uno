package cl.previred.arquitectura.seleccion.lagunas.entrada;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cl.previred.arquitectura.seleccion.lagunas.exceptions.EntradaException;
import cl.previred.arquitectura.seleccion.lagunas.proceso.InputData;
import cl.previred.arquitectura.seleccion.lagunas.proceso.InputVO;

/**
 * Esta clase captura los datos desde la enntrada estandard y arma el objeto con los datos.
 * @author Juan Villablanca
 *
 */
public class EntradaEstandard implements InputData {

	/* (non-Javadoc)
	 * @see cl.previred.arquitectura.seleccion.lagunas.proceso.InputData#getData()
	 */
	@Override
	public InputVO getData() throws EntradaException  {

		InputVO datai=null;
		try {
			//Leemos desde la entrada y guardamos en un stringbuffer
			BufferedReader br = null;
			StringBuilder sb = new StringBuilder();
			br = new BufferedReader(new InputStreamReader(System.in));
			String linea=null;
			while((linea = br.readLine()) != null) {
				sb.append(linea);
			}
			br.close();

			//Se convierte el json a objeto
			ObjectMapper objectMapper = new ObjectMapper();
			datai=objectMapper.readValue(sb.toString(), InputVO.class);

		} catch (JsonParseException|JsonMappingException e) {
			throw new EntradaException("Error en el parseo",e);
		} catch (IOException e) {
			throw new EntradaException("Error IO ",e);
		}

		return datai;
	}

}
