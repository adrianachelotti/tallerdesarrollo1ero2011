package hibernate.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="usuarios")
public class Usuario {
	
	private Long id;
	@Column(nullable=false, length=50)
	private String username;
	@Column(nullable=false, length=50)
	private String pass;
	@Column(nullable=false, length=50)
	private String nombre;
	@Column(nullable=false, length=50)
	private String email;
		
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


}
