package com.example.desafio_uno.entity;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


@Data
public class PeriodosPerdidos extends Previred implements Serializable {
    private List<LocalDate> fechasFaltantes;
}
