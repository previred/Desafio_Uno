package com.previred.desafio.periodo.integracion;

import com.previred.desafio.periodo.client.GddClient;
import com.previred.desafio.periodo.bean.Periodo;
import com.previred.desafio.periodo.dto.RespuestaPeriodo;
import com.previred.desafio.periodo.util.PropertyUtil;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Roderick Rangel
 */
public class GddIntegracion implements IGdd{
    private final static Logger LOG = Logger.getLogger(GddIntegracion.class.getName());
    
    private static final int TO_READ_DEFAULT = 5000;
    private static final int TO_CONNECT_DEFAULT = 5000;
    
    private final String url;
    private final int toRead;
    private final int toConnect;
    
    public GddIntegracion()
    {
        this.url = PropertyUtil.getProperty("gdd.endpoint");
        LOG.log(Level.INFO, "Endpoint GDD: {0}", this.url);
        
        this.toRead = (PropertyUtil.getProperty("gdd.http.read") == null) ? TO_READ_DEFAULT : Integer.parseInt(PropertyUtil.getProperty("gdd.http.read"));
        this.toConnect = (PropertyUtil.getProperty("gdd.http.connect") == null) ? TO_CONNECT_DEFAULT : Integer.parseInt(PropertyUtil.getProperty("gdd.http.connect"));
    }

    @Override
    public Periodo consultarPeriodos() 
    {        
        GddClient cliente = new GddClient(this.url, this.toRead, this.toConnect);
        
        RespuestaPeriodo respuesta = cliente.consultarGdd();
        
        if(respuesta.getResponseCode() == 200)
        {
            Periodo periodo = new Periodo();
            periodo.setId(respuesta.getId());
            periodo.setFechaCreacion(respuesta.getFechaCreacion());
            periodo.setFechaFin(respuesta.getFechaFin());
            periodo.setFechas(respuesta.getFechas());
            
            return periodo; 
        }
        else
            LOG.log(Level.WARNING, String.format("Servicio GDD - ResponseCode {0}: Msg {1}", respuesta.getResponseCode(), respuesta.getMsgRespuesta()));
        
        return null;
    }
    
}
