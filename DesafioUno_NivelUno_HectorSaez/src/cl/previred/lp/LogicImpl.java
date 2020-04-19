package cl.previred.lp;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TreeSet;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Clase de logica de negocio
 * Permite leer y generar el archivo con diferencias
 * @author hector saez
 *
 */
public class LogicImpl implements LogicService {
	
	private TreeSet<String> inputDates = new TreeSet<String>(); //set de lista de fechas
	private final JSONObject inputJson;//objeto Json Java

	/**
	 * Constructor principal de la clase
	 * Inicializa las variables que contienen las rutas de los archivos de entrada y salida
	 */
	public LogicImpl(final JSONObject inputJson) {
		super();
		this.inputJson = inputJson;
	}
	
	/**
	 * Metodo que realiza la logica de periodos perdidos
	 * Se identifican las fechas de inicio y termino
	 * Se generan todos los meses entre esos periodos
	 * se hace una comparacion ente conjuntos, entre las fechas ingresadas y las fechas comprendidas entre el rango
	 * las fechas generadas se ordenan automaticamente dado el formato ingles de fechas
	 */
	public TreeSet<String> getLostPeriods() throws Exception {
		TreeSet<String> outputDates = new TreeSet<String>(); //conjunto de fechas perdidas	
		
		JSONArray dates = (JSONArray) inputJson.get("fechas");
		if (dates!=null && dates.size()>0) {
			inputDates.addAll(dates); //fechas de ingresos incorporadas a un conjunto
		
		String createDate = (String) inputJson.get("fechaCreacion");
		String endDate = (String) inputJson.get("fechaFin");
		DateFormat dfFechas= new SimpleDateFormat(Constants.formatDate);
		
		Calendar createCalendar = Calendar.getInstance();
		Calendar endCalendar = Calendar.getInstance();

		try {
			createCalendar.setTime(dfFechas.parse(createDate));
		    endCalendar.setTime(dfFechas.parse(endDate));
		} catch (ParseException e) {
			new Exception("Problemas en formato de fechas:",e);
		}
		
		//ciclo que recorre el rango de fechas por meses
		while (createCalendar.before(endCalendar)) {
		    String date = dfFechas.format(createCalendar.getTime()).toUpperCase();
		    outputDates.add(date);
		    createCalendar.add(Calendar.MONTH, 1);
		}
		//se remueven las fechas repetidas, la diferencia genera los periodos perdidos
		outputDates.removeAll(inputDates);
		}
		else
			throw new Exception("no viene el listado de fechas en el archivo");
		
		return outputDates;
	}
	
	
	
	

}
