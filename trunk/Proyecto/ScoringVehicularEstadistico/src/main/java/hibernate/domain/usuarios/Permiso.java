package hibernate.domain.usuarios;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="permisos")
public class Permiso {
	
	private long id;
	private String descripcion;
	private Set<Usuario> usuarios;
	public void setId(long id) {
		this.id = id;
	}
	
	@Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
	@Column (name="permiso_id")
	public long getId() {
		return id;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getDescripcion() {
		return descripcion;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

    @ManyToMany(
            targetEntity=hibernate.domain.usuarios.Usuario.class,
            cascade={CascadeType.ALL },
            fetch=  FetchType.EAGER
        )
    @JoinTable(
            name="usuario_permiso",
            joinColumns = @JoinColumn( name="permiso_id"),
            inverseJoinColumns = @JoinColumn( name="usuario_id")) 
	public Set<Usuario> getUsuarios() {
		return usuarios;
	}
	
	
    

	public void agregarUsuario(Usuario u){
		usuarios.add(u);
	}

	public void eliminarUsuario(Usuario usuario) {
		usuarios.remove(usuario);
		
	}

}
