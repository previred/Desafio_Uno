package com.previred.desafio.periodo.client;

import com.previred.desafio.periodo.dto.RespuestaPeriodo;
import com.previred.desafio.periodo.parser.JsonParser;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * Objeto cliente que consume el servicio GDD
 * @author Roderick Rangel
 * @version v1
 */
public class GddClient {
    private final static Logger LOG = Logger.getLogger(GddClient.class.getName());
    
    private final String urlEndPoint;
    private final int timeoutRead;
    private final int timeoutConnect;
    
    public GddClient(String urlEndPoint, int timeoutRead, int timeoutConnect)
    {
        this.urlEndPoint = urlEndPoint;
        this.timeoutConnect = timeoutConnect;
        this.timeoutRead = timeoutRead;
    }
    
    public RespuestaPeriodo consultarGdd()
    {
        RespuestaPeriodo respuesta = null;
            
        try {
            RequestConfig config = RequestConfig.custom()
                    .setConnectTimeout(this.timeoutConnect)
                    .setConnectionRequestTimeout(this.timeoutConnect)
                    .setSocketTimeout(this.timeoutRead).build();
            
            HttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
            
            HttpGet request = new HttpGet(this.urlEndPoint);
            request.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
            request.addHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
            
            HttpResponse response = httpClient.execute(request);
            
            HttpEntity responseEntity = response.getEntity();
            
            //Respuesta OK
            if(response.getStatusLine().getStatusCode() == 200)
            {
                if (responseEntity != null) {
                    String responseString = EntityUtils.toString(responseEntity);
                    respuesta = JsonParser.getRespuestaFecha(responseString);
                }
            }
            else
            {
                //Respuesta Error
                respuesta = new RespuestaPeriodo(response.getStatusLine().getStatusCode(),response.getStatusLine().getReasonPhrase());
            }
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }    
        
        return respuesta;        
    }    
}
