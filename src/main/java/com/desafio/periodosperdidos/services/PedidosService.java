package com.desafio.periodosperdidos.services;

import com.desafio.periodosperdidos.entites.ResponseGDD;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
import java.util.Objects;
import java.util.Optional;

@Service
public class PedidosService implements IPedidosService {

    private final Path storage;
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

    public PedidosService() throws IOException {
        storage = Paths.get("result").toAbsolutePath().normalize();
        Files.createDirectories(storage);
    }

    @Override
    public ResponseEntity<ResponseGDD> getPeriodos() {
        try {
            Optional<ResponseGDD> periodos = obtenerPedidosGDD();

            if (periodos.isPresent()) {
                ResponseGDD fechasEntrada = gson.fromJson(gson.toJson(periodos.get()), ResponseGDD.class);

                LocalDate temp = fechasEntrada.getFechaCreacion();

                do {
                    if (!fechasEntrada.getFechas().contains(temp))
                        fechasEntrada.getFechasFaltantes().add(temp);
                    temp = temp.plusMonths(1);
                } while (temp.isBefore(fechasEntrada.getFechaFin()));

                escribirJSON(periodos.get(), fechasEntrada);

                return new ResponseEntity(fechasEntrada, HttpStatus.OK);
            }

            throw new Exception("No existen períodos");
        } catch (Exception exception) {
            return new ResponseEntity<>(new ResponseGDD(), HttpStatus.BAD_REQUEST);
        }
    }

    private Optional<ResponseGDD> obtenerPedidosGDD() {
        try {
            RestTemplate template = new RestTemplate();
            String urlRemoteService = "http://localhost:8083/periodos/api";

            ResponseEntity<ResponseGDD> response = template.getForEntity(urlRemoteService, ResponseGDD.class);
            if (response.getStatusCode().equals(HttpStatus.OK) && response.hasBody()) {
                return Optional.of(Objects.requireNonNull(response.getBody()));
            }
            throw new Exception("Error en el host remoto GDD");
        } catch (Exception exception) {
            return Optional.empty();
        }
    }

    private void escribirJSON(ResponseGDD entrada, ResponseGDD salida) {
        try {
            Path file = Paths.get("result").toAbsolutePath().normalize();
            Files.createDirectories(file);

            // entrada
            Path targetEntrada = file.resolve("entrada.json");
            FileOutputStream streamEntrada = new FileOutputStream(targetEntrada.toString());
            streamEntrada.write(gson.toJson(entrada).getBytes());
            streamEntrada.close();

            // salida
            Path targetSalida = file.resolve("salida.json");
            FileOutputStream streamSalida = new FileOutputStream(targetSalida.toString());
            streamSalida.write(gson.toJson(salida).getBytes());
            streamSalida.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
