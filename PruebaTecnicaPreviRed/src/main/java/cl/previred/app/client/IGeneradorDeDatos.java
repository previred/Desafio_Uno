package cl.previred.app.client;

import java.net.ConnectException;

public interface IGeneradorDeDatos {
	
	public String getDataApi(String url) throws Exception, RuntimeException, ConnectException;

}
