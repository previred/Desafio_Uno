package cl.previred.services;

import java.io.IOException;
import java.text.ParseException;

import cl.previred.rest.response.CompletePeriodResponse;

public interface GDDCompleteService {

	CompletePeriodResponse getCompletePeriod() throws IOException, ParseException;
}
