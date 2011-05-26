package hibernate.domain.usuarios;




import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.*;


import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="usuarios")
@Inheritance(strategy=InheritanceType.JOINED)
public class Usuario {
	
	private Long id;
	@Column(nullable=false, length=50)
	private String username;
	@Column(nullable=false, length=50)
	private String pass;
	
	private String nombre;
	private String apellido;
	
	private String email;
	
	private Servicio servicio;
	
	private String sexo;
	//TODO: estos puede ser un mapeos onetoone
	private String provincia;
	private String localidad;
	private String rubro;
	
	
	private Set<Permiso> permisos = new HashSet<Permiso>();
		
	public Usuario(){
		
	}
	
	public Usuario(String username, String pass, String nombre, String email) {
		this.username=username;
		this.pass=pass;
		this.nombre=nombre;
		this.email=email;
	}

	@Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
	@Column(name = "usuario_id")
	public Long getId() {
		return id;
	}
	
	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPass() {
		return pass;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getApellido() {
		return apellido;
	}

	public void setPermisos(Set<Permiso> permisos) {
		
		this.permisos = permisos;
	}



    @ManyToMany(

            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            
            fetch=  FetchType.EAGER ,

            mappedBy = "usuarios",

            targetEntity = hibernate.domain.usuarios.Permiso.class

        )
	public Set<Permiso> getPermisos() {
		return permisos;
	}

	public void agregarPermiso(Permiso permiso) {
		permisos.add(permiso);
		
	}

	public void eliminarTodosLosPermisos() {
		permisos.clear();
		
		
	}

	public void eliminarPermiso(Permiso permiso) {
		permisos.remove(permiso);
		
	}

	public boolean tienePermiso(long id) {
		Iterator<Permiso> it=permisos.iterator();
		boolean encontrado=false;
		while((!encontrado)&&(it.hasNext())){
			if(it.next().getId()==id) encontrado=true;
		}
		
		return encontrado;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	@OneToOne
	public Servicio getServicio() {
		return servicio;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getSexo() {
		return sexo;
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

	public void setRubro(String rubro) {
		this.rubro = rubro;
	}

	public String getRubro() {
		return rubro;
	}


}
