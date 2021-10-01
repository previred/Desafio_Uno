package com.nibor.examen.lostperiod.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PeriodoGeneradoDTO {
    private Integer id;
    private String fechaCreacion;
    private String fechaFin;
    private List<String> fechas;

}
