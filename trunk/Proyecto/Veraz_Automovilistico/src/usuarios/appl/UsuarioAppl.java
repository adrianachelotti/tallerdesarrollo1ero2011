package usuarios.appl;

import usuarios.domain.Usuario;

public class UsuarioAppl {

	public static boolean esUsuarioAutorizado(Usuario usuario) {
		if (usuario.getIdentificador().equals("usuario")){
			return true;
		}
		return false;
	}

	
}
