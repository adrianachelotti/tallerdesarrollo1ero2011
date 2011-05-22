package domain.usuarios;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class Usuario {
	
	
	private String username;
	private String password;
	private char enabled;
	private Set<Permiso> authorities;
	
	
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Id
	public String getUsername() {
		return username;
	}
	public void setEnabled(char enabled) {
		this.enabled = enabled;
	}
	public char getEnabled() {
		return enabled;
	}
	public void setAuthorities(Set<Permiso> authorities) {
		this.authorities = authorities;
	}
	
    
	@ManyToMany(cascade = CascadeType.ALL)
	public Set<Permiso> getAuthorities() {
		return authorities;
	}
	

}
