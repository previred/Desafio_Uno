package com.previred.dataaggregator.service;

import com.previred.dataaggregator.client.PeriodosClient;

public class PeriodsService {
	private final PeriodosClient client;

	public PeriodsService(PeriodosClient client) {
		super();
		this.client = client;
	}
	
	public Periodo getPeriods() {
		return this.client.getPeriods();
	}
}
