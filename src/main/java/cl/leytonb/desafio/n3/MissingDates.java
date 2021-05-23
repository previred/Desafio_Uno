package cl.leytonb.desafio.n3;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import cl.leytonb.desafio.GeneradorResp;

public class MissingDates extends GeneradorResp {

	@JsonProperty(value = "fechasFaltantes")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private List<LocalDate> missingDates;

	public MissingDates(GeneradorResp resp, List<LocalDate> missingDates) {
		super(resp);
		this.missingDates = missingDates;
	}

	public final List<LocalDate> getMissingDates() {
		return missingDates;
	}

}
