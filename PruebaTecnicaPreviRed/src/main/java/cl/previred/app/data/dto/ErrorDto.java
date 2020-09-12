package cl.previred.app.data.dto;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "modelo de respuesta de error")
public class ErrorDto implements Serializable {
	
	@ApiModelProperty(notes = "Clase de error", name = "error", required = true, value = "Exception")
	private String error;
	@ApiModelProperty(notes = "fecha actual del error", name = "fecha", required = true, value = "2020-09-12T20:08:10.438+00:00")	
	private Date fecha;
	@ApiModelProperty(notes = "estado del error", name = "status", required = true, value = "200")
	private int status;
	@ApiModelProperty(notes = "mensajes de error interno", name = "mensaje", required = true, value = "Error de conexión a la api externa")
	private String mensaje;
	
	public ErrorDto(String error, Date fecha, int status, String mensaje) {
		super();
		this.error = error;
		this.fecha = fecha;
		this.status = status;
		this.mensaje = mensaje;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
}
