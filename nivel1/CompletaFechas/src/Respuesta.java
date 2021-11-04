
public class Respuesta {
	private int id;
	private String fechaCreacion;
	private String fechaFin;
	private String[] fechasFaltantes;
	
	Respuesta(int vid, String fCrea, String fFin, String[] fech){
		id = vid;
		fechaCreacion = fCrea;
		fechaFin = fFin;
		fechasFaltantes = fech;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	public String[] getFechasFaltantes() {
		return fechasFaltantes;
	}
	public void setFechasFaltantes(String[] fechas) {
		this.fechasFaltantes = fechas;
	}
}
