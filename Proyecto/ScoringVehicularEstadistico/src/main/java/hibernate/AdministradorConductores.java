package hibernate;

import java.util.List;



import org.hibernate.Query;
import org.hibernate.Session;



import hibernate.domain.conductores.Conductor;

import hibernate.domain.vehiculos.Vehiculo;



import hibernate.util.HibernateUtil;

public class AdministradorConductores{

	
	@SuppressWarnings("unchecked")
	public static Conductor obtenerConductor(String tipoDoc, Integer doc) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Conductor> resultado = null;
		Conductor conductor = null;

		Query query = session.createQuery("from Conductor conductor where conductor.tipoDocumento ='"+tipoDoc+"' and conductor.numeroDocumento='"+doc+"'");
		resultado = query.list();
		session.getTransaction().commit();
		if (!resultado.isEmpty())
			conductor = (Conductor) resultado.get(0);
		return conductor;

	}

	public static void agregarConductor(Conductor conductor) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(conductor);
		session.flush();
		session.getTransaction().commit();
	}
	
	
	
	

	
	public static boolean eliminarConductor(String tipoDoc, Integer doc) {

		Conductor conductor = obtenerConductor(tipoDoc,doc);
		boolean eliminado = false;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		if (conductor != null) {
			session.delete(conductor);
			eliminado = true;
		}
		session.getTransaction().commit();
		return eliminado;
	}

	

	@SuppressWarnings("unchecked")
	public static List<Conductor> obtenerConductoresPorApellidoNombre(String apellido, String nombre) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Conductor> resultado = null;
		//TODO: que esto no sea estricto, reemplazar por LIKE
		Query query = session.createQuery("from Conductor c where c.apellidos='"+apellido+"' and c.nombre='"+nombre+"'");
		resultado = query.list();
		session.getTransaction().commit();
		return resultado;
	}

	@SuppressWarnings("unchecked")
	public static Vehiculo obtenerVehiculo(String patente) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Vehiculo> resultado = null;
		Vehiculo vehiculo = null;

		Query query = session.createQuery("from Vehiculo vehiculo where vehiculo.patente='"+patente+"'");
		resultado = query.list();
		session.getTransaction().commit();
		if (!resultado.isEmpty())
			vehiculo = (Vehiculo) resultado.get(0);
		return vehiculo;
	}

	@SuppressWarnings("unchecked")
	public static List<Conductor> obtenerConductoresPorApellido(String apellido) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Conductor> resultado = null;
		//TODO: que esto no sea estricto, reemplazar por LIKE
		Query query = session.createQuery("from Conductor c where c.apellidos='"+apellido+"' ");
		resultado = query.list();
		session.getTransaction().commit();
		return resultado;
	}
	
	
	
	
		

	

	



}
