package com.example.desafio.entity;

import javax.validation.constraints.Min;
import java.util.ArrayList;

public class DesafioResponse {
    Integer id;
    String fechaCreacion;
    String fechaFin;
    ArrayList<String> fechas;
    ArrayList<String> fechasFaltantes;

    public DesafioResponse() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public ArrayList<String> getFechasFaltantes() {
        return fechasFaltantes;
    }

    public void setFechasFaltantes(ArrayList<String> fechasFaltantes) {
        this.fechasFaltantes = fechasFaltantes;
    }

    @Override
    public String toString() {
        return "DesafioResponse{" +
                "id=" + id +
                ", fechaCreacion='" + fechaCreacion + '\'' +
                ", fechaFin='" + fechaFin + '\'' +
                ", fechas=" + fechas +
                ", fechasFaltantes=" + fechasFaltantes +
                '}';
    }
}
