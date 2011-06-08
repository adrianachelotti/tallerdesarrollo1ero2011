package hibernate;

import java.sql.Date;

import hibernate.domain.conductores.Conductor;
import hibernate.domain.usuarios.Administrador;
import hibernate.domain.usuarios.Rubro;
import hibernate.domain.usuarios.Servicio;
import hibernate.domain.vehiculos.Vehiculo;


public class AppInit {
	
	
	public static boolean inicializarAplicacion(){
		
		inicializarServicios();
		inicializarRubros();
		inicializarEjemplos();
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
		usuario.setNombre("Administrador default");
		usuario.setApellido("Administrador default");
		AdministradorUsuarios.agregarUsuario(usuario);
		return true;
		
		
	}
	
	
	@SuppressWarnings("deprecation")
	private static void inicializarEjemplos() {
		
		if(AdministradorConductores.obtenerVehiculo("123ABC")!=null) return;
		Vehiculo v1=new Vehiculo();
		v1.setEstadoExterior("BUENO");
		v1.setEstadoInterior("BUENO");
		v1.setEstadoMecanica("BUENO");
		v1.setMarca("Ford");
		v1.setModelo("Ka");
		v1.setPatente("123ABC");
		
		
		Vehiculo v2=new Vehiculo();
		v2.setEstadoExterior("BUENO");
		v2.setEstadoInterior("BUENO");
		v2.setEstadoMecanica("BUENO");
		v2.setMarca("Volkswaven");
		v2.setModelo("Gol");
		v2.setPatente("456ABC");
		
		Vehiculo v3=new Vehiculo();
		v3.setEstadoExterior("BUENO");
		v3.setEstadoInterior("BUENO");
		v3.setEstadoMecanica("BUENO");
		v3.setPatente("789ABC");
		v3.setMarca("Fiat");
		v3.setModelo("Siena");
		
		Vehiculo v4=new Vehiculo();
		v4.setEstadoExterior("BUENO");
		v4.setEstadoInterior("BUENO");
		v4.setEstadoMecanica("BUENO");
		v3.setMarca("Volkwagen");
		v3.setModelo("Passat");
		v4.setPatente("321ABC");
		
		
		
		Conductor conductor1=new Conductor();
		conductor1.setNombre("Ana");
		conductor1.setApellidos("Perez");
		conductor1.setTipoDocumento("DNI");
		conductor1.setSexo("Femenino");
		conductor1.setLugarResidencia("Retiro");
		conductor1.setProfesion("Abogado");
		conductor1.setFechaNacimiento(new Date(87, 1, 4));
		conductor1.setNumeroDocumento(33206679);
		
		conductor1.agregarVehiculo(v1);
		conductor1.agregarVehiculo(v2);
		AdministradorConductores.agregarConductor(conductor1);
		
		Conductor conductor2=new Conductor();
		conductor2.setNombre("Juan");
		conductor2.setApellidos("Diaz");
		conductor2.setTipoDocumento("DNI");
		conductor2.setSexo("Masculino");
		conductor2.setLugarResidencia("Retiro");
		conductor2.setProfesion("Contador");
		conductor2.setFechaNacimiento(new Date(87, 1, 2));
		conductor2.setNumeroDocumento(33206680);
		
		conductor2.agregarVehiculo(v1);
		conductor2.agregarVehiculo(v3);
		AdministradorConductores.agregarConductor(conductor2);
		
		Conductor conductor3=new Conductor();
		conductor3.setNombre("Juan");
		conductor3.setApellidos("Diaz");
		conductor3.setTipoDocumento("DNI");
		conductor3.setSexo("Masculino");
		conductor3.setLugarResidencia("Devoto");
		conductor3.setProfesion("Médico");
		conductor3.setFechaNacimiento(new Date(87, 1, 1));
		conductor3.setNumeroDocumento(33206681);
		
		
		conductor2.agregarVehiculo(v4);
		AdministradorConductores.agregarConductor(conductor3);
		
		
	}
	
	

}
