package com.previred.desafio.controller;

import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * common functions and atributes for the other controllers
 */
public class CommonController {

    /**
     * the lower limit date
     */
    @Value("${limit.start.year}")
    private String startDate;

    /**
     * the he higher limit date
     */
    @Value("${limit.end.year}")
    private String endDate;

    protected LocalDate getStartDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(startDate, formatter);
    }

    protected LocalDate getEndDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(endDate, formatter);
    }
}
