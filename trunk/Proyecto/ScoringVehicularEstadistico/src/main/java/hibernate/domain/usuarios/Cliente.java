package hibernate.domain.usuarios;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="clientes")
public class Cliente extends Usuario{
	
	private Servicio servicio;
	
	

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	
	@OneToOne
	public Servicio getServicio() {
		return servicio;
	}


	

}
