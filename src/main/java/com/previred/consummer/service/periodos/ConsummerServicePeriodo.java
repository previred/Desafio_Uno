/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.previred.consummer.service.periodos;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sergi
 */
public class ConsummerServicePeriodo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {

            URL url = new URL("http://127.0.0.1:8080/periodos/api");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String output;
            while ((output = br.readLine()) != null) {
                Data data = new Gson().fromJson(output, Data.class);
                System.out.println("fecha creación: " + data.getFechaCreacion());
                System.out.println("fecha fin: " + data.getFechaFin());
                System.out.println("fechas recibidas: " + mostrarFechasRecibidas(data));
                System.out.println("fechas faltantes: " + generadorFechasFaltantes(data));
            }
            conn.disconnect();

        } catch (IOException | RuntimeException e) {
            System.out.println("Exception in NetClientGet:- " + e);
        }
    }

    private static String generadorFechasFaltantes(Data data) {
        try {
            String fechasFaltantes = "";
            Date fechaCreacion = new SimpleDateFormat("yyyy-MM-dd").parse(data.getFechaCreacion());
            Date fechaFin = new SimpleDateFormat("yyyy-MM-dd").parse(data.getFechaFin());
            Date fechaEvaluar = fechaCreacion;

            while (fechaEvaluar.compareTo(fechaFin) != 0) {
                //System.out.println("Evaluando: "+fechaEvaluar);
                Boolean existe = false;
                for (String fecha : data.getFechas()) {
                    Date fecharecibida = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
                    if (fechaEvaluar.compareTo(fecharecibida) == 0) {
                        existe = true;
                    }
                }
                if (!existe) {
                    //System.out.println("Se agrega como fecha faltante: "+fechaEvaluar);
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String strDate = dateFormat.format(fechaEvaluar);
                    fechasFaltantes = fechasFaltantes + "," + strDate;
                }
                fechaEvaluar = sumarRestarMeses(fechaEvaluar, 1);
            }
            return fechasFaltantes.substring(1, fechasFaltantes.length());
        } catch (ParseException ex) {
            Logger.getLogger(ConsummerServicePeriodo.class.getName()).log(Level.SEVERE, null, ex);
            return "Ocurrio un problema al generar fechas faltantes";
        }

    }

    private static String mostrarFechasRecibidas(Data data) {
        String fechasRecibidas = "";
        for (String fecha : data.getFechas()) {
            fechasRecibidas = fechasRecibidas + "," + fecha;
        }
        return fechasRecibidas.substring(1, fechasRecibidas.length());
    }

    private static Date sumarRestarMeses(Date fecha, Integer meses) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.MONTH, meses);
        return calendar.getTime();
    }
}
