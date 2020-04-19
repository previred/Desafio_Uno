package com.desafio.models;

import java.util.Date;
import java.util.List;

public class OutputModel {
    private int id;
    private Date fechaCreacion;
    private Date fechaFin;
    private List< Date > fechasFaltantes;

    // Getter Methods

    public int getId() {
        return id;
    }

    public Date getFechaCreacion() { return fechaCreacion; }

    public Date getFechaFin() { return fechaFin; }

    public List<Date> getFechasFaltantes() {
        return fechasFaltantes;
    }

    // Setter Methods

    public void setId(int id) {
        this.id = id;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setFechasFaltantes(List<Date> fechas) {
        this.fechasFaltantes = fechas;
    }
}
