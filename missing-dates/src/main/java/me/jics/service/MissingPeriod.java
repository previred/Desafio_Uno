package me.jics.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Schema(name="MissingPeriod", description="Expected result proposed by the challenge")
public class MissingPeriod {
    private int id;
    @JsonProperty("fechaCreacion")
    private LocalDate creationDate;
    @JsonProperty("fechaFin")
    private LocalDate endDate;
    @JsonProperty("fechas")
    private List<LocalDate> dates;
    @JsonProperty("fechasFaltantes")
    private List<LocalDate> missingDates;
}
