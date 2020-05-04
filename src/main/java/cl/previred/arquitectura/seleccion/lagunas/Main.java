package cl.previred.arquitectura.seleccion.lagunas;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

import cl.previred.arquitectura.seleccion.lagunas.entrada.EntradaEstandard;
import cl.previred.arquitectura.seleccion.lagunas.exceptions.EntradaException;
import cl.previred.arquitectura.seleccion.lagunas.exceptions.SalidaException;
import cl.previred.arquitectura.seleccion.lagunas.salida.SalidaEstandard;
import cl.previred.arquitectura.seleccion.lagunas.proceso.BuscaLagunas;

public class Main {

	private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
	
	public static void main(String[] args) {

		EntradaEstandard canalEntrada = new EntradaEstandard();
		SalidaEstandard canalSalida = new SalidaEstandard();

		try {
			BuscaLagunas.busca(canalEntrada, canalSalida);
		} catch (EntradaException e) {
			LOGGER.severe("Ocurrio un error en la entrada.");
			LOGGER.severe(Main.getStackTrace(e));
		} catch (SalidaException e) {
			LOGGER.severe("Ocurrio un error en la salida.");
			LOGGER.severe(Main.getStackTrace(e));
		}

	}
	
	public static String getStackTrace(Exception e) 
	{
		StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        return sw.toString();
	}
}
