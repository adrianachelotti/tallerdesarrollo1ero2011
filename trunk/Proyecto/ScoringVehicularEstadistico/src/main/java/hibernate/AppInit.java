package hibernate;

import hibernate.domain.usuarios.Administrador;
import hibernate.domain.usuarios.Rubro;
import hibernate.domain.usuarios.Servicio;


public class AppInit {
	
	
	public static boolean inicializarAplicacion(){
		
		inicializarServicios();
		inicializarRubros();
		return(inicializarUsuarios());
		
		
		
	}
	private static void inicializarRubros() {
		if(!AdministradorUsuarios.obtenerRubros().isEmpty()) return;
		
		AdministradorUsuarios.agregarRubro(new Rubro("Aseguradora"));
		AdministradorUsuarios.agregarRubro(new Rubro("Empresa de logística"));
		AdministradorUsuarios.agregarRubro(new Rubro("Empresa de transporte"));
		AdministradorUsuarios.agregarRubro(new Rubro("Empresa de finanzas"));
		AdministradorUsuarios.agregarRubro(new Rubro("Prestadora"));
		AdministradorUsuarios.agregarRubro(new Rubro("Otros"));
	}
	
	
	private static void inicializarServicios() {
		if(!AdministradorServicios.obtenerServicios().isEmpty()) return;
		AdministradorServicios.agregarServicio(new Servicio("CP","Consulta puntual",5.00,"consulta",-1,false,false));
		AdministradorServicios.agregarServicio(new Servicio("CM","Consulta mixta",10.00,"consulta",-1,true,false));
		AdministradorServicios.agregarServicio(new Servicio("P","Premium",30.00,"consulta",-1,true,true));
		
		
	}
	
	
	
	private static boolean inicializarUsuarios() {
		if(!AdministradorUsuarios.obtenerUsuarios().isEmpty()) return false;
		Administrador usuario=new Administrador();
		usuario.setUsername("admin");
		usuario.setPass("admin");
		AdministradorUsuarios.agregarUsuario(usuario);
		return true;
		
	}
	
	

}
