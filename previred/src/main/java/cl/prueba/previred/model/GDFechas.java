package cl.prueba.previred.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;


import java.util.List;
import java.lang.String;


@Getter
@Setter
@ApiModel(value = "Response from api gd", description = "respuesta servicio")
public class GDFechas {

    @ApiModelProperty(value = "id response", example = "1", required = true)
    @JsonProperty(value = "id", index = 1)
    private int id;

    @ApiModelProperty(value = "Fecha Creacion", example = "2019-10-20", required = true)
    @JsonProperty(value = "fechaCreacion", index = 2)
    private String fechaCreacion;

    @ApiModelProperty(value = "Fecha Fin", example = "2019-10-21", required = true)
    @JsonProperty(value = "fechaFin", index = 3)
    private String fechaFin;

    @ApiModelProperty(value = "Fecha Contenidas", example = "[2019-10-01, 2019-11-01]", required = true)
    @JsonProperty(value = "fechas", index = 4)
    private List <String> fechas;

    @ApiModelProperty(value = "Fecha Faltantes", example = "[2019-10-01, 2019-11-01]", required = true)
    @JsonProperty(value = "fechasFaltantes", index = 5)
    private List <String> fechasFaltantes;

    @Builder
    public GDFechas() {
        this.id = id;
        this.fechaCreacion = fechaCreacion;
        this.fechaFin = fechaFin;
        this.fechas = fechas;
        this.fechasFaltantes = fechasFaltantes;
    }

}
