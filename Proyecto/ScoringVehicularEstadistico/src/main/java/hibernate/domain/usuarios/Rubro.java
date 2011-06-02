package hibernate.domain.usuarios;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="rubros")
public class Rubro {
	private Integer id;	
	private String descripcion;
	
	
	public Rubro(){
		
	}
	
	public Rubro(String descripcion){
		this.descripcion=descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	@Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
	@Column(name = "rubro_id")
	public Integer getId() {
		return id;
	}

}
