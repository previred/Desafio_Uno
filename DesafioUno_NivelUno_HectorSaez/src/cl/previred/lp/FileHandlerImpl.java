package cl.previred.lp;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Clase de logica de negocio
 * Permite leer y generar el archivo con diferencias
 * @author hector saez
 *
 */
public class FileHandlerImpl implements FileHandlerService {
	
	private final String inputFile;
	private final String outputFile;
	
	/**
	 * Constructor principal de la clase
	 * Inicializa las variables que contienen las rutas de los archivos de entrada y salida
	 */
	public FileHandlerImpl(final String inputFile, final String outputFile) {
		super();
		this.inputFile = inputFile;
		this.outputFile = outputFile;
	}
	
	/**
	 * Metodo que permite leer el archivo de entrada y parsearlo a un objeto json
	 * @throws Exception
	 */
	public JSONObject readFile () throws Exception
	{
		JSONObject inputJson;//objeto Json Java
		JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader(this.inputFile))
        {
        	//Read JSON file
        	inputJson = (JSONObject) jsonParser.parse(reader);
            
            System.out.println("Arhivo de Entrada ");
            System.out.println(inputJson);
 
        } catch (FileNotFoundException e) {
        	throw new Exception("Archivo de entrada encontrado:",e);
        } catch (IOException e) {
        	throw new Exception("Problemas de lecturas:",e);
        }
        return inputJson;
		
	}
	
	
	
	/**
	 * Metodo que permite escribir los periodos perdidos en un archivo de tipo json
	 */
	public void writeFile(JSONObject inputJson, TreeSet<String> outputDates) throws Exception {
		//First Employee
		Map<String, Object> mapJsonOutput = new HashMap<>();
        mapJsonOutput.put("id", (Long) inputJson.get("id"));
        mapJsonOutput.put("fechaCreacion", (String) inputJson.get("fechaCreacion"));
        mapJsonOutput.put("fechaFin", (String) inputJson.get("fechaFin"));
        
        JSONArray dateList = new JSONArray();
        dateList.addAll(outputDates);
        mapJsonOutput.put("fechasFaltantes", dateList);
        
        JSONObject jsonOutput = new JSONObject(mapJsonOutput);
        
        try (FileWriter file = new FileWriter(this.outputFile)) {
        	 
            file.write(jsonOutput.toJSONString());
            file.flush();
            System.out.println("Arhivo Generado exitosamente Resultado: ");
            System.out.println(jsonOutput);
 
        } catch (IOException e) {
        	throw new Exception("Problemas de lecturas:",e); 
        }
	}

}
