package com.desafio.desafioCayo.bsd.impl;

import com.desafio.desafioCayo.bsd.BSDLeerArchivoInterface;
import com.desafio.desafioCayo.dto.FechasGeneradorDTO;
import com.desafio.desafioCayo.utils.Util;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

@Service
public class BSDLeerArchivo implements BSDLeerArchivoInterface {

    public boolean leerARchivo(String archivo, String archivoSal) {

        FileReader fr = null;
        FechasGeneradorDTO sal = new FechasGeneradorDTO();
        try {
            fr = new FileReader(archivo);
            JsonElement datos = JsonParser.parseReader(fr);

            crearArchivo(archivoSal, generarSalida(dumpJSONElement(datos)));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static FechasGeneradorDTO dumpJSONElement(JsonElement elemento) {

        FechasGeneradorDTO reg = new FechasGeneradorDTO();
        if (elemento.isJsonObject()) {
            reg.setId(elemento.getAsJsonObject().get("id").getAsInt());
            reg.setFechaCreacion(elemento.getAsJsonObject().get("fechaCreacion").getAsString());
            reg.setFechaFin(elemento.getAsJsonObject().get("fechaFin").getAsString());
            reg.setFechas(elemento.getAsJsonObject().get("fechas").getAsJsonArray());
        } else if (elemento.isJsonArray()) {
            // Es un conjunto de valores, que pueden ser elementos simples o compuestos
            // Para cada valor, llamar a dumpJSONElemento(valor)

        } else if (elemento.isJsonPrimitive()) {
            // Es un elemento simple. Determinar si se trata de un valor booleano,
            // un número o cadena de texto

        } else if (elemento.isJsonNull()) {
            System.out.println("Es NULL");
        } else {
            System.out.println("Es otra cosa");
        }

        return reg;
    }

    public FechasGeneradorDTO generarSalida(FechasGeneradorDTO entrada) {

        FechasGeneradorDTO salida = new FechasGeneradorDTO();
        if (entrada != null) {
            salida.setId(entrada.getId());
            salida.setFechaCreacion(entrada.getFechaCreacion());
            salida.setFechaFin(entrada.getFechaFin());
            salida.setFechas(entrada.getFechas());

            if (faltantes(salida)) {
                return salida;
            }
        }
        return salida;
    }

    private Boolean faltantes(FechasGeneradorDTO salida) {

        ArrayList<String> lista = new ArrayList<>();
        String actual;
        String inicio;

        inicio = salida.getFechaCreacion();
        ArrayList<String> fechas2 = salida.getFechas();
        Collections.sort(fechas2);
        for (int i = 0; i < fechas2.size(); i++) {
            actual = fechas2.get(i);

            while (inicio.compareTo(actual) < 0) {
                lista.add(inicio);
                inicio = Util.convertDateToString_YYYYMMDD(Util.sumarRestarDiasFecha(inicio, 1));
            }
            inicio = Util.convertDateToString_YYYYMMDD(Util.sumarRestarDiasFecha(inicio, 1));
        }
        actual = salida.getFechaFin();
        while (inicio.compareTo(actual) <= 0) {
            lista.add(inicio);
            inicio = Util.convertDateToString_YYYYMMDD(Util.sumarRestarDiasFecha(inicio, 1));
        }

        salida.setFechasFaltantes(lista);
        return true;
    }

    private void crearArchivo(String archivo, FechasGeneradorDTO reg) {
        try {
            String ruta = archivo;
            String contenido = reg.imprimir();
            File file = new File(ruta);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(contenido);
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
