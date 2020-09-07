package com.previred.ws.rest.mothcalculator.controllers;

import com.previred.ws.rest.mothcalculator.models.PeriodResponse;
import com.previred.ws.rest.mothcalculator.services.EmptyMonthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MonthCalculator {
    private EmptyMonthService emptyMonthService;

    public MonthCalculator(EmptyMonthService emptyMonthService) {
        this.emptyMonthService = emptyMonthService;
    }

    @GetMapping("/calculate")
    public ResponseEntity<PeriodResponse> periodCalculator() {
        return ResponseEntity.ok(emptyMonthService.validateEmptyMonth());
    }
}
