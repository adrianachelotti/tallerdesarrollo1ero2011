package hibernate;


import hibernate.domain.vehiculos.ActaDeInfraccion;
import hibernate.domain.vehiculos.Infraccion;
import hibernate.util.HibernateUtil;

import org.hibernate.Session;

public class AdministradorInfracciones {
	
	public static void agregarInfraccion(Infraccion infraccion) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(infraccion);
		session.flush();
		session.getTransaction().commit();
	}
	
	public static void agregarActa(ActaDeInfraccion actaInfraccion) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(actaInfraccion);
		session.flush();
		session.getTransaction().commit();
	}
	

}
