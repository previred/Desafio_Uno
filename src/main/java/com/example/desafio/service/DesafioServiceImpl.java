package com.example.desafio.service;

import com.example.desafio.entity.DesafioResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.swing.text.DateFormatter;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Service
public class DesafioServiceImpl implements DesafioService{


    @Override
    public DesafioResponse buscarFechaFaltantes() {
        DesafioResponse desafioResponse = new DesafioResponse();
        RestTemplate restTemplate = new RestTemplate();
        ArrayList<String>  listaFechaFaltantes= new ArrayList<>();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        try {
            HttpEntity<String> entity = new HttpEntity<String>(null, headers);
            ResponseEntity<DesafioResponse> response = restTemplate.exchange("http://127.0.0.1:8080/periodos/api", HttpMethod.GET,
                    entity, DesafioResponse.class);

            desafioResponse = response.getBody();

            if (desafioResponse!=null){
                listaFechaFaltantes = fechasFaltantes(desafioResponse.getFechaCreacion(), desafioResponse.getFechaFin(),
                        desafioResponse.getFechas());

                desafioResponse.setFechasFaltantes(listaFechaFaltantes);
            }
        } catch (Exception e){
            // Ocurrió un error
        }

        return desafioResponse;

    }

    private ArrayList<String> fechasFaltantes(String fechaInicio, String fechaFin, ArrayList<String> listFechas){
        ArrayList<String>  listaFechaFaltantes= new ArrayList<>();

        LocalDate localDateInicio = LocalDate.parse(fechaInicio, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate localDateFin = LocalDate.parse(fechaFin, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        int yearIni = localDateInicio.getYear();
        while (yearIni<= localDateFin.getYear()){

            for (int i=1; i<=12; i++){
                LocalDate nuevaFecha = LocalDate.of(yearIni, i, 1);
                Boolean exist=false;

                for (String fecha : listFechas) {
                    LocalDate localfecha = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                    if (localfecha.isEqual(nuevaFecha)){
                        exist=true;
                    }
                }

                if (!exist){
                    if (nuevaFecha.isEqual(localDateInicio) || nuevaFecha.isEqual(localDateFin)){
                     listaFechaFaltantes.add(nuevaFecha.toString());
                    }
                    else if (nuevaFecha.isAfter(localDateInicio) && nuevaFecha.isBefore(localDateFin)){
                        listaFechaFaltantes.add(nuevaFecha.toString());
                    }
                }
            }
            yearIni++;
        }
        return listaFechaFaltantes;
    }

}
