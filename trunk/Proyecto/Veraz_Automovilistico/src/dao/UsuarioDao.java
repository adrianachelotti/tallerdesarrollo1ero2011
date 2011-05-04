package dao;

import application.Usuario;

//TODO: falta la conexion a la bbdd
public class UsuarioDao {

	public static boolean esUsuarioAutorizado(Usuario usuario) {
		if (usuario.getNombre().equals("usuario")){
			return true;
		}
		return false;
	}

	
}
