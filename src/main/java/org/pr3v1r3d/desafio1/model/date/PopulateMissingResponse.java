package org.pr3v1r3d.desafio1.model.date;

import java.time.LocalDate;
import java.util.List;

public class PopulateMissingResponse {

	private Long id;
	private LocalDate initDate; //1969-03-01
	private LocalDate finalDate; //1970-01-01
	private List<LocalDate> dates; //1969-03-01
	private List<LocalDate> missingDates; //1969-04-01

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getInitDate() {
		return initDate;
	}
	public void setInitDate(LocalDate initDate) {
		this.initDate = initDate;
	}
	public LocalDate getFinalDate() {
		return finalDate;
	}
	public void setFinalDate(LocalDate finalDate) {
		this.finalDate = finalDate;
	}
	public List<LocalDate> getDates() {
		return dates;
	}
	public void setDates(List<LocalDate> dates) {
		this.dates = dates;
	}
	public List<LocalDate> getMissingDates() {
		return missingDates;
	}
	public void setMissingDates(List<LocalDate> missingDates) {
		this.missingDates = missingDates;
	}

}
