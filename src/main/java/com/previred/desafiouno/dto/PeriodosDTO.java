package com.previred.desafiouno.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PeriodosDTO {
	private int id;
	private String fechaCreacion;
	private String fechaFin;
	private List<String> fechas;
}
