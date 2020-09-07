package com.previred.ws.rest.mothcalculator.services.impl;

import com.previred.ws.rest.mothcalculator.client.GDDClient;
import com.previred.ws.rest.mothcalculator.models.Period;
import com.previred.ws.rest.mothcalculator.models.PeriodResponse;
import com.previred.ws.rest.mothcalculator.services.EmptyMonthService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class EmptyMonthServiceImpl implements EmptyMonthService {
    private GDDClient gddClient;

    public EmptyMonthServiceImpl(GDDClient gddClient) {
        this.gddClient = gddClient;
    }

    @Override
    public PeriodResponse validateEmptyMonth() {
        PeriodResponse periodResponse = new PeriodResponse();
        Period period = this.gddClient.getPeriod();
        
        
        long monthsBetween = ChronoUnit.MONTHS.between(period.getFechaCreacion().withDayOfMonth(1),
        		period.getFechaFin().withDayOfMonth(1));
        
        
        List<LocalDate> missingDates = new ArrayList<LocalDate>();
        List<LocalDate> datesCopy =  new ArrayList<LocalDate>(period.getFechas()); 
        LocalDate firstMonth = period.getFechaCreacion();
        
        for(int i=0; i < monthsBetween; i++) {
        	
        	if(datesCopy.contains(firstMonth)) {
        		datesCopy.remove(firstMonth);
        	} else {
        		missingDates.add(firstMonth);
        	}
        	
        	firstMonth = firstMonth.plusMonths(1);
        }
        
        
        periodResponse.setId(period.getId());
        periodResponse.setFechaCreacion(period.getFechaCreacion());
        periodResponse.setFechaFin(period.getFechaFin());
        periodResponse.setFechas(period.getFechas());
        periodResponse.setFechasFaltantes(missingDates);
        
        return periodResponse;
    }
}
