package hibernate.domain.usuarios;

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
	private boolean activado;
	
		
	
		
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

	


	

	
		
	public boolean esCliente(){
		return this.getClass().getSimpleName().compareTo("Cliente")==0;
	}
	
	public boolean esAdministrador(){
		return this.getClass().getSimpleName().compareTo("Administrador")==0;
	}
	
	public boolean esOperador(){
		return this.getClass().getSimpleName().compareTo("Operador")==0;
	}

	public void setActivado(boolean activado) {
		this.activado = activado;
	}

	public boolean isActivado() {
		return activado;
	}



}
