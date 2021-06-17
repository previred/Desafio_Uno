package cl.hossman.facade.constants;
/***
 *  @author Hossman Escobar (H.E)
 *  * 
 */
public  class ApiConstants {
	
	private ApiConstants () {
		
	}
	public static final  String TYPE_ERROR= "error";
	public static final  String TYPE_WARNING = "warning";
	public static final  String TYPE_INFO= "info";
	public static final  String TYPE_DEBUG = "debug";
	public static final  String CODE_ERR_API_PERIODOS= "ERR_CONEXION";
	public static final  String MSG_ERR_API_PERIODOS = "El micro-servicio de periodos falló";
	public static final  String API_PERIOD = "El micro-servicio de periodos falló";
	public static final  String CODE_ERR_BAD_CONTENT= "BAD_CONTENT";
	public static final  String MSG_ERR_BAD_CONTENT = "Cabecera content no válida";
}
