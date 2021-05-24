package com.previred.uno.services;

import com.previred.uno.exceptions.PreviredException;
import com.previred.uno.models.periodos.Periodos;

/**
 *
 * @author pvillar
 */
public interface PeriodosService {
     Periodos obtenerPeriodos() throws PreviredException;
}
