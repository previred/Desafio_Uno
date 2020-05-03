package com.hossman.periodos.cliente;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.hossman.periodos.swagger.codegen.exception.ApiException;
import com.hossman.periodos.swagger.codegen.exception.ClienteRestException;
import com.hossman.periodos.utils.ApiConstants;

public class PeriodoRestClient {

	Logger log = LoggerFactory.getLogger(PeriodoRestClient.class);

	private Environment env;

	public PeriodoRestClient(Environment env) {
		this.env = env;
	}

	/**
	 * @author Hossman Escobar (H.E)
	 * Conector Simple Para Una ApiRest externa
	 * @param tipoMetodoHttp = tipoMetodo http a invocar
	 * @param nombreApiConectar = nombre de la api con la que se va a conectar
	 *     * 
	 * @throws ApiException */
	public ResponseEntity<String> conectorApiRestExterna(HttpMethod tipoMetodoHttp, String nombreApiConectar) throws ClienteRestException {


		String uri = "";

		if(validaPropiedades(nombreApiConectar)) {
			uri = generardorUri(nombreApiConectar);
		
		} else {
			throw new ClienteRestException( "Api no configurada");  
		}

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);


		ResponseEntity<String> result = null;

		try {
			log.info("Conectandose a: " +  uri);
			result = restTemplate.exchange(uri, tipoMetodoHttp, entity, String.class); 			
		} catch (Exception e) {
			log.error("Error al conectarse a la API externa",e);
			throw new ClienteRestException("Error al conectarse a la API externa");  
		}

		return result;

	}

	// Comentarios en verdes, para que no sea detectado como SMELL CODE, por algun medidor de metricas de calidad, ya que metodo privado no se documenta.
	/* @author Hossman Escobar (H.E)
      @param nombreApiConsumir = nombre de la api que queremos validar las propiedades, distintas apis podrian tener otros properties, o se pueden querer incoporar mas validaciones*/	
	private boolean validaPropiedades(String nomberApiValidar) {

		/* H.E Filtro cada una de las properties, para que no vuelva a desarrollo la incidencia y producción pueda configurar */
		if(nomberApiValidar.equals(ApiConstants.API_PERIOD)) {
			if(env.getProperty(ApiConstants.SERVER) == null) { /* H.E No se utilizan valores por defecto, para no generar compartimientos automaticos */
				log.error("No esta definida la propiedad " + ApiConstants.SERVER + "en el archivo aplication.properties");
				return false;  
			} else if (env.getProperty(ApiConstants.PORT) == null)  {
				log.error("No esta definida la propiedad " + ApiConstants.PORT +" en el archivo aplication.properties");
				return false;  
			}                	
			else if(env.getProperty(ApiConstants.RESOURCE) == null ) {
				log.error("No esta definida la propiedad" + ApiConstants.RESOURCE +" en el archivo aplication.properties");
				return false;  
			}  		
		}
		/* H.E Agregar condiciones otras APIS */
		return true;
	}

	private String generardorUri(final String nomberApiValidar) {

		String uriStr = "";
		if(nomberApiValidar.equals(ApiConstants.API_PERIOD)) {
			/*H.E Stringbuffer por que tiene mejor perfomance, PD: se que para 3 variables es despreciable */
			StringBuffer uri = new StringBuffer();
			uri.append(env.getProperty("api-periodos-server")).
			append(env.getProperty("api-periodos-port")).
			append(env.getProperty("api-periodos-periodos"));
			return uri.toString();

		}
		/* H.E Agregar condiciones otras APIS */
		return uriStr;
	}
}
