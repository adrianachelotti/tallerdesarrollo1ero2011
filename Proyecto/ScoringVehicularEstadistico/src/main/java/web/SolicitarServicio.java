package web;

import hibernate.AdministradorServicios;
import hibernate.AdministradorUsuarios;
import hibernate.domain.usuarios.Cliente;
import hibernate.domain.usuarios.Rubro;
import hibernate.domain.usuarios.Servicio;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;


import web.utils.FeedbackLabel;

import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.CheckBoxMultipleChoice;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.value.ValueMap;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.apache.wicket.validation.validator.RangeValidator;
import org.apache.wicket.validation.validator.StringValidator;

import web.utils.ErrorLevelsFeedbackMessageFilter;
import web.validators.UsernameValidator;






public class SolicitarServicio extends WebPage {
	
	public final static int EDADMINIMA=21;
	
	//Datos
	
	FeedbackPanel panel;
	
	private TextField<String> usernameTF;
	private PasswordTextField passTF;
	private final ValueMap properties = new ValueMap();
	private TextField<String> nombreTF;
	private TextField<String> apellidoTF;
	
	private Model<String> tipoDocumentoModel;
	private DropDownChoice<String> tipoDocumentoDD;
	
	private TextField<Integer> documentoTF;
	private TextField<String> emailTF;
	private TextField<String> telefonoTF;
	
	private String sexo = "Femenino";
	private RadioChoice<String> sexoR;
	
	private TextField<Date> fechaTF;
	
	private Model<String> provinciaModel;
	private DropDownChoice<String> provinciaDD;
	
	private TextField<String> localidadTF;
	
	private String plan = "Consulta Puntual";
	private RadioChoice<String> planR;
	
	
	private ArrayList<String> rubrosSeleccionados = new ArrayList<String>();
	private CheckBoxMultipleChoice<String> serviciosCBMC ;
	
	
	private CheckBox aceptaCB;
	private boolean acepta = true;
	
	

