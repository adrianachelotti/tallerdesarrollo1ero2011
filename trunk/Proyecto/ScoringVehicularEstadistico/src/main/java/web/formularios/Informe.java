package web.formularios;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;

import hibernate.AdministradorConductores;
import hibernate.domain.conductores.Conductor;
import hibernate.domain.consultas.Consulta;
import hibernate.domain.sistemaFinanciero.DeudaSistemaFinanciero;
import web.Formulario;
import web.Menu;



public class Informe extends Formulario {

	/**
	 * 
	 */
	
	private WebMarkupContainer infraccionesContainer=new WebMarkupContainer("infracciones") ;
	private WebMarkupContainer siniestrosContainer=new WebMarkupContainer("siniestros") ;;
	private WebMarkupContainer sistemaFinancieroContainer=new WebMarkupContainer("sistemaFinanciero") ;;
	private WebMarkupContainer expedientesJudicialesContainer=new WebMarkupContainer("expedientesJudiciales") ;;
	private WebMarkupContainer vehiculosContainer=new WebMarkupContainer("vehiculos") ;;
	private WebMarkupContainer conductorContainer=new WebMarkupContainer("conductor") ;;
	private WebMarkupContainer scoringContainer=new WebMarkupContainer("scoring") ;;
	private WebMarkupContainer vtvContainer=new WebMarkupContainer("vtv") ;;
	

	private Consulta consulta;
	
	private static final long serialVersionUID = 1L;

	public Informe(Menu menu, Consulta consultaSeleccionada){
		super(menu);
		
		
		this.consulta=consultaSeleccionada;
		
		
		this.getContenido().add(infraccionesContainer);
		this.getContenido().add(siniestrosContainer);
		this.getContenido().add(sistemaFinancieroContainer);
		this.getContenido().add(expedientesJudicialesContainer);
		this.getContenido().add(vehiculosContainer);
		this.getContenido().add(conductorContainer);
		this.getContenido().add(scoringContainer);
		this.getContenido().add(vtvContainer);
		
		//TODO: SCORING
		
		armarConductor();
		armarVehiculos();
		armarInfracciones();
		armarSiniestros();
		armarExpedientesJudiciales();
		armarDeudas();
		armarVtv();
		armarScoring();
		
		if(!consulta.isInfracciones()) infraccionesContainer.setVisible(false);
		if(!consulta.isSiniestros()) siniestrosContainer.setVisible(false);
		if(!consulta.isExpedientesJudiciales()) expedientesJudicialesContainer.setVisible(false);
		if(!consulta.isSituacionFinanciera()) expedientesJudicialesContainer.setVisible(false);
		if(!consulta.isVtv()) vtvContainer.setVisible(false);
		if(!consulta.isScoring()) scoringContainer.setVisible(false);
		
		
	}

	private void armarScoring() {
		
		
	}

	private void armarVtv() {
		// TODO Auto-generated method stub
		
	}

