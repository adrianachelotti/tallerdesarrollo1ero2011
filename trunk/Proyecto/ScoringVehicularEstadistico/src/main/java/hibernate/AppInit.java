package hibernate;

import java.sql.Date;

import hibernate.domain.conductores.Conductor;
import hibernate.domain.consultas.Consulta;
import hibernate.domain.sistemaFinanciero.DeudaSistemaFinanciero;
import hibernate.domain.sistemaJudicial.ExpedienteJudicial;
import hibernate.domain.usuarios.Administrador;
import hibernate.domain.usuarios.Cliente;
import hibernate.domain.usuarios.Pago;
import hibernate.domain.usuarios.Rubro;
import hibernate.domain.usuarios.Servicio;
import hibernate.domain.vehiculos.Vehiculo;


public class AppInit {
	
	
	public static boolean inicializarAplicacion(){
		
		inicializarServicios();
		inicializarRubros();
		inicializarEjemplos();
		
		
		boolean primeraVez=inicializarUsuarios();
		inicializarPagos();
		return primeraVez;
		
		
	}
	private static void inicializarPagos() {
		if(!AdministradorPagos.obtenerPagos().isEmpty()) return;
		Pago pago=new Pago();
		pago.setCliente(AdministradorClientes.obtenerCliente("cliente_con_pagos"));
		pago.setConcepto("Mes Junio");
		pago.setFecha(Date.valueOf("2011-06-01"));
		pago.setMonto(100.0);
		pago.setFormaPago("Contado");
		AdministradorPagos.agregarPago(pago);
		
		
		Pago pago2=new Pago();
		pago2.setCliente(AdministradorClientes.obtenerCliente("cliente_con_pagos"));
		pago2.setConcepto("Mes Mayo");
		pago2.setFecha(Date.valueOf("2011-05-01"));
		pago2.setMonto(100.0);
		pago2.setFormaPago("Tarjeta de credito");
		AdministradorPagos.agregarPago(pago2);
		
		
		Pago pago3=new Pago();
		pago3.setCliente(AdministradorClientes.obtenerCliente("cliente_con_pagos"));
		pago3.setConcepto("Mes Abril");
		pago3.setFecha(Date.valueOf("2011-04-01"));
		pago3.setMonto(100.0);
		pago3.setFormaPago("Contado");
		AdministradorPagos.agregarPago(pago3);
		
		
	}
	private static void inicializarRubros() {
		if(!AdministradorUsuarios.obtenerRubros().isEmpty()) return;
		
		AdministradorUsuarios.agregarRubro(new Rubro("Aseguradora"));
		AdministradorUsuarios.agregarRubro(new Rubro("Empresa de logistica"));
		AdministradorUsuarios.agregarRubro(new Rubro("Empresa de transporte"));
		AdministradorUsuarios.agregarRubro(new Rubro("Empresa de finanzas"));
		AdministradorUsuarios.agregarRubro(new Rubro("Prestadora"));
		AdministradorUsuarios.agregarRubro(new Rubro("Otros"));
	}
	
	
	private static void inicializarServicios() {
		/******************************** IMPORTANTE !!!!!!!!!!!!!! *************************
		 * 
		 * COMPLETAR LOS DETALLES CON LA DESCRIPCIÃ“N COMO TIENE QUE QUEDAR
		 * 
		 * *********************************************************************************
		 */
		
		
		if(!AdministradorServicios.obtenerServicios().isEmpty()) return;
		AdministradorServicios.agregarServicio(new Servicio("CP","Consulta puntual","Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",68.00,"consulta",-1,true,true));
		AdministradorServicios.agregarServicio(new Servicio("50C","Pack 50 consultas/mes","Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",2720.00,"mes",50,true,true));
		AdministradorServicios.agregarServicio(new Servicio("200C","Pack 200 consultas/mes","Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",10880.00,"mes",200,true,true));
		AdministradorServicios.agregarServicio(new Servicio("CI","Consultas ilimitadas por mes","Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",10880.00,"mes",-1,true,true));
		
		
	}
	
	
	
