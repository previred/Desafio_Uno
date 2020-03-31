package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.ArrayList;

public class Periodo implements Serializable {
    @JsonProperty("id")
    private Long id = null;

    @JsonProperty("fechaCreacion")
    private String fechaCreacion = null;

    @JsonProperty("fechaFin")
    private String fechaFin = null;

    @JsonProperty("fechas")
    private ArrayList<String> fechas = null;



    public Periodo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        this.fechas = fechas;
    }

}
