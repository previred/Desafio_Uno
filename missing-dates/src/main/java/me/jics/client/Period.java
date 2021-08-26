package me.jics.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
@EqualsAndHashCode
public class Period {
    private int id;
    @JsonProperty("fechaCreacion")
    private LocalDate creationDate;
    @JsonProperty("fechaFin")
    private LocalDate endDate;
    @JsonProperty("fechas")
    private List<LocalDate> dates;

}
