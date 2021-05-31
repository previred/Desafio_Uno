package cl.previred.desafio.uno.nivel1.service;

import cl.previred.desafio.uno.nivel1.dto.DesafioUnoFechasDTO;
import cl.previred.desafio.uno.nivel1.exceptions.DesafioUnoInformedException;

/**
 * 
 * @author <a href="mailto:marco.cerda.veas@gmail.com>Marco Cerda Veas</a>
 *
 *
 */
public interface OneLevelReaderFileService {

	/**
	 * Método encargado de leer un archivo json dado su nombre y retornar una
	 * instancia de su representación en clase
	 * 
	 * @param pathFile Nombre del archivo a leer
	 * @return
	 * @throws DesafioUnoInformedException
	 */
	public DesafioUnoFechasDTO readFile(String pathFile) throws DesafioUnoInformedException;

}
