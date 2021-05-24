package com.previred.uno.services.rest;

import com.previred.uno.exceptions.PreviredException;
import com.previred.uno.models.periodos.PeriodosRest;

/**
 *
 * @author pvillar
 */
public interface ObtenerPeriodosService {
    PeriodosRest getPeriodos() throws PreviredException;
}
