package com.aleatay.previred.model;

import com.fasterxml.jackson.annotation.*;

import java.util.List;

/**
 * @author Alexis Rivas
 * @date 03-09-2020
 */
@JsonPropertyOrder({ "id", "fechaCreacion", "fechaFin", "fechas", "fechasFaltantes" })
public class MissingDateModel {

    @JsonProperty("id")
    private int id;

    @JsonProperty("fechaCreacion")
    private String startDate;

    @JsonProperty("fechaFin")
    private String endDate;

    @JsonProperty("fechas")
    private List<String> inputsDates;

    @JsonProperty("fechasFaltantes")
    private List<String> missingDates;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<String> getInputsDates() {
        return inputsDates;
    }

    public void setInputsDates(List<String> inputsDates) {
        this.inputsDates = inputsDates;
    }

    public List<String> getMissingDates() {
        return missingDates;
    }

    public void setMissingDates(List<String> missingDates) {
        this.missingDates = missingDates;
    }
}
