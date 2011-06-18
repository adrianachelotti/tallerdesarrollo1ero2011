package hibernate;
import hibernate.domain.sistemaFinanciero.DeudaSistemaFinanciero;
import hibernate.util.HibernateUtil;
import org.hibernate.Session;

public class AdministradorDeudas {

	public static void agregarConductor(DeudaSistemaFinanciero deudas) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(deudas);
		session.flush();
		session.getTransaction().commit();
	}
	
}
