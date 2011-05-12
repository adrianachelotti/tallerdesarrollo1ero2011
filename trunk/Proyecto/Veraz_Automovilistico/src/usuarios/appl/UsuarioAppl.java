package usuarios.appl;

import usuarios.domain.UsuarioDO;

//TODO: falta la conexion a la bbdd
public class UsuarioAppl {

	public static boolean esUsuarioAutorizado(UsuarioDO usuario) {
		if (usuario.getUser().equals("usuario")){
			return true;
		}
		return false;
	}

	
}
