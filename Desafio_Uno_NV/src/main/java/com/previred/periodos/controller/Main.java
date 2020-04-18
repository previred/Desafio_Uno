package com.previred.periodos.controller;

import com.previred.periodos.servicio.PeriodosService;

/**
 *
 * @author Nelson Villamizar
 */
public class Main {
	public static void main(String[] args) {

		PeriodosService periodosService = new PeriodosService();
		FechasFaltantesController app = new FechasFaltantesController(periodosService);

		app.procesar(args);

	}
}
