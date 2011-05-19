package usuarios.domain;

import java.util.List;

/**
 * Modela el perfil de un usuario.
 * @author grupo 2
 *
 */
public class Perfil {
	Integer id;
	String descripcion;
	List<Servicio> servicios;
	
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
	public List<Servicio> getServicios() {
		return servicios;
	}
	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}
		
}
