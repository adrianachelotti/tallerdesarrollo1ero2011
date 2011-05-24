package hibernate;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

import hibernate.domain.usuarios.Transaccion;

import hibernate.util.HibernateUtil;

public class AdministradorTransacciones {

	/*
	 * Obtiene una transaccion
	 * 
	 * @param id transaccion
	 * 
	 * @return transaccion asociada a id
	 * 
	 * @return null si no existe transaccion con ese username
	 */
	@SuppressWarnings("unchecked")
	public static Transaccion obtenerTransaccion(long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Transaccion> resultado = null;
		Transaccion transaccion = null;

		Query query = session.createQuery("from Transaccion t where t.id ='"+ id + "'");
		resultado = query.list();
		session.getTransaction().commit();
		if (!resultado.isEmpty())
			transaccion = (Transaccion) resultado.get(0);
		return transaccion;

	}

	
	public static void agregarTransaccion(Transaccion transaccion) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(transaccion);
		session.flush();
		session.getTransaction().commit();
	}

	
	public static boolean eliminarTransaccion(long id) {

		Transaccion transaccion = obtenerTransaccion(id);
		boolean eliminado = false;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		if (transaccion != null) {
			session.delete(transaccion);
			eliminado = true;
		}
		session.getTransaction().commit();
		return eliminado;
	}


	public static boolean modificarTransaccion(Transaccion transaccion) {
		boolean modificado = false;
		if (transaccion != null) {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.update(transaccion);
			modificado = true;
			session.getTransaction().commit();
		}
		return modificado;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Transaccion> obtenerTransaccionesDeCliente(String username){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Transaccion> resultado = null;
		

		Query query = session.createQuery("from Transaccion t where t.cliente.username ='"+ username + "' order by fecha");
		resultado = query.list();
		session.getTransaction().commit();
		return resultado;
		
	}

}
