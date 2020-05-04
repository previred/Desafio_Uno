package cl.previred.arquitectura.seleccion.lagunas.proceso;

import cl.previred.arquitectura.seleccion.lagunas.exceptions.EntradaException;

public class CanalEntrada implements InputData  {

	private InputVO datoi;
	
	public CanalEntrada(InputVO datoi) {
		this.datoi=datoi;
	}

	@Override
	public InputVO getData() throws EntradaException {
		return datoi;
	}

}
