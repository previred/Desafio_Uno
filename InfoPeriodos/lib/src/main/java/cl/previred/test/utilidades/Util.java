package cl.previred.test.utilidades;

public class Util {
	
	public static synchronized Double tiempoEjecucion(long inicio){
		
		Double time = (double)(System.currentTimeMillis() - inicio);
		
		time /= 1000;
		
		return time;
	}
	
}
