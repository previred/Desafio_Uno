package com.desafio.models;

import java.util.Date;
import java.util.List;

public class InputModel {
    private int id;
    private Date fechaCreacion;
    private Date fechaFin;
    private List< Date > fechas;


    // Getter Methods

    public int getId() {
        return id;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public List<Date> getFechas() {
        return fechas;
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

    public void setFechas(List<Date> fechas) {
        this.fechas = fechas;
    }
}
