package hibernate.domain.usuarios;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="servicios")
public class Servicio {

	private long id;
	private String descripcion;
	private double precio;
	private String unidad;
	public void setId(long id) {
		this.id = id;
	}
	@Id
	public long getId() {
		return id;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public double getPrecio() {
		return precio;
	}
	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}
	public String getUnidad() {
		return unidad;
	}
	
}
