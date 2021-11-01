package com.edenred.exercise.one.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class GddResponseDto implements Serializable {

    public long id;
    @JsonProperty("fechaCreacion")
    public String dateCreate;
    @JsonProperty("fechaFin")
    public String dateEnd;
    @JsonProperty("fechas")
    public List<String> dates;

}