	private void armarDeudas() {
		DeudaSistemaFinanciero deuda=AdministradorConductores.obtenerDeuda(consulta.getTipoDoc(), consulta.getDocumento());
		if(deuda==null){
			
			sistemaFinancieroContainer.add(new Label("claveUnica","No disponible"));
			sistemaFinancieroContainer.add(new Label("tipoClaveUnica","No disponible"));
			sistemaFinancieroContainer.add(new Label("cheques","No disponible"));
			sistemaFinancieroContainer.add(new Label("prestamos","No disponible"));
			sistemaFinancieroContainer.add(new Label("prestamosMayor","No disponible"));
			sistemaFinancieroContainer.add(new Label("judicial","No disponible"));
			sistemaFinancieroContainer.add(new Label("multa","No disponible"));
			
			sistemaFinancieroContainer.add(new Label("irrecuperable","No disponible"));
			sistemaFinancieroContainer.add(new Label("normal","No disponible"));
			sistemaFinancieroContainer.add(new Label("promedio","No disponible"));
			
			
		}else{
		sistemaFinancieroContainer.add(new Label("claveUnica",deuda.getClaveUnica()));
		sistemaFinancieroContainer.add(new Label("tipoClaveUnica",deuda.getTipoClaveUnica()));
		sistemaFinancieroContainer.add(new Label("cheques",deuda.getCantidadChequesRechazados()==null?"-":deuda.getCantidadChequesRechazados().toString()));
		sistemaFinancieroContainer.add(new Label("prestamos",deuda.getCantidadPrestamos()==null?"-":deuda.getCantidadPrestamos().toString()));
		sistemaFinancieroContainer.add(new Label("prestamosMayor",deuda.getCantidadPrestamosCalificacionMayor()==null?"-":deuda.getCantidadPrestamosCalificacionMayor().toString()));
		sistemaFinancieroContainer.add(new Label("judicial",deuda.getInhabilitacionJudicial()==null?"-":deuda.getInhabilitacionJudicial()?"SÍ":"NO"));
		sistemaFinancieroContainer.add(new Label("multa",deuda.getInhabilitacionMulta()==null?"-":deuda.getInhabilitacionMulta()?"SÍ":"NO"));
		
		sistemaFinancieroContainer.add(new Label("irrecuperable",deuda.getMontoDeudaIrrecuperable()==null?"-":"$"+deuda.getMontoDeudaIrrecuperable().toString()));
		sistemaFinancieroContainer.add(new Label("normal",deuda.getMontoDeudaNormal()==null?"-":"$ "+deuda.getMontoDeudaNormal().toString()));
		sistemaFinancieroContainer.add(new Label("promedio",deuda.getPromedioDiasAtraso()==null?"-":deuda.getPromedioDiasAtraso().toString()));
		
		};
		
		
	}

	private void armarExpedientesJudiciales() {
		// TODO Auto-generated method stub
		
	}

	private void armarSiniestros() {
		// TODO Auto-generated method stub
		
	}

	private void armarInfracciones() {
		// TODO Auto-generated method stub
		
	}

	private void armarVehiculos() {
		
		
	}

	private void armarConductor() {
		Conductor conductor=AdministradorConductores.obtenerConductor(consulta.getTipoDoc(), consulta.getDocumento());
		conductorContainer.add(new Label("tipoDocumento",conductor.getTipoDocumento()));
		conductorContainer.add(new Label("numeroDocumento",conductor.getNumeroDocumento().toString()));
		conductorContainer.add(new Label("apellidos",conductor.getApellidos()));
		conductorContainer.add(new Label("nombres",conductor.getNombre()));
		conductorContainer.add(new Label("sexo",conductor.getSexo()));
		conductorContainer.add(new Label("edad",conductor.getEdad()==null?"-":conductor.getEdad().toString()));
		conductorContainer.add(new Label("cantidadHijos",conductor.getCantidadHijos()==null?"-":conductor.getCantidadHijos().toString()));
		conductorContainer.add(new Label("estadoCivil",conductor.getEstadoCivil()));
		conductorContainer.add(new Label("fechaNacimiento",conductor.getFechaNacimiento()==null?"-":conductor.getFechaNacimiento().toString().substring(0,10)));
		conductorContainer.add(new Label("ingresoPromedio",conductor.getIngresoPromedio()==null?"-":conductor.getIngresoPromedio().toString()));
		conductorContainer.add(new Label("lugarResidencia",conductor.getLugarResidencia()));
		conductorContainer.add(new Label("nivelEstudio",conductor.getNivelDeEstudio()));
		conductorContainer.add(new Label("profesion",conductor.getProfesion()));
		conductorContainer.add(new Label("saldoScoring",conductor.getSaldoScoring()==null?"-":conductor.getSaldoScoring().toString()));
		conductorContainer.add(new Label("cantidad0",conductor.getCantidadCerosEnScoring()==null?"-":conductor.getCantidadCerosEnScoring().toString()));
		conductorContainer.add(new Label("cantidadConsultas",conductor.getCantidadConsultas()==null?"-":conductor.getCantidadConsultas().toString()));
			
		
	}

	
}
