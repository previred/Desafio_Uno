package com.previred.desafio.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * the model for transport the data of the request
 */
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataGeneratorResponse implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * the correlative id
     */
    @JsonProperty("id")
    private Integer id;

    /**
     * the lower limit date
     */
    @JsonProperty("fechaCreacion")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    /**
     * the he higher limit date
     */
    @JsonProperty("fechaFin")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    /**
     * the list of dates
     */
    @JsonProperty("fechas")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    List<LocalDate> dateList;

    /**
     * the missing list of dates
     */
    @JsonProperty("fechasFaltantes")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    List<LocalDate> missingDatesList;

    /**
     * order list by date
     */
    public void orderList() {
        if (this.getDateList() != null) {
            Collections.sort(this.getDateList(), new SortByDate());
        }
        if (this.getMissingDatesList() != null) {
            Collections.sort(this.getMissingDatesList(), new SortByDate());
        }
    }

    /**
     * the comparator for sort list by localdate
     */
    static class SortByDate implements Comparator<LocalDate> {
        @Override
        public int compare(LocalDate a, LocalDate b) {
            return a.compareTo(b);
        }
    }
}
