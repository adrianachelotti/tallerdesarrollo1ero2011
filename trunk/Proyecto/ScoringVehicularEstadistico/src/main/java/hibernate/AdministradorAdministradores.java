package hibernate;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

import hibernate.domain.usuarios.Administrador;
import hibernate.util.HibernateUtil;

public class AdministradorAdministradores {


	@SuppressWarnings("unchecked")
	public static Administrador obtenerAdministrador(String username) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Administrador> resultado = null;
		Administrador administrador = null;

		Query query = session.createQuery("from Administrador usuario where usuario.username ='"+ username + "'");
		resultado = query.list();
		session.getTransaction().commit();
		if (!resultado.isEmpty())
			administrador = (Administrador) resultado.get(0);
		return administrador;

	}

	
	public static void agregarAdministrador(Administrador administrador) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(administrador);
		session.flush();
		session.getTransaction().commit();
	}

	public static boolean eliminarAdministrador(String username) {

		Administrador administrador = obtenerAdministrador(username);
		boolean eliminado = false;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		if (administrador != null) {
			session.delete(administrador);
			eliminado = true;
		}
		session.getTransaction().commit();
		return eliminado;
	}

	public static boolean modificarAdministrador(Administrador administrador) {
		boolean modificado = false;
		if (administrador != null) {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.update(administrador);
			modificado = true;
			session.getTransaction().commit();
		}
		return modificado;
	}

}
