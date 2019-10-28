package com.previred.desafio.services;

import com.previred.desafio.api.ApiApiController;
import com.previred.desafio.model.Periodos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class PeriodoServiceImpl implements PeriodoService {

    private final static Long ID = 1L;
    private final static String URL = "http://127.0.0.1:8080/periodos/api";
    private static final Logger log = LoggerFactory.getLogger(ApiApiController.class);

    @Autowired
    private RestTemplate restTemplate;

    public Periodos getPeriodosFaltantes(){

        String url = URL;
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        Periodos response = restTemplate.getForObject(url, Periodos.class);

        Periodos periodos = new Periodos();

        periodos.setId(response.getId());
        periodos.setFechaCreacion(response.getFechaCreacion());
        periodos.setFechaFin(response.getFechaFin());
        periodos.setFechas(response.getFechas());

        //Obtiene response de fechas desde el servicio GDD
        List<LocalDate> fechas = response.getFechas();

        //Agrega fechas faltantes
        this.agregaFechasFaltantes(fechas,response);
        periodos.setFechasFaltantes(response.getFechasFaltantes());

        return periodos;
    }

    private void agregaFechasFaltantes(List<LocalDate> fechas, Periodos response){

        //obtiene fecha inicial de creación y Fin
        LocalDate fechaCreacion = response.getFechaCreacion();
        LocalDate fechaFin = response.getFechaFin();

        int totalFechas = fechas.size();
        ArrayList<Object> datosFechas;

        for (int i = 0 ; i < totalFechas - 1; i++ ){

            //Inserta primero las fechas faltantes desde la fecha de creación hasta la primera fecha del response
            if(i == 0 && i < totalFechas - 1){
                datosFechas = this.obtieneDatosFechas(fechaCreacion, fechas.get(i));
                this.insertaDatosFechas((long)datosFechas.get(0), (LocalDate) datosFechas.get(1), response,2);

                datosFechas = this.obtieneDatosFechas(fechas.get(i), fechas.get(i+1));
                this.insertaDatosFechas((long)datosFechas.get(0), (LocalDate) datosFechas.get(1), response,0);

            }else if (i < totalFechas - 1){
                datosFechas = this.obtieneDatosFechas(fechas.get(i), fechas.get(i+1));
                this.insertaDatosFechas((long)datosFechas.get(0), (LocalDate) datosFechas.get(1), response, 0);
            }

        }
        //Finalmente inserta fechas Faltantes desde la ultima fecha del response hasta la fechaFin
        datosFechas = this.obtieneDatosFechas(fechas.get(totalFechas - 1), fechaFin);
        this.insertaDatosFechas((long)datosFechas.get(0), (LocalDate) datosFechas.get(1), response, 1);
    }

    private ArrayList<Object> obtieneDatosFechas(LocalDate fechaIni, LocalDate fechaFin){
        LocalDate tempDate = LocalDate.from(fechaIni);
        long months = tempDate.until(fechaFin, ChronoUnit.MONTHS);
        LocalDate mesActual = fechaIni;

        ArrayList<Object> responseDatos = new ArrayList<>();

        responseDatos.add(months);
        responseDatos.add(mesActual);

        return responseDatos;
    }

    private void insertaDatosFechas(long mes, LocalDate mesActual, Periodos response, int tipo){


        if(tipo == 2 && !response.getFechas().get(0).equals(response.getFechaCreacion())){
            response.addFechasFaltantesItem(response.getFechaCreacion());
        }

        for (int mesIni = 1 ; mesIni < mes ; mesIni++ ){
            LocalDate fechaNext = mesActual;
            response.addFechasFaltantesItem(fechaNext.plusMonths(mesIni));
        }

        if(tipo == 1 && !mesActual.equals(response.getFechaFin())){
            response.addFechasFaltantesItem(response.getFechaFin());
        }
    }
}
