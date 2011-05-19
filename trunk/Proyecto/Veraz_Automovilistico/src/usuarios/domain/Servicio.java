package usuarios.domain;

/**
 * Modela un servicio de la aplicacion.
 * @author grupo2
 *
 */
public class Servicio {

	Integer id;
	String descripcion;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
		
}
