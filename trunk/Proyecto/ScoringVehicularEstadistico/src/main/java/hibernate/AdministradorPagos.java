package hibernate;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

import hibernate.domain.usuarios.Pago;

import hibernate.util.HibernateUtil;

public class AdministradorPagos {

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
	public static Pago obtenerPago(long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Pago> resultado = null;
		Pago transaccion = null;

		Query query = session.createQuery("from Pago t where t.id ='"+ id + "'");
		resultado = query.list();
		session.getTransaction().commit();
		if (!resultado.isEmpty())
			transaccion = (Pago) resultado.get(0);
		return transaccion;

	}

	
	public static void agregarPago(Pago transaccion) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(transaccion);
		session.flush();
		session.getTransaction().commit();
	}

	
	public static boolean eliminarPago(long id) {

		Pago transaccion = obtenerPago(id);
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


	public static boolean modificarPago(Pago transaccion) {
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
	public static List<Pago> obtenerPagosDeCliente(String username){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Pago> resultado = null;
		

		Query query = session.createQuery("from Pago t where t.cliente.username ='"+ username + "' order by fecha");
		resultado = query.list();
		session.getTransaction().commit();
		return resultado;
		
	}
	
	@SuppressWarnings("unchecked")
	public static List<Pago> obtenerPagos(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Pago> resultado = null;
		

		Query query = session.createQuery("from Pago ");
		resultado = query.list();
		session.getTransaction().commit();
		return resultado;
		
	}

}
