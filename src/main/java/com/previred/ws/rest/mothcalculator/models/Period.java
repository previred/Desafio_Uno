package com.previred.ws.rest.mothcalculator.models;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class Period {
	
	private Long id;
    private LocalDate fechaCreacion;
    private LocalDate fechaFin;
    private List<LocalDate> fechas;
    
}
