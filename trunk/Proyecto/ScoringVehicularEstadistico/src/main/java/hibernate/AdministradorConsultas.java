package hibernate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



import org.hibernate.Query;
import org.hibernate.Session;



import hibernate.domain.conductores.Conductor;
import hibernate.domain.consultas.Consulta;



import hibernate.util.HibernateUtil;

public class AdministradorConsultas {

	
	@SuppressWarnings("unchecked")
	public static Consulta obtenerConsulta(Integer id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Consulta> resultado = null;
		Consulta consulta = null;

		Query query = session.createQuery("from Consulta consulta where consulta.id ="+id);
		resultado = query.list();
		session.getTransaction().commit();
		if (!resultado.isEmpty())
			consulta = (Consulta) resultado.get(0);
		return consulta;

	}

	public static void agregarConsulta(Consulta consulta) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(consulta);
		session.flush();
		session.getTransaction().commit();
	}

	
	public static boolean eliminarConsulta(Integer id) {

		Consulta consulta = obtenerConsulta(id);
		boolean eliminado = false;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		if (consulta != null) {
			session.delete(consulta);
			eliminado = true;
		}
		session.getTransaction().commit();
		return eliminado;
	}

	

	@SuppressWarnings("unchecked")
	public static List<Consulta> obtenerConsultas() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Consulta> resultado = null;
		Query query = session.createQuery("from Consulta ");
		resultado = query.list();
		session.getTransaction().commit();
		return resultado;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Consulta> obtenerConsultasPorUsuario(String username) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Consulta> resultado = null;
		Query query = session.createQuery("from Consulta consulta where consulta.usuario.username='"+username+"'");;
		resultado = query.list();
		session.getTransaction().commit();
		return resultado;
	}

	
	
	public static List<Consulta> obtenerConsultasPosibles(Consulta consultaBase) {
		
		List<Consulta> consultasPosibles=new ArrayList<Consulta>();
		
		boolean hayResultados=true;
		
		hayResultados=analizarDocumento(consultaBase, consultasPosibles);
		if(!hayResultados)
			hayResultados=analizarNombreApellido(consultaBase,consultasPosibles);
		if(!hayResultados)
			hayResultados=analizarApellido(consultaBase, consultasPosibles);
			
								
		
					
		
		
		return consultasPosibles;
		
		
		
		
	}

	/*
	private static boolean analizarPatente(Consulta consultaBase,
			List<Consulta> consultasPosibles) {
		
		boolean hayCoincidencia=false;
		
		if(consultaBase.getPatente().compareTo("")!=0){
			
			Vehiculo vehiculo=AdministradorConductores.obtenerVehiculo(consultaBase.getPatente());
			agregarConsultaParaCadaConductor(vehiculo,consultasPosibles);
			
		}
		
		return hayCoincidencia;
	}*/

