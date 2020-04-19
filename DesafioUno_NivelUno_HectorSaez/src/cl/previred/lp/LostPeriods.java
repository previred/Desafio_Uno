package cl.previred.lp;

import java.util.TreeSet;

import org.json.simple.JSONObject;

/**
 * Clase principal del sistema
 * @author hector saez
 *
 */
public class LostPeriods {

	/**
	 * Metreodo de ejcucion, donde comienza la ejecución del proyecto
	 * @param args
	 */
	public static void main(String[] args) {
		
		if (args!=null && args.length==2)
		{
			if (args[0].endsWith(Constants.extension) && args[1].endsWith(Constants.extension))
			{
				try {
					FileHandlerService fileHandler=new FileHandlerImpl(args[0],args[1]);
					JSONObject inputJson = fileHandler.readFile();
					if (inputJson!=null) {
						LogicService logic=new LogicImpl(inputJson);
						TreeSet<String> outputDates=logic.getLostPeriods();
						fileHandler.writeFile(inputJson, outputDates);
					}
					else
						System.out.println("No se pudo parsear el archivo de entrada en objeto java");
					
				} catch (Exception e) {
					System.out.println("Error en el programa");
					System.out.println(e.getMessage());
				}
			}
			else
				System.out.println("La extension de los archivos debe ser en formato .json");
		}
		else
			System.out.println("Se necesita dos parametros de entrada, primer argumento: nombre archivo a leer; segundo argumento: nombre archivo a generar");
		
	}

}
