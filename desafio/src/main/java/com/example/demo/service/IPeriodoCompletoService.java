package com.example.demo.service;

import com.example.demo.entity.Periodo;
import com.example.demo.entity.PeriodoCompleto;

public interface IPeriodoCompletoService {
    PeriodoCompleto devolverPeriodoCompleto();
    Periodo devolverPeriodo();
}
