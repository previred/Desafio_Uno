package com.desafio.desafioCayo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.Collections;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FechasGeneradorDTO {

    private int id;
    private String fechaCreacion;
    private String fechaFin;
    private ArrayList<String> fechas;
    private ArrayList<String> fechasFaltantes;

    public FechasGeneradorDTO(int id, String fechaCreacion, String fechaFin, ArrayList<String> fechasFaltantes) {

        this.id = id;
        this.fechaCreacion = fechaCreacion;
        this.fechaFin = fechaFin;
        this.fechasFaltantes = fechasFaltantes;
    }

    public FechasGeneradorDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public ArrayList<String> getFechas() {
        return fechas;
    }

    public void setFechas(ArrayList<String> fechas) {
        this.fechas = (fechas);
    }

    public void setFechas(JsonArray fechas) {
        String act;
        this.fechas = new ArrayList<>();
        String lista_String[];
        for (int i = 0; i < fechas.size(); i++) {
            act = fechas.get(i).getAsString();
            this.fechas.add(act);
        }
    }

    public ArrayList<String> getFechasFaltantes() {
        return fechasFaltantes;
    }

    public void setFechasFaltantes(ArrayList<String> fechasFaltantes) {
        this.fechasFaltantes = fechasFaltantes;
    }

    public String imprimir() {
        Gson gson = new Gson();
        return (gson.toJson(new FechasGeneradorDTO(this.getId(), this.getFechaCreacion(), this.getFechaFin(), this.getFechasFaltantes())));
    }
}
