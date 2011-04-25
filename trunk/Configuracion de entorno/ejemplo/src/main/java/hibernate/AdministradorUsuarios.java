package hibernate;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import hibernate.domain.Usuario;
import hibernate.util.HibernateUtil;

public class AdministradorUsuarios {

	/*
	 * Obtiene un usuario
	 * 
	 * @param username del usuario a obtener
	 * 
	 * @return usuario con username
	 * 
	 * @return null si no existe usuario con ese username
	 */
	@SuppressWarnings("unchecked")
	public static Usuario obtenerUsuario(String username) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Usuario> resultado = null;
		Usuario Usuario = null;

		Query query = session.createQuery("from Usuario usuario where usuario.username ='"+ username + "'");
		resultado = query.list();
		session.getTransaction().commit();
		if (!resultado.isEmpty())
			Usuario = (Usuario) resultado.get(0);
		return Usuario;

	}

	/*
	 * Obtiene un usuario, si la contraseña y username son correctos
	 * 
	 * @param username del usuario a obtener y validar
	 * 
	 * @param pass contraseña del usuario para obtener y validar
	 * 
	 * @return usuario con username
	 * 
	 * @return null si no existe usuario con ese username, o si no coincide la
	 * contraseña
	 */

	public static Usuario obtenerUsuarioValido(String username, String pass) {
		Usuario usuario = obtenerUsuario(username);
		if (usuario != null) {
			if (usuario.getPass().compareTo(pass) != 0)
				usuario = null;
		}
		return usuario;

	}

	/*
	 * Verifica si existe un usuario en la BD.
	 * 
	 * @param username a consultar
	 * 
	 * @return true si existe
	 * 
	 * @return false si no
	 */
	public static boolean existeUsuario(String username) {
		boolean existe = false;
		Usuario UsuarioAuxiliar = obtenerUsuario(username);
		if (UsuarioAuxiliar != null)
			existe = true;
		return existe;
	}

	/*
	 * Agrega un usuario a la BD
	 * 
	 * @param usuario para agregar a la BD
	 */
	public static void agregarUsuario(Usuario usuario) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(usuario);
		session.flush();
		session.getTransaction().commit();
	}

	/*
	 * Elimina un usuario de la bd
	 * 
	 * @param username del usuario a eliminar de la bd
	 * 
	 * @return true si lo elimina
	 * 
	 * @return false si no puede eliminarlo
	 */
	public static boolean eliminarUsuario(String username) {

		Usuario usuario = obtenerUsuario(username);
		boolean eliminado = false;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		if (usuario != null) {
			session.delete(usuario);
			eliminado = true;
		}
		session.getTransaction().commit();
		return eliminado;
	}

	/*
	 * Actualiza los datos del usuario
	 * 
	 * @param usuario con atributos ya modificados
	 * 
	 * @return true si logra hacer la modificación con exito
	 * 
	 * @return false si no
	 */
	public static boolean modificarUsuario(Usuario usuario) {
		boolean modificado = false;
		if (usuario != null) {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.update(usuario);
			modificado = true;
			session.getTransaction().commit();
		}
		return modificado;
	}

}
