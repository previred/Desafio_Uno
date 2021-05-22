package com.previred.gdd.rest.model;

import java.util.List;

public class GDD {

    private long id;
    private String fechaCreacion;
    private String fechaFin;
    private List<String> fechas;
    private List<String> fechasFaltantes;

    public GDD() {
    }

    public GDD(long id, String fechaCreacion, String fechaFin) {
        this.id = id;
        this.fechaCreacion = fechaCreacion;
        this.fechaFin = fechaFin;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public List<String> getFechas() {
        return fechas;
    }

    public void setFechas(List<String> fechas) {
        this.fechas = fechas;
    }

    public List<String> getFechasFaltantes() {
        return fechasFaltantes;
    }

    public void setFechasFaltantes(List<String> fechasFaltantes) {
        this.fechasFaltantes = fechasFaltantes;
    }
}
