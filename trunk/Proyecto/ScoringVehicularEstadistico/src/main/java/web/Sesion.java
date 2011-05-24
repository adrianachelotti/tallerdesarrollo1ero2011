package web;

import hibernate.AdministradorUsuarios;
import hibernate.domain.usuarios.Usuario;
import org.apache.wicket.Request;
import org.apache.wicket.protocol.http.WebSession;


/**
 * Session class for signin example. Holds and authenticates users.
 * 
 * @author Jonathan Locke
 */
public final class Sesion extends WebSession
{
	
	private Usuario usuario;
	
	public Sesion(Request request) {
		super(request);
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;
		
	public final boolean authenticate(final String username, final String pass)
	{
		
		if(username==null) return false;
		if(pass==null) return false;
		usuario=AdministradorUsuarios.obtenerUsuarioValido(username, pass);
		return usuario != null;
	}


	public boolean signedIn()
	{
		return usuario != null;
	}

	public Usuario getUsuario()
	{
		return usuario;
	}

	
}
