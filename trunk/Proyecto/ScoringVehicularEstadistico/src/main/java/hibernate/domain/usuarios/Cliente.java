package hibernate.domain.usuarios;


import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="clientes")
public class Cliente extends Usuario{
	
	private String tipoDocumento;
	private Integer documento;
	private Date fechaNacimiento;
	private String provincia;
	private String localidad;
	private String sexo;
	private Set<Rubro> rubros=new HashSet<Rubro>();
	private Servicio servicio;
	
	

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	
	@OneToOne
	public Servicio getServicio() {
		return servicio;
	}


	
	public void setRubros(Set<Rubro> rubros) {
		this.rubros = rubros;
	}

	@ManyToMany
	public Set<Rubro> getRubros() {
		return rubros;
	}


	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}


	public String getTipoDocumento() {
		return tipoDocumento;
	}


	public void setDocumento(Integer documento) {
		this.documento = documento;
	}


	public Integer getDocumento() {
		return documento;
	}


	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}


	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}


	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}


	public String getProvincia() {
		return provincia;
	}


	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}


	public String getLocalidad() {
		return localidad;
	}


	public void setSexo(String sexo) {
		this.sexo = sexo;
	}


	public String getSexo() {
		return sexo;
	}


	public void agregarRubro(Rubro rubro) {
		rubros.add(rubro);
		
	}



	

}
