package cl.previred.desafio.uno.vo;

import java.time.LocalDate;
import java.util.List;

import cl.previred.desafio.uno.dto.Periodo;

public class PeriodoFaltante extends Periodo {

	private static final long serialVersionUID = 1390691042254771228L;

	private List<LocalDate> fechasFaltantes;

	public PeriodoFaltante() {
		super();
	}

	public PeriodoFaltante(Long id, LocalDate fechaCreacion, LocalDate fechaFin, List<LocalDate> fechas,
			List<LocalDate> fechasFaltantes) {
		super(id, fechaCreacion, fechaFin, fechas);
		this.fechasFaltantes = fechasFaltantes;
	}

	public List<LocalDate> getFechasFaltantes() {
		return fechasFaltantes;
	}

	public void setFechasFaltantes(List<LocalDate> fechasFaltantes) {
		this.fechasFaltantes = fechasFaltantes;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("PeriodoFaltante [");
		sb.append("fechasFaltantes=" + fechasFaltantes);
		sb.append("]");
		return sb.toString();
	}
}
