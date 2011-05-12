package usuarios.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

public class UsuarioDO {
	String user;
	String password;
	
	public String getUser() {
		return user;
	}
	public void setUser(String usuario) {
		this.user = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}
