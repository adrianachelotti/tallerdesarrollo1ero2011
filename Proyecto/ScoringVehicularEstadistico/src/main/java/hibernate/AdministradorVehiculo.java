package hibernate;


import hibernate.domain.vehiculos.Siniestro;
import hibernate.domain.vehiculos.VerificacionTecnica;
import hibernate.util.HibernateUtil;

import org.hibernate.Session;

public class AdministradorVehiculo {

	
	public static void agregarVtv(VerificacionTecnica vtv) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(vtv);
		session.flush();
		session.getTransaction().commit();
	}
	
	public static void agregarSiniestro(Siniestro siniestro) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(siniestro);
		session.flush();
		session.getTransaction().commit();
	}
	
}
