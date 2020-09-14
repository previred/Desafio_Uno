package com.previred.apifechasfaltantes.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.List;

@Validated
public class FechasFaltantesDTO extends PeriodoDTO {

    @JsonProperty("fechasFaltantes")
    private List<LocalDate> fechasFaltantes;

    @ApiModelProperty(value = "")
    public List<LocalDate> getFechasFaltantes() {
        return fechasFaltantes;
    }

    public void setFechasFaltantes(List<LocalDate> fechasFaltantes) {
        this.fechasFaltantes = fechasFaltantes;
    }


}
