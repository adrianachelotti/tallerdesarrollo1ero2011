package web.formularios;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;

import hibernate.AdministradorConductores;
import hibernate.domain.conductores.Conductor;
import hibernate.domain.consultas.Consulta;
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
	//scoring
	//vtv
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
		
		//TODO: SCORING
		
		armarConductor();
		armarVehiculos();
		armarInfracciones();
		armarSiniestros();
		armarExpedientesJudiciales();
		armarDeudas();
		//vtv
		//scoring
		
		if(!consulta.isInfracciones()) infraccionesContainer.setVisible(false);
		if(!consulta.isSiniestros()) siniestrosContainer.setVisible(false);
		if(!consulta.isExpedientesJudiciales()) expedientesJudicialesContainer.setVisible(false);
		if(!consulta.isSituacionFinanciera()) expedientesJudicialesContainer.setVisible(false);
		//vtv
		//scoring
		
		
	}

	private void armarDeudas() {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
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
			
		
	}

	
}
