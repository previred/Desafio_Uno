package com.previred.periodosPerdidos.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matias Arce on 11/23/2019.
 */
@ApiModel(description = "Detalles de Periodo")
@JsonPropertyOrder({ "id", "fechaCreacion", "fechaFin" ,"fechas","fechasFaltantes"})
public class Periodo {

    @ApiModelProperty(notes = "ID unico")
    @JsonProperty("id")
    private Long id = null;

    @ApiModelProperty(notes = "Fecha inicial rango")
    @JsonProperty("fechaCreacion")
    private LocalDate fechaCreacion = null;

    @ApiModelProperty(notes = "fecha final rango")
    @JsonProperty("fechaFin")
    private LocalDate fechaFin = null;

    @ApiModelProperty(notes = "fechas aleatorias")
    @JsonProperty("fechas")
    private List<LocalDate> fechas = null;

    @ApiModelProperty(notes = "fechas perdidas")
    @JsonProperty("fechasFaltantes")
    private List<LocalDate> fechasFaltantes = null;

    public Periodo() {
        this.fechaCreacion=null;
        this.fechaFin=null;
        this.fechas=new ArrayList<LocalDate>();
        this.fechasFaltantes=new ArrayList<LocalDate>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public void setFechas(List<LocalDate> fechas) {
        this.fechas = fechas;
    }

    public List<LocalDate> getFechasFaltantes() {
        return fechasFaltantes;
    }

    public void setFechasFaltantes(List<LocalDate> fechasFaltantes) {
        this.fechasFaltantes = fechasFaltantes;
    }
}
