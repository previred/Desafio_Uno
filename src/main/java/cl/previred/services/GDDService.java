package cl.previred.services;

import java.io.IOException;

import cl.previred.entity.IncompletePeriod;

public interface GDDService {
	
	IncompletePeriod getIncompletePeriod() throws IOException;
}
