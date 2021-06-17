package cl.hossman.connector.client;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import cl.hossman.connector.constants.ConnectorConstants;
import cl.hossman.connector.exceptions.ClienteRestException;
import org.apache.commons.lang3.StringUtils;

public class ClienteRest {

	Logger log = LoggerFactory.getLogger(ClienteRest.class);

	private Environment env;

	public ClienteRest(Environment env) {
		this.env = env;
	}

	/**
	 * @author Hossman Escobar (H.E)
	 * Conector Simple Para Una ApiRest externa.
	 * @param tipoMetodoHttp = tipoMetodo http a invocar.
	 * @param nombreApiConectar = nombre de la api con la que se va a conectar.
	 *     * 
	 * @throws ApiException */
	public ResponseEntity<String> conectorApiRestExterna(HttpMethod tipoMetodoHttp, String nombreApiConectar) throws ClienteRestException {


		String uri = "";

		if(validaPropiedades(nombreApiConectar)) {
			uri = generardorUri(nombreApiConectar);
		
		} else {
			throw new ClienteRestException(ConnectorConstants.CODE_ERR_PROPERTY , ConnectorConstants.MSG_ERR_PROPERTY);  
		}

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		ResponseEntity<String> result = new ResponseEntity<String>(StringUtils.EMPTY, HttpStatus.SERVICE_UNAVAILABLE);	

		try {
			log.info("Conectandose a: " +  uri);
			result = restTemplate.exchange(uri, tipoMetodoHttp, entity, String.class); 			
		} catch (Exception e) {
			throw new ClienteRestException(ConnectorConstants.CODE_ERR_CONEXION, ConnectorConstants.MSG_ERR_CONEXION,e);  
		}

		return result;

	}

	
	/** @author Hossman Escobar (H.E).
      @param nombreApiConsumir = nombre de la api que queremos validar las propiedades, distintas apis podrian tener otros properties, o se pueden querer incoporar mas validaciones. **/	
	private boolean validaPropiedades(String nomberApiValidar) {

		/* Se logea la Propiedad pero no se tira en el exception hacia capas superiores para no entregar informacion de la property de falla */
		/* H.E Filtro cada una de las properties, para que no vuelva a desarrollo la incidencia y producción pueda configurar */
		if(nomberApiValidar.equals(ConnectorConstants.API_PERIOD)) {
			if(StringUtils.isEmpty(env.getProperty(ConnectorConstants.SERVER))) { /* H.E No se utilizan valores por defecto, para no generar compartimientos automaticos */
				log.error("No esta definida la propiedad " + ConnectorConstants.SERVER + "en el archivo aplication.properties");
				return false;  
			} else if (StringUtils.isEmpty(env.getProperty(ConnectorConstants.PORT)))  {
				log.error("No esta definida la propiedad " + ConnectorConstants.PORT +" en el archivo aplication.properties");
				return false;  
			}                	
			else if(StringUtils.isEmpty(env.getProperty(ConnectorConstants.RESOURCE))) {
				log.error("No esta definida la propiedad" + ConnectorConstants.RESOURCE +" en el archivo aplication.properties");
				return false;  
			}  		
		}
		return true;
	}

	private String generardorUri(final String nomberApiValidar) {

	
		String uriStr = "";
		if(nomberApiValidar.equals(ConnectorConstants.API_PERIOD)) {
			/*H.E Stringbuffer por que tiene mejor perfomance, PD: se que para 3 variables es despreciable */
			StringBuffer uri = new StringBuffer();
			uri.append(env.getProperty(ConnectorConstants.SERVER)).
			append(env.getProperty(ConnectorConstants.PORT)).
			append(env.getProperty(ConnectorConstants.RESOURCE));
			return uri.toString();

		}
		return uriStr;
	}
}
