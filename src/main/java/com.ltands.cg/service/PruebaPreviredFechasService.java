package com.ltands.cg.service;

import com.ltands.cg.request.DatosEntrada;
import com.ltands.cg.response.DatosSalida;



public interface PruebaPreviredFechasService {

    DatosSalida obtieneDetalleFechas(DatosEntrada datosEntrada) throws Exception;


}
