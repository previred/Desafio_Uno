package com.previred.desafio.periodo.rest;

import com.previred.desafio.periodo.core.InfoPeriodo;
import com.previred.desafio.periodo.bean.Periodo;
import com.previred.desafio.periodo.integracion.GddIntegracion;
import com.previred.desafio.periodo.rest.parser.JsonParser;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 *
 * @author Roderick Rangel
 */
@Path("/fechas")
@Api(value = "fechas", description = "")
public class FechasPeriodoSvr {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value="Consultar fechas en GDD y listar las fechas faltantes del periodo", produces = MediaType.APPLICATION_JSON)
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Respuesta GDD mas fechas faltantes") ,
        @ApiResponse(code = 404, message = "No se obtuvo respuesta de GDD") 
    }) 
    public Response consultarFechas() throws JSONException {
        
        GddIntegracion integracion = new GddIntegracion();
        Periodo periodoConsulta = integracion.consultarPeriodos();
        
        if(periodoConsulta != null)
        {
            InfoPeriodo info = new InfoPeriodo(periodoConsulta);
            List<String> fechasFaltantes = info.listarFechasFaltantes();

            JSONObject jsonObject = JsonParser.getJsonRespuesta(periodoConsulta, fechasFaltantes);
            
            return Response.status(HttpStatus.SC_OK).entity(jsonObject.toString()).build();
        }
        else
        {
            return Response.status(HttpStatus.SC_NOT_FOUND).build();
        }
        
        
    }
}
