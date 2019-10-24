package com.beltran.anthony.challenge.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Validated
@JsonIgnoreProperties(ignoreUnknown = true)
public class Periodo {
    @JsonProperty("id")
    private Long id = null;

    @JsonProperty("fechaCreacion")
    private LocalDate fechaCreacion = null;

    @JsonProperty("fechaFin")
    private LocalDate fechaFin = null;

    @JsonProperty("fechas")
    @Valid
    private List<LocalDate> fechas = null;

    @JsonProperty("fechasFaltantes")
    @Valid
    private List<LocalDate> fechasFaltantes = null;

    public Periodo id(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Get id
     *
     * @return id
     **/
    @ApiModelProperty(value = "")

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get fechaCreacion
     *
     * @return fechaCreacion
     **/
    @ApiModelProperty(value = "", example = "2019-10-24")

    @Valid

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Get fechaFin
     *
     * @return fechaFin
     **/
    @ApiModelProperty(value = "", example = "2019-10-24")

    @Valid

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * Get fechas
     *
     * @return fechas
     **/
    @ApiModelProperty(value = "", example = "[\"2019-10-24\"]")

    @Valid

    public List<LocalDate> getFechas() {
        return fechas;
    }

    public void setFechas(List<LocalDate> fechas) {
        this.fechas = fechas;
    }

    /**
     * Get fechasFaltantes
     *
     * @return fechasFaltantes
     **/
    @ApiModelProperty(value = "", example = "[null]")

    @Valid

    public List<LocalDate> getFechasFaltantes() {
        return fechasFaltantes;
    }

    public void setFechasFaltantes(List<LocalDate> fechasFaltantes) {
        this.fechasFaltantes = fechasFaltantes;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Periodo periodo = (Periodo) o;
        return Objects.equals(this.id, periodo.id) &&
                Objects.equals(this.fechaCreacion, periodo.fechaCreacion) &&
                Objects.equals(this.fechaFin, periodo.fechaFin) &&
                Objects.equals(this.fechas, periodo.fechas) &&
                Objects.equals(this.fechasFaltantes, periodo.fechasFaltantes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fechaCreacion, fechaFin, fechas, fechasFaltantes);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Periodo {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    fechaCreacion: ").append(toIndentedString(fechaCreacion)).append("\n");
        sb.append("    fechaFin: ").append(toIndentedString(fechaFin)).append("\n");
        sb.append("    fechas: ").append(toIndentedString(fechas)).append("\n");
        sb.append("    fechasFaltantes: ").append(toIndentedString(fechasFaltantes)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
