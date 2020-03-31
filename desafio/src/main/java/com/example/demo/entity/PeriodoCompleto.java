package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PeriodoCompleto implements Serializable {
    @JsonProperty("id")
    @ApiModelProperty
    private int id = 0;

    @JsonProperty("fechaCreacion")
    @ApiModelProperty
    private LocalDate fechaCreacion = null;

    @JsonProperty("fechaFin")
    @ApiModelProperty
    private LocalDate fechaFin = null;

    @JsonProperty("fechas")
    @ApiModelProperty
    private ArrayList<LocalDate> fechas = null;

    @JsonProperty("fechasFaltantes")
    @ApiModelProperty
    private ArrayList<LocalDate> fechasFaltantes = null;

    public PeriodoCompleto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public List<LocalDate> getFechas() {
        return fechas;
    }

    public void setFechas(ArrayList<LocalDate> fechas) {
        this.fechas = fechas;
    }

    public List<LocalDate> getFechasFaltantes() {
        return fechasFaltantes;
    }

    public void setFechasFaltantes(ArrayList<LocalDate> fechasFaltantes) {

        this.fechasFaltantes = fechasFaltantes;
    }
}