/*	private static void agregarConsultaParaCadaConductor(Vehiculo vehiculo,
			List<Consulta> consultasPosibles) {
		Iterator<Conductor> iteradorConductores=vehiculo.getConductores().iterator();
		while(iteradorConductores.hasNext()){
			Consulta consulta=new Consulta();
			consulta.setValoresConductor(iteradorConductores.next());
			consulta.setPatente(vehiculo.getPatente());
			consultasPosibles.add(consulta);
		}
		
	}*/

	/*private static boolean analizarNombreApellido(Consulta consultaBase,
			List<Consulta> consultasPosibles) {
	boolean hayCoincidencia=false;
		
		if((consultaBase.getApellido().compareTo("")!=0)&&(consultaBase.getNombre().compareTo("")!=0)){
			hayCoincidencia=true;
			
			
			Iterator<Conductor> itConductores=AdministradorConductores.obtenerConductoresPorApellidoNombre(consultaBase.getApellido(),consultaBase.getNombre()).iterator();
			while(itConductores.hasNext()){
				Conductor conductor=itConductores.next();
				
				if(consultaBase.getPatente().compareTo("")!=0){
					String patente=consultaBase.getPatente();
					if(conductor.conduce(patente)){
						Consulta consulta=new Consulta();
						consulta.setValoresConductor(conductor);
						consulta.setPatente(patente);
						consultasPosibles.add(consulta);
					}
				}else{
					agregarConsultaParaCadaPatente(conductor,consultasPosibles);
				}
			}
			
			
			
		};
		
		return hayCoincidencia;
	}*/
	
	private static boolean analizarApellido(Consulta consultaBase,
			List<Consulta> consultasPosibles) {
		boolean hayCoincidencia = false;

		if (consultaBase.getApellido().compareTo("") != 0) {
			hayCoincidencia = true;

			Iterator<Conductor> itConductores = AdministradorConductores.obtenerConductoresPorApellido(consultaBase.getApellido()).iterator();
			while (itConductores.hasNext()) {
				Conductor conductor = itConductores.next();
				Consulta consulta = new Consulta();
				consulta.setValoresConductor(conductor);
				consultasPosibles.add(consulta);

				
			}

		}
		;

		return hayCoincidencia;
	}

	private static boolean analizarNombreApellido(Consulta consultaBase,
			List<Consulta> consultasPosibles) {
		boolean hayCoincidencia = false;

		if ((consultaBase.getApellido().compareTo("") != 0)	&& (consultaBase.getNombre().compareTo("") != 0)) {
			hayCoincidencia = true;

			Iterator<Conductor> itConductores = AdministradorConductores.obtenerConductoresPorApellidoNombre(consultaBase.getApellido(),consultaBase.getNombre()).iterator();
			while (itConductores.hasNext()) {
				Conductor conductor = itConductores.next();
				Consulta consulta = new Consulta();
				consulta.setValoresConductor(conductor);
				consultasPosibles.add(consulta);

				
			}

		}
		;

		return hayCoincidencia;
	}

	/*private static boolean analizarDocumento(Consulta consultaBase,
			List<Consulta> consultasPosibles) {
		
		boolean hayCoincidencia=false;
		
		if(consultaBase.getDocumento()!=null){
			hayCoincidencia=true;
			
			Conductor conductor=AdministradorConductores.obtenerConductor(consultaBase.getTipoDoc(), consultaBase.getDocumento());
			
			if(consultaBase.getPatente().compareTo("")!=0){
			
				
				String patente=consultaBase.getPatente();
				if(conductor.conduce(patente)){
					Consulta consulta=new Consulta();
					consulta.setValoresConductor(conductor);
					consulta.setPatente(patente);
					consultasPosibles.add(consulta);
					
					
				}
				
			}else{
				
				agregarConsultaParaCadaPatente(conductor,consultasPosibles);
				
			};
		};
		
		return hayCoincidencia;
		
	}*/
	
	
	private static boolean analizarDocumento(Consulta consultaBase,
			List<Consulta> consultasPosibles) {

		boolean hayCoincidencia = false;

		if (consultaBase.getDocumento() != null) {
			hayCoincidencia = true;

			Conductor conductor = AdministradorConductores.obtenerConductor(consultaBase.getTipoDoc(), consultaBase.getDocumento());
			Consulta consulta = new Consulta();
			consulta.setValoresConductor(conductor);
			consultasPosibles.add(consulta);

		}
		;

		return hayCoincidencia;

	}


	

	/*private static void agregarConsultaParaCadaPatente(Conductor conductor,List<Consulta> consultasPosibles) {
		Iterator<Vehiculo> iteradorVehiculos=conductor.getVehiculos().iterator();
		while(iteradorVehiculos.hasNext()){
			Consulta consulta=new Consulta();
			consulta.setValoresConductor(conductor);
			consulta.setPatente(iteradorVehiculos.next().getPatente());
			consultasPosibles.add(consulta);
		}
		
	}*/


	
	
		

	

	



}
