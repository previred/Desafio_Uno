package com.previred.uno.models.periodos;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * Clase que representa un Periodo, utilizada como respuesta de MS
 *
 * @author pvillar
 */

@ApiModel(description = "Objeto que representa un Periodo.")
public class Periodos {

    @ApiModelProperty(position = 1, notes = "Identificador Unico", example = "1")
    @JsonProperty("id")
    private Long id;

    @ApiModelProperty(position = 2, notes = "Fecha de inicio de la secuencia", example = "1995-02-01")
    @JsonProperty("fechaCreacion")
    private LocalDate fechaCreacion;

    @ApiModelProperty(position = 3, notes = "Fecha de fin de la secuencia", example = "1996-03-01")
    @JsonProperty("fechaFin")
    private LocalDate fechaFin;

    @ApiModelProperty(position = 4, notes = "Fechas entregadas", example = "[\n" +
            "    \"1995-03-01\",\n" +
            "    \"1995-05-01\",\n" +
            "    \"1995-06-01\",\n" +
            "    \"1995-09-01\",\n" +
            "    \"1996-01-01\"\n" +
            "  ]")
    @JsonProperty("fechas")
    private List<LocalDate> fechas;

    @ApiModelProperty(position = 5,  notes = "Fechas faltantes en entregadas", example = "[\n" +
            "    \"1995-02-01\",\n" +
            "    \"1995-04-01\",\n" +
            "    \"1995-07-01\",\n" +
            "    \"1995-08-01\",\n" +
            "    \"1995-10-01\",\n" +
            "    \"1995-11-01\",\n" +
            "    \"1995-12-01\",\n" +
            "    \"1996-03-01\"\n" +
            "  ]")
    @JsonProperty("fechasFaltantes")
    private List<LocalDate> fechasFaltantes;

    public Periodos() {}

    public Periodos(PeriodosRest wrapper) {
        this.setId(wrapper.getId());
        this.setFechaCreacion(wrapper.getFechaCreacion());
        this.setFechaFin(wrapper.getFechaFin());
        this.setFechas(wrapper.getFechas());
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Periodos periodos = (Periodos) o;
        return Objects.equals(id, periodos.id) && Objects.equals(fechaCreacion, periodos.fechaCreacion) && Objects.equals(fechaFin, periodos.fechaFin) && Objects.equals(fechas, periodos.fechas) && Objects.equals(fechasFaltantes, periodos.fechasFaltantes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fechaCreacion, fechaFin, fechas, fechasFaltantes);
    }

    @Override
    public String toString() {
        return "Periodos{" +
                "id=" + id +
                ", fechaCreacion=" + fechaCreacion +
                ", fechaFin=" + fechaFin +
                ", fechas=" + fechas +
                ", fechasFaltantes=" + fechasFaltantes +
                '}';
    }
}