	@SuppressWarnings("unchecked")
	public SolicitarServicio(){
		
		
		Form<Void> formulario = new Form<Void>("formulario") {

			private static final long serialVersionUID = 1L;

			protected void onSubmit() {
				
				Cliente cliente = new Cliente();
				cliente.setUsername(usernameTF.getModelObject());
				cliente.setPass(passTF.getModelObject());
				cliente.setNombre(nombreTF.getModelObject());
				cliente.setApellido(apellidoTF.getModelObject());
				cliente.setEmail(emailTF.getModelObject());
				cliente.setTipoDocumento(tipoDocumentoModel.getObject());
				cliente.setDocumento(documentoTF.getModelObject());
				cliente.setFechaNacimiento(fechaTF.getModelObject());
				cliente.setProvincia(provinciaModel.getObject());
				cliente.setLocalidad(localidadTF.getModelObject());
				cliente.setSexo(sexo);
				cliente.setServicio(AdministradorServicios.obtenerServicioPorDescripcion(plan));
				cliente.setActivado(false);
				Iterator<String> iteradorRubros=rubrosSeleccionados.iterator();
				while(iteradorRubros.hasNext()){
					cliente.agregarRubro(AdministradorUsuarios.obtenerRubro(iteradorRubros.next()));
					
				}
				
				AdministradorUsuarios.agregarUsuario(cliente);
				info("Gracias por contratar nuestro servicio. Nos comunicaremos con usted a la brevedad para confirmar sus datos y activar su cuenta.");
				this.setVisible(false);
				
					
		

			};
		};

		add(formulario);
		panel=new FeedbackPanel("mensajes");
		add(panel);
		
		
		
		
        // filteredErrorLevels will not be shown in the FeedbackPanel
        int[] filteredErrorLevels = new int[]{FeedbackMessage.ERROR};
        panel.setFilter(new ErrorLevelsFeedbackMessageFilter(filteredErrorLevels));
        

		
		
		// text field de username
		usernameTF = new TextField<String>("Nombre de usuario", new Model<String>(),	String.class);
		usernameTF.setRequired(true);
		usernameTF.add(new UsernameValidator());
		formulario.add(usernameTF);
   		formulario.add(new FeedbackLabel("usernameF", usernameTF));
		
		// text field de pass
		passTF = new PasswordTextField("Contraseña", new PropertyModel<String>(properties, "password"));
		passTF.setRequired(true);
		passTF.add(StringValidator.maximumLength(20));
		passTF.add(StringValidator.minimumLength(5));
		formulario.add(passTF);
		formulario.add(new FeedbackLabel("passF", passTF));
		
		
		// text field de nombre
		nombreTF = new TextField<String>("Nombre", new Model<String>(),	String.class);
		nombreTF.setRequired(true);
		formulario.add(nombreTF);
		formulario.add(new FeedbackLabel("nombreF", nombreTF));
		
		// text field de apellido
		apellidoTF = new TextField<String>("Apellido", new Model<String>(),	String.class);
		apellidoTF.setRequired(true);
		formulario.add(apellidoTF);
		formulario.add(new FeedbackLabel("apellidoF", apellidoTF));
		
		
		//dd tipo doc
		tipoDocumentoModel = new Model<String>("DNI");
        tipoDocumentoDD = new DropDownChoice<String>("Tipo de documento",tipoDocumentoModel, Arrays.asList(new String[] { "DNI","Libreta Cívica","Libreta de Enrolamiento","Pasaporte", "Cédula de Identidad" })) ;
        tipoDocumentoDD.setRequired(true);
        formulario.add(tipoDocumentoDD);
        
		
		
		// text field de nro doc
		documentoTF = new TextField<Integer>("Documento", new Model<Integer>(),	Integer.class);
		documentoTF.setRequired(true);
		formulario.add(documentoTF);
		formulario.add(new FeedbackLabel("documentoF", documentoTF));
		
		
		// text field de email
		emailTF = new TextField<String>("Email", new Model<String>(),	String.class);
		emailTF.setRequired(true);
		emailTF.add(EmailAddressValidator.getInstance());
		formulario.add(emailTF);
		formulario.add(new FeedbackLabel("emailF", emailTF));
		
		// text field de telefono
		telefonoTF = new TextField<String>("Telefono", new Model<String>(),	String.class);
		telefonoTF.setRequired(true);
		formulario.add(telefonoTF);
		formulario.add(new FeedbackLabel("telefonoF", telefonoTF));
		
		
		// radio sexo
		sexoR = new RadioChoice<String>("Sexo", new PropertyModel<String>(this,
				"sexo"),
				Arrays.asList(new String[] { "Femenino", "Masculino" }));
		formulario.add(sexoR);
		
		// text field de fecha
		fechaTF = new TextField<Date>("Fecha de nacimiento", new Model<Date>(),	Date.class);
		formulario.add(fechaTF);
		formulario.add(new FeedbackLabel("fechaF", fechaTF));
		
		
			
		//dd provincias
		provinciaModel = new Model<String>("CAPITAL FEDERAL");
		provinciaDD = new DropDownChoice<String>("Provincia",
				provinciaModel, Arrays.asList(new String[] { 
						"BUENOS AIRES",
						"CAPITAL FEDERAL",
						"CATAMARCA",
						"CHACO",
						"CHUBUT",
						"CORDOBA",
						"CORRIENTES",
						"ENTRE RIOS",
						"FORMOSA",
						"JUJUY",
						"LA PAMPA",
						"LA RIOJA",
						"MENDOZA",
						"MISIONES",
						"NEUQUEN",
						"RIO NEGRO",
						"SALTA",
						"SAN JUAN",
						"SAN LUIS",
						"SANTA CRUZ",
						"SANTA FE",
						"SANTIAGO DEL ESTERO",
						"TIERRA DEL FUEGO",
						"TUCUMAN", }));
		
		formulario.add(provinciaDD);
		
		// text field de localidad
		localidadTF = new TextField<String>("Localidad", new Model<String>(),	String.class);
		localidadTF.setRequired(true);
		formulario.add(localidadTF);
		formulario.add(new FeedbackLabel("localidadF", localidadTF));
		
		//radio plan
		
		List<String> servicios=new ArrayList<String>();
		Iterator<Servicio> iteradorServicios=AdministradorServicios.obtenerServicios().iterator();
		while(iteradorServicios.hasNext()){
			servicios.add(iteradorServicios.next().getDescripcion());
		}			
		planR = new RadioChoice<String>("plan", new PropertyModel<String>(this,
				"plan"),servicios);
		planR.setRequired(true);
		Model textoServicio = new Model("Debe seleccionar un servicio.");
		formulario.add(new FeedbackLabel("planF", planR,textoServicio));
		formulario.add(planR);
		
		
		//checkboxes para rubro
		
		List<String> rubros=new ArrayList<String>();
		Iterator<Rubro> iteradorRubros=AdministradorUsuarios.obtenerRubros().iterator();
		while(iteradorRubros.hasNext()){
			rubros.add(iteradorRubros.next().getDescripcion());
		}					
		serviciosCBMC = new CheckBoxMultipleChoice<String>(
				"rubros", new Model(rubrosSeleccionados),rubros);
		
		
		formulario.add(serviciosCBMC);
		
		//ch acepta
		aceptaCB = new CheckBox("acepta",new PropertyModel<Boolean>(this, "acepta"));
		aceptaCB.setRequired(true);
		Model textoAcepta = new Model("Debe aceptar términos y condiciones.");
		formulario.add(new FeedbackLabel("aceptaF", aceptaCB,textoAcepta));
		formulario.add(aceptaCB);
		
		//link volver
		
		add(new BookmarkablePageLink("linkVolver",Login2.class));
		
		
		
	}

}
