package com.gdd.generadordedatos.calculoFecha;

import com.gdd.generadordedatos.dto.FechaCalculadas;
import com.gdd.generadordedatos.dto.FechaEntradas;

import java.text.ParseException;

public interface CalcularService {

    FechaCalculadas getFecha(FechaEntradas fechaEntradas) throws ParseException;
}
