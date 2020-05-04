package com.previred.desafio.periodo.dto;

import java.util.List;

/**
 *
 * @author Roderick Rangel
 */
public class RespuestaPeriodo {
    private int responseCode;
    private String msgRespuesta;
    private int id;
    private String fechaCreacion;
    private String fechaFin;
    private List<String> fechas;
    
    public RespuestaPeriodo()
    {
        super();
    }
    
    public RespuestaPeriodo(int responseCode, String msgRespuesta)
    {
        super();
        this.responseCode = responseCode;
        this.msgRespuesta = msgRespuesta;
    }
    
    /**
     * @return the responseCode
     */
    public int getResponseCode() {
        return responseCode;
    }

    /**
     * @param responseCode the responseCode to set
     */
    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    /**
     * @return the msgRespuesta
     */
    public String getMsgRespuesta() {
        return msgRespuesta;
    }

    /**
     * @param msgRespuesta the msgRespuesta to set
     */
    public void setMsgRespuesta(String msgRespuesta) {
        this.msgRespuesta = msgRespuesta;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the fechaCreacion
     */
    public String getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * @param fechaCreacion the fechaCreacion to set
     */
    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * @return the fechaFin
     */
    public String getFechaFin() {
        return fechaFin;
    }

    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * @return the fechas
     */
    public List<String> getFechas() {
        return fechas;
    }

    /**
     * @param fechas the fechas to set
     */
    public void setFechas(List<String> fechas) {
        this.fechas = fechas;
    }

    
}
