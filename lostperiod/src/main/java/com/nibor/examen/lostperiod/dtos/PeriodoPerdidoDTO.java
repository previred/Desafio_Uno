package com.nibor.examen.lostperiod.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PeriodoPerdidoDTO extends PeriodoGeneradoDTO {
    private List<String> fechasFaltantes;

}
