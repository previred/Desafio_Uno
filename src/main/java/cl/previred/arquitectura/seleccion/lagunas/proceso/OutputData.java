package cl.previred.arquitectura.seleccion.lagunas.proceso;

import cl.previred.arquitectura.seleccion.lagunas.exceptions.SalidaException;

/**
 * Esta interfaz es la que debe cumplicar alguna clase que quiera procesar la respuesta del proceso
 * @author Juan Villablanca
 *
 */
public interface OutputData {
	
	/**
	 * Se recibe un objeto con los datos del proceso para realizar alguna tarea.
	 * @param datos
	 * @throws SalidaException
	 */
	void putLagunas(OutputVO datos)  throws SalidaException;

}
