package com.gdd.generadordedatos.dto;

import java.io.Serializable;
import java.util.List;

public class FechaEntradas implements Serializable {

    private String fechaCreacion;
    private String fechaFin;
    private List<String> fechas;

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

    public List<String> getFechas() {
        return fechas;
    }

    public void setFechas(List<String> fechas) {
        this.fechas = fechas;
    }
}
