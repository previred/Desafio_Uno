package com.previred.desafiouno.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PeriodosPerdidosDTO extends PeriodosDTO{
	private List<String> fechasFaltantes;

	public PeriodosPerdidosDTO(PeriodosDTO periodosDTO, List<String> fechasFaltantes){
		super(periodosDTO.getId(), periodosDTO.getFechaCreacion(), periodosDTO.getFechaFin(), periodosDTO.getFechas());
		this.fechasFaltantes = fechasFaltantes;
	}
}
