package cl.previred.desafio.uno.nivel2.service;

import cl.previred.desafio.uno.nivel2.dto.DesafioUnoFechasDTO;
import cl.previred.desafio.uno.nivel2.exceptions.DesafioUnoInformedException;

/**
 * 
 * @author <a href="mailto:marco.cerda.veas@gmail.com>Marco Cerda Veas</a>
 *
 *
 */
public interface OneLevelCallGDDService {

	/**
	 * Método encargado de leer un archivo json dado su nombre y retornar una
	 * instancia de su representación en clase
	 * 
	 * @param pathFile Nombre del archivo a leer
	 * @return
	 * @throws DesafioUnoInformedException
	 */
	public DesafioUnoFechasDTO callGetGDD() throws DesafioUnoInformedException;

}
