package com.previred.prueba.service;

import com.previred.prueba.request.DatosEntrada;
import com.previred.prueba.response.DatosSalida;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


@Service(value = "PruebaPreviredFechasService")
@Transactional
public class PruebaPreviredFechasServiceImpl implements PruebaPreviredFechasService {

    private static final Logger log = Logger.getLogger(PruebaPreviredFechasServiceImpl.class);

    @Override
    public DatosSalida obtieneDetalleFechas(DatosEntrada datosEntrada) throws Exception {
        try{

            //        Agrego la lista de fechas a un map (este paso es par facilitar mas abajo la busqueda directo por el id)
            Map fechasProvenientesServicio = new HashMap();
            for(String fechas : datosEntrada.getFechas()){
                fechasProvenientesServicio.put(fechas, fechas);
            }

            List<String> fechasEncontradasReales = new ArrayList();
            Date fechaFin = formatFechaStringADate(datosEntrada.getFechaFin(), "yyyy-MM-dd");
            Date fechaCreacion = formatFechaStringADate(datosEntrada.getFechaCreacion(), "yyyy-MM-dd");
            int contadorMaximo = 0;
            //       Recorro las fechas segun los parametros de entrada de inicio y fin
            while (!fechaFin.before(fechaCreacion)) {

                //          Obtengo el numero de dia para corroborar que sea dia 1 segun fecha de inicio
                Calendar fechaRecorrida = Calendar.getInstance();
                fechaRecorrida.setTime(fechaCreacion);
                int dia = fechaRecorrida.get(Calendar.DAY_OF_MONTH);
                //          Formateo la fecha para guardar registro en mismo formato segun el ejemplo dado en la respuesta del servicio
                String fechaFormt = obtienefechaStringSegunFormato(fechaCreacion, "yyyy-MM-dd");

                Calendar cal = Calendar.getInstance();
                cal.setTime(fechaCreacion);
                //            Sumo 1 mes para pasar al siguiente
                cal.add(Calendar.MONTH, 1);

                //            Valido que la fecha de inicio comience comience el dia 1 para determinar si es agregada a la lista
                if(dia != 1){
                    //            si no corresponde al dia 1, seteo el proximo mes que comience desde el dia 1 para agregarlo a la lista
                    cal.set(Calendar.DAY_OF_MONTH, 1);
                } else {
                    fechasEncontradasReales.add(fechaFormt);
                }
                //          Agrego la nueva fecha a consultar
                fechaCreacion = cal.getTime();
                contadorMaximo = contadorMaximo + 1;
                if(contadorMaximo == 101){
                    break;
                }

            }

            //        Preparo una lista para ingresar las fechas faltantes
            List <String> fechasFaltantes = new ArrayList();

            //        Recorro las fechas reales encontradas bajo los criterios de entrada de inicio y fin ya verificados
            for (String fechaEncontrada: fechasEncontradasReales){

//            Verifico a traves de la fechaReal si existe en el listado de fechas entregado por el servicio, si no existe, la agrego a las faltantes
                if(!fechasProvenientesServicio.containsKey(fechaEncontrada)){
                    fechasFaltantes.add(fechaEncontrada);
                }
            }

//            Las ingreso a traves de un contructor al objeto de respuesta
            DatosSalida datosSalida = new DatosSalida(datosEntrada, fechasFaltantes);

            return datosSalida;

        }catch (Exception e){
            log.error(e.toString());
            throw e;

        }
    }


    private String obtienefechaStringSegunFormato(Date fecha, String formatoSalida) {
        String fechaString = "";
        if (fecha == null) {
            return fechaString;
        } else {
            try {
                DateFormat formato = new SimpleDateFormat(formatoSalida);
                fechaString = formato.format(fecha);
                //System.out.println("====================>  Fecha de salida :  "+fechaString);
                return fechaString;
            } catch (Exception e) {
                fechaString = "error: "+e;
                return fechaString;
            }
        }
    }

    private Date formatFechaStringADate(String fechaString, String formatoEntrada)throws Exception {

        if (fechaString == null || fechaString == " ") {
            throw new Exception("Fecha null");
        } else {
            try {
                //        Pasar fecha de String a Date
                Date fechaDate = new Date();
                SimpleDateFormat formatoFecha = new SimpleDateFormat(formatoEntrada);
                fechaDate = formatoFecha.parse(fechaString);
                return fechaDate;
                ///// fin /////
            } catch (Exception e) {
                log.error(e.getMessage());
                throw e;
            }
        }
    }

	}









