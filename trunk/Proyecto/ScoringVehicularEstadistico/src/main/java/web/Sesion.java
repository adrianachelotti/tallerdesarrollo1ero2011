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
	
	private Boolean signedin;
	
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
		if(usuario==null) return false;
		signedin=usuario.isActivado();
		return (usuario.isActivado());
				
	}


	public boolean signedIn()
	{
		return signedin;
	}

	public Usuario getUsuario()
	{
		return usuario;
	}
	
	public Boolean isSignedIn(){
		return signedin;
	}
	
	public void setSignedIn(Boolean signedin){
		this.signedin=signedin;
	}

	
}
