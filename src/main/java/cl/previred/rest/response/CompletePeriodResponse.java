package cl.previred.rest.response;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import cl.previred.entity.IncompletePeriod;
import cl.previred.utils.AppUtils;

@JsonPropertyOrder({ "id", "fechaCreacion", "fechaFin", "fechas", "fechasFaltantes"})
public class CompletePeriodResponse extends IncompletePeriod{

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private List<Date> fechasFaltantes;

	
	public List<Date> getFechasFaltantes() {
		return fechasFaltantes;
	}

	public void setFechasFaltantes(List<Date> fechasFaltantes) {
		this.fechasFaltantes = fechasFaltantes;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Periodo [id=");
		builder.append(getId());
		builder.append(", fechaCreacion=");
		builder.append(AppUtils.getDatesString(getFechaCreacion()));
		builder.append(", fechaFin=");
		builder.append(AppUtils.getDatesString(getFechaFin()));
		builder.append(", fechasGeneradas=\n");

		List<String> fecSts = AppUtils.getDatesString(getFechas());

		for (String fecSt : fecSts) {
			builder.append(fecSt);
			builder.append("\n");
		}
		builder.append(", fechasFaltantes=\n");

		List<String> fecFts = AppUtils.getDatesString(fechasFaltantes);

		for (String fecFt : fecFts) {
			builder.append(fecFt);
			builder.append("\n");
		}

		builder.append("]");
		return builder.toString();
	}

}