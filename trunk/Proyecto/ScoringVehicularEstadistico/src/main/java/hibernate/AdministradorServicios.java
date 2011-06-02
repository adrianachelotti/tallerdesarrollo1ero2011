package hibernate;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import hibernate.domain.usuarios.Servicio;


import hibernate.util.HibernateUtil;

public class AdministradorServicios {

	
	@SuppressWarnings("unchecked")
	public static Servicio obtenerServicio(String codigo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Servicio> resultado = null;
		Servicio servicio = null;

		Query query = session.createQuery("from Servicio s where s.codigo ='"+ codigo + "'");
		resultado = query.list();
		session.getTransaction().commit();
		if (!resultado.isEmpty())
			servicio = (Servicio) resultado.get(0);
		return servicio;

	}
	
	@SuppressWarnings("unchecked")
	public static Servicio obtenerServicioPorDescripcion(String descripcion) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Servicio> resultado = null;
		Servicio servicio = null;

		Query query = session.createQuery("from Servicio s where s.descripcion ='"+ descripcion + "'");
		resultado = query.list();
		session.getTransaction().commit();
		if (!resultado.isEmpty())
			servicio = (Servicio) resultado.get(0);
		return servicio;

	}
	
	

	
	public static void agregarServicio(Servicio servicio) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(servicio);
		session.flush();
		session.getTransaction().commit();
	}

	
	public static boolean eliminarServicio(String codigo) {

		Servicio servicio = obtenerServicio(codigo);
		boolean eliminado = false;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		if (servicio != null) {
			session.delete(servicio);
			eliminado = true;
		}
		session.getTransaction().commit();
		return eliminado;
	}


	@SuppressWarnings("unchecked")
	public static List<Servicio> obtenerServicios(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Servicio> resultado = null;
		

		Query query = session.createQuery("from Servicio");
		resultado = query.list();
		session.getTransaction().commit();
		return resultado;
		
	}

}
