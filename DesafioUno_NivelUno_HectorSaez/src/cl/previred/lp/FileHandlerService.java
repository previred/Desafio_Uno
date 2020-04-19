package cl.previred.lp;

import java.util.TreeSet;

import org.json.simple.JSONObject;

/**
 * Interfaz de la logica de negocio
 * @author hector saez
 *
 */
public interface FileHandlerService {
	
	JSONObject readFile () throws Exception;
	void writeFile(final JSONObject inputJson, final TreeSet<String> outputDates) throws Exception;
}
