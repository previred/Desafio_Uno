package cl.previred.app.services.impl;

import java.net.ConnectException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cl.previred.app.client.IGeneradorDeDatos;
import cl.previred.app.data.dto.ResponseDto;
import cl.previred.app.errores.ErrorConexcionApiException;
import cl.previred.app.errores.ErrorGlobalSistema;
import cl.previred.app.services.IFechaService;
import cl.previred.app.utils.Fecha;
import cl.previred.app.utils.Mapper;

@Service
public class FechaServiceImpl implements IFechaService {

	@Autowired(required = true)
	private IGeneradorDeDatos gdd;

	@Value("${url.api.fechas}")
	private String url;

	/**
	 * Método que obtiene las fechas faltantes
	 * 
	 * @return ResponseDto
	 * @exception ErrorGlobalSistema,ErrorConexcionApiException 
	 */
	@Override
	public ResponseDto getFechasRestantes() {
		try {
			/*se hace el llamado al servicio de generador de datos*/
			ResponseDto data = getTransformarToDto(gdd.getDataApi(url));
			
			/*se evalua la data*/
			if(data == null)
				throw new NullPointerException("Ha ocurrido un problema al realizar pasar la información a objeto dto. data:null");
			
			/*se genera la lista de fechas faltantes*/
			data.setFechasFaltante(generarListaFechasFaltantes(data));

			return data;
			
		} catch (IllegalArgumentException | NullPointerException e) {
			throw new ErrorGlobalSistema(e.getMessage());
		} catch (RuntimeException | ConnectException e) {
			throw new ErrorConexcionApiException(url,e.getMessage());
		} catch (Exception e) {
			throw new ErrorGlobalSistema(e.getMessage());
		}
	}

	/**
	 * Método invoca la funcionalidad de transformación
	 * 
	 * @param String data
	 * @return ResponseDto
	 * @exception Exception, NullPointerException
	 */
	private ResponseDto getTransformarToDto(String data) throws Exception, NullPointerException {
		return Mapper.dataToDto(data);
	}

	/**
	 * Método genera la lista completa desde una fecha inicial y fecha final
	 * 
	 * @param ResponseDto response
	 * @return List<String>
	 * @exception Exception, IllegalArgumentException
	 */
	private List<String> generarListaFechasFaltantes(ResponseDto response) throws Exception, IllegalArgumentException{
		return Fecha.listadoFechasFaltantes(response);
	}

}
