package hibernate;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

import hibernate.domain.usuarios.Cliente;
import hibernate.util.HibernateUtil;

public class AdministradorClientes {

	/*
	 * Obtiene un cliente
	 * 
	 * @param username del cliente
	 * 
	 * @return cliente con username
	 * 
	 * @return null si no existe usuario con ese username
	 */
	@SuppressWarnings("unchecked")
	public static Cliente obtenerCliente(String username) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Cliente> resultado = null;
		Cliente cliente = null;

		Query query = session.createQuery("from Cliente usuario where usuario.username ='"+ username + "'");
		resultado = query.list();
		session.getTransaction().commit();
		if (!resultado.isEmpty())
			cliente = (Cliente) resultado.get(0);
		return cliente;

	}

	
	public static void agregarCliente(Cliente cliente) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(cliente);
		session.flush();
		session.getTransaction().commit();
	}

	/*
	 * Elimina un usuario de la bd
	 * 
	 * @param username del cliente a eliminar de la bd
	 * 
	 * @return true si lo elimina
	 * 
	 * @return false si no puede eliminarlo
	 */
	public static boolean eliminarCliente(String username) {

		Cliente cliente = obtenerCliente(username);
		boolean eliminado = false;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		if (cliente != null) {
			session.delete(cliente);
			eliminado = true;
		}
		session.getTransaction().commit();
		return eliminado;
	}

	/*
	 * Actualiza los datos del cliente
	 * 
	 * @param usuario con atributos ya modificados
	 * 
	 * @return true si logra hacer la modificación con exito
	 * 
	 * @return false si no
	 */
	public static boolean modificarCliente(Cliente cliente) {
		boolean modificado = false;
		if (cliente != null) {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.update(cliente);
			modificado = true;
			session.getTransaction().commit();
		}
		return modificado;
	}

}
