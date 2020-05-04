package cl.previred.arquitectura.seleccion.lagunas.proceso;

import cl.previred.arquitectura.seleccion.lagunas.exceptions.EntradaException;

/**
 * Esta Interfaz es lo que debe implementar la clase que quiera entregar los datos al proceso
 * @author Juan Villablanca
 *
 */
public interface InputData  {
	/**
	 * Entrega un objeto con los datos a analizar.
	 * @return
	 * @throws EntradaException
	 */
	InputVO getData() throws EntradaException;
}
