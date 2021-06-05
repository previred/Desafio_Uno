package com.previred.prueba.service;

import com.previred.prueba.request.DatosEntrada;
import com.previred.prueba.response.DatosSalida;


public interface PruebaPreviredFechasService {

    DatosSalida obtieneDetalleFechas(DatosEntrada datosEntrada) throws Exception;


}
