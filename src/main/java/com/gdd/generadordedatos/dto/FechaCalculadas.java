package com.gdd.generadordedatos.dto;

import java.io.Serializable;
import java.util.List;

public class FechaCalculadas implements Serializable {

    private String fechaCreacion;
    private String fechaFin;
    private List<String> fechasRecibidas;
    private List<String> fechasFaltantes;

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

    public List<String> getFechasRecibidas() {
        return fechasRecibidas;
    }

    public void setFechasRecibidas(List<String> fechasRecibidas) {
        this.fechasRecibidas = fechasRecibidas;
    }

    public List<String> getFechasFaltantes() {
        return fechasFaltantes;
    }

    public void setFechasFaltantes(List<String> fechasFaltantes) {
        this.fechasFaltantes = fechasFaltantes;
    }
}
