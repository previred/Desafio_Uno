package cl.previred.arquitectura.seleccion.lagunas.proceso;

import cl.previred.arquitectura.seleccion.lagunas.exceptions.SalidaException;

public class CanalSalida implements OutputData{

    private OutputVO datoso;

	@Override
	public void putLagunas(OutputVO datos) throws SalidaException {
		this.datoso=datos;
	}
	
	public OutputVO getDatos()
	{
		return this.datoso;
	}

}