	private static boolean inicializarUsuarios() {
		if(!AdministradorUsuarios.obtenerUsuarios().isEmpty()) return false;
		Administrador usuario=new Administrador();
		usuario.setUsername("admin");
		usuario.setPass("admin");
		usuario.setNombre("Administrador default");
		usuario.setApellido("Administrador default");
		usuario.setActivado(true);
		AdministradorUsuarios.agregarUsuario(usuario);
		
		otrosUsuarios();
		return true;
		
		
	}
	
	
	private static void otrosUsuarios() {
		
		Cliente usuario=new Cliente();
		usuario.setUsername("cliente_con_pagos");
		usuario.setPass("12345");
		usuario.setNombre("Maria");
		usuario.setApellido("Perez");
		usuario.setServicio(AdministradorServicios.obtenerServicio("CP"));
		usuario.setActivado(true);
		AdministradorUsuarios.agregarUsuario(usuario);
		crearConsultas(48,"cliente_con_pagos");
		
		Cliente usuario2=new Cliente();
		usuario2.setUsername("cliente_con_consultas");
		usuario2.setPass("12345");
		usuario2.setNombre("Mariana");
		usuario2.setApellido("Perez");
		usuario2.setServicio(AdministradorServicios.obtenerServicio("50C"));
		usuario2.setActivado(true);
		usuario2.setCantidadConsultas(48);
		AdministradorUsuarios.agregarUsuario(usuario2);
		
		crearConsultas(48,"cliente_con_consultas");
		usuario.setCantidadConsultas(48);
		
		
	}
	private static void crearConsultas(int cantidad, String username) {
		for(int i=0;i<cantidad;i++){
			Consulta c=new Consulta();
			c.setDocumento(33206679);
			c.setTipoDoc("DNI");
			Cliente cliente=AdministradorClientes.obtenerCliente(username);
			c.setUsuario(cliente);
			java.util.Date hoy = new java.util.Date();
    		c.setFecha(new java.sql.Date(hoy.getTime()));
    		
    		c.setInfracciones(true);
    		c.setExpedientesJudiciales(true);
    		c.setScoring(true);
    		c.setSiniestros(true);
    		c.setSituacionFinanciera(true);
    		c.setVtv(true);
    		
    		AdministradorConsultas.agregarConsulta(c);


			
			
		}
		
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
		conductor1.setCantidadCerosEnScoring(1);
		conductor1.setCantidadConsultas(0);
		conductor1.setCantidadHijos(0);
		conductor1.setEdad(23);
		conductor1.setEstadoCivil("Soltero");
		conductor1.setNivelDeEstudio("Secundario");
		conductor1.setIngresoPromedio(2000.0);
		
		conductor1.setSaldoScoring(5);
		
		conductor1.setFechaNacimiento(new Date(87, 1, 4));
		conductor1.setNumeroDocumento(33206679);
		
		conductor1.agregarVehiculo(v1);
		conductor1.agregarVehiculo(v2);
		conductor1.setScoring(3);
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
		conductor2.setCantidadCerosEnScoring(1);
		conductor2.setCantidadConsultas(0);
		conductor2.setCantidadHijos(0);
		conductor2.setEdad(23);
		conductor2.setEstadoCivil("Soltero");
		conductor2.setNivelDeEstudio("Secundario");
		conductor2.setIngresoPromedio(2000.0);
		conductor2.setScoring(5);
		
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
		conductor3.setCantidadCerosEnScoring(1);
		conductor3.setCantidadConsultas(0);
		conductor3.setCantidadHijos(0);
		conductor3.setEdad(23);
		conductor3.setEstadoCivil("Soltero");
		conductor3.setNivelDeEstudio("Secundario");
		conductor3.setIngresoPromedio(2000.0);
		
		
		conductor3.setScoring(4);
		conductor3.agregarVehiculo(v4);
		AdministradorConductores.agregarConductor(conductor3);
		
		
		agregarDeudas(conductor1);
		agregarDeudas(conductor2);
		agregarDeudas(conductor3);
		agregarExpediente(conductor1);
		
		
		
	}
	private static void agregarExpediente(Conductor conductor1) {
		ExpedienteJudicial expediente=new ExpedienteJudicial();
		expediente.setConductor(conductor1);
		expediente.setNumero(12345);
		expediente.setObjeto("Accidente");
		expediente.setSituacion("Demandado");
		AdministradorConductores.agregarExpediente(expediente);
		
	}
	private static void agregarDeudas(Conductor conductor1) {
		DeudaSistemaFinanciero deuda=new DeudaSistemaFinanciero();
		deuda.setConductor(conductor1);
		deuda.setCantidadChequesRechazados(3);
		deuda.setCantidadPrestamos(4);
		deuda.setCantidadPrestamosCalificacionMayor(1);
		deuda.setClaveUnica("20186686432");
		deuda.setTipoClaveUnica("CUIL");
		deuda.setInhabilitacionJudicial(false);
		deuda.setInhabilitacionMulta(true);
		deuda.setMontoDeudaIrrecuperable(100000.0);
		deuda.setMontoDeudaNormal(2000.0);
		AdministradorConductores.agregarDeuda(deuda);
		
		
	}
	
	

}
