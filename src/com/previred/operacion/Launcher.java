package com.previred.operacion;

import com.previred.app.PeriodosFaltantesAPP;

public class Launcher {

	/**
	 * lanzador de la operacion para calculo de periodos faltantes
	 * @param args
	 */
	public static void main(String[] args) {
		
		PeriodosFaltantesAPP p = new PeriodosFaltantesAPP();
		try {
			p.obtenerPeriodosFaltantes();
		} catch (Exception e) {
			System.out.println("Error de conectividad con el servicio apiPeriodos");
		}
		
	}
	
	
	
	
}
