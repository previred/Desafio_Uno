package com.desafio.periodosperdidos.services;

import com.desafio.periodosperdidos.config.LocalDateAdapter;
import com.desafio.periodosperdidos.io.ResponsePeriodosPerdidos;
import com.desafio.periodosperdidos.io.ResponseRemotePeriodos;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PeriodosPerdidosServ implements IPeriodosPerdidosServ{

    Logger logger = LoggerFactory.getLogger(PeriodosPerdidosServ.class);
    private final RestTemplate restTemplate = new RestTemplate();
    private final Path fileStorageLocation;
    Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .create();

    public PeriodosPerdidosServ() {
        this.fileStorageLocation = Paths.get("resultado")
                .toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<ResponsePeriodosPerdidos> getPeriodosPerdidos() {
        try{
            logger.info("inicio getPeriodosPerdidos()");
            Optional<ResponseRemotePeriodos> periodos = getRemotePeriodos();
            if (periodos.isPresent()){
                ResponsePeriodosPerdidos periodosPerdidos = ResponsePeriodosPerdidos.builder()
                        .fechaCreacion(periodos.get().getFechaCreacion())
                        .fechaFin(periodos.get().getFechaFin())
                        .fechas(periodos.get().getFechas())
                        .build();
                periodosPerdidos.setFechasFaltantes(new ArrayList<>());
                periodosPerdidos.getFechasFaltantes().addAll(getFechasFaltantes(periodosPerdidos));
                generarArchivoEntrada(periodos.get());
                generarArchivoSalida(periodosPerdidos);
                return new ResponseEntity<>(periodosPerdidos, HttpStatus.OK);
            }
            throw new Exception("No Hay periodos perdidos");
        }catch (Exception ex){
            logger.error(ex.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Optional<ResponseRemotePeriodos> getRemotePeriodos(){

        try{
            logger.info("inicio getRemotePeriodos()");
            String urlRemoteService = "http://localhost:8083/periodos/api";
            ResponseEntity<ResponseRemotePeriodos> response = restTemplate.getForEntity(
                    urlRemoteService,
                    ResponseRemotePeriodos.class);
            if (response.getStatusCode().equals(HttpStatus.OK) && response.hasBody()){
                return Optional.of(Objects.requireNonNull(response.getBody()));
            }
            throw new Exception("ERROR SERVICIO REMOTO");
        }catch (Exception e){
            logger.error(e.getMessage());
            return Optional.empty();
        }
    }

    private List<LocalDate> getFechasFaltantes(ResponsePeriodosPerdidos reqFechas){
        logger.info("inicio getFechasFaltantes()");
        LocalDate iter= reqFechas.getFechaCreacion();
        List<LocalDate> listaIncompleta = reqFechas.getFechas();
        List<LocalDate> listaCompleta = new ArrayList<>();

        listaCompleta.add(reqFechas.getFechaCreacion());

        while(iter.isBefore(reqFechas.getFechaFin())){
            iter = iter.plusMonths(1);
            listaCompleta.add(iter);
        }

        return listaCompleta.stream()
                .filter(dt0 ->{
                    Optional<LocalDate> opDate = listaIncompleta.stream()
                            .filter(dt0::equals)
                            .findFirst();
                    return !opDate.isPresent();
                })
                .collect(Collectors.toList());
    }

    private void generarArchivoEntrada(ResponseRemotePeriodos request){
        logger.info("inicio generarArchivoEntrada()");
        String filename = "req.json";
        String result = gson.toJson(request);
        logger.info(result);
        Path targetLocation = this.fileStorageLocation.resolve(filename);
        try(FileOutputStream fileOutputStream = new FileOutputStream(targetLocation.toString())){
            fileOutputStream.write(result.getBytes());
        }catch (IOException e){
            logger.error(e.getMessage());
        }
    }

    private void generarArchivoSalida(ResponsePeriodosPerdidos request){
        logger.info("inicio generarArchivoSalida()");
        String filename = "resp.json";
        String result = gson.toJson(request);
        logger.info(result);
        Path targetLocation = this.fileStorageLocation.resolve(filename);
        try(FileOutputStream fileOutputStream = new FileOutputStream(targetLocation.toString())){
            fileOutputStream.write(result.getBytes());
        }catch (IOException e){
            logger.error(e.getMessage());
        }
    }
}
