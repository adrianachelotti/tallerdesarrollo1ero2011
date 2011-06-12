package web.formularios;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


import hibernate.AdministradorServicios;
import hibernate.AdministradorUsuarios;



import hibernate.domain.usuarios.Cliente;
import hibernate.domain.usuarios.Rubro;
import hibernate.domain.usuarios.Servicio;
import hibernate.domain.usuarios.Usuario;

import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;

import org.apache.wicket.markup.html.form.CheckBoxMultipleChoice;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.value.ValueMap;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.apache.wicket.validation.validator.StringValidator;

import web.Formulario;
import web.Login2;

import web.Menu;
import web.utils.ErrorLevelsFeedbackMessageFilter;
import web.utils.FeedbackLabel;
import web.validators.UsernameValidator;

@SuppressWarnings("unused")
public class ModificarDatosCuenta extends Formulario {

	

	private static final long serialVersionUID = 1L;

	protected Usuario usuario;
	protected Form<Void> formulario;
	
	
//Datos
	
	FeedbackPanel panel;
	
	
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
	
		

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ModificarDatosCuenta(Menu menu) {
		super(menu);
		this.usuario = menu.getUsuario();

		formulario = new Form<Void>("formularioModificarDatos") {

			private static final long serialVersionUID = 1L;

			protected void onSubmit() {

			};
		};

		this.getContenido().add(formulario);
		
		panel=new FeedbackPanel("mensajes");
		formulario.add(panel);
		
		
        // filteredErrorLevels will not be shown in the FeedbackPanel
        int[] filteredErrorLevels = new int[]{FeedbackMessage.ERROR};
        panel.setFilter(new ErrorLevelsFeedbackMessageFilter(filteredErrorLevels));
        
		
		// text field de pass
		passTF = new PasswordTextField("Contraseña", new PropertyModel<String>(properties, "password"));
		passTF.setDefaultModelObject(usuario.getPass());
		passTF.setResetPassword(false);
		passTF.setRequired(true);
		passTF.add(StringValidator.maximumLength(20));
		passTF.add(StringValidator.minimumLength(5));
		formulario.add(passTF);
		formulario.add(new FeedbackLabel("passF", passTF));
		
		
		// text field de nombre
		nombreTF = new TextField<String>("Nombre", new Model<String>(),	String.class);
		nombreTF.setDefaultModelObject(usuario.getNombre());
		nombreTF.setRequired(true);
		formulario.add(nombreTF);
		formulario.add(new FeedbackLabel("nombreF", nombreTF));
		
		// text field de apellido
		apellidoTF = new TextField<String>("Apellido", new Model<String>(),	String.class);
		apellidoTF.setDefaultModelObject(usuario.getApellido());
		apellidoTF.setRequired(true);
		formulario.add(apellidoTF);
		formulario.add(new FeedbackLabel("apellidoF", apellidoTF));
		
		
		
		//COSAS QUE SOLO SE HABILITAN SI ES CLIENTE
		
		
		//dd tipo doc
		tipoDocumentoModel = new Model<String>("DNI");
        tipoDocumentoDD = new DropDownChoice<String>("Tipo de documento",tipoDocumentoModel, Arrays.asList(new String[] { "DNI","Libreta C�vica","Libreta de Enrolamiento","Pasaporte", "C�dula de Identidad" })) ;
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
		emailTF.setDefaultModelObject(usuario.getEmail());
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
		
		
		if(usuario.esCliente()) {
			cargarValoresDefaultDeCliente();
			habilitarOpcionesCliente(true);
		}else habilitarOpcionesCliente(false);
		
		
		
		
		
		

	}
	
	
	private void cargarValoresDefaultDeCliente() {
		Cliente cliente=(Cliente) usuario;
		tipoDocumentoDD.setDefaultModelObject(cliente.getTipoDocumento());
		documentoTF.setDefaultModelObject(cliente.getDocumento());
		
		telefonoTF.setDefaultModelObject(cliente.getTelefono());
		sexoR.setDefaultModelObject(cliente.getSexo());
		fechaTF.setDefaultModelObject(cliente.getFechaNacimiento());
		provinciaDD.setDefaultModelObject(cliente.getProvincia());
		localidadTF.setDefaultModelObject(cliente.getLocalidad());
		planR.setDefaultModelObject(cliente.getServicio().getDescripcion());
		
		Iterator<Rubro> iteradorRubros=cliente.getRubros().iterator();
		
		while(iteradorRubros.hasNext()){
			rubrosSeleccionados.add(iteradorRubros.next().getDescripcion());
			
		}
		serviciosCBMC.setDefaultModelObject(rubrosSeleccionados);
		
		
	}

	protected void habilitarOpcionesCliente(boolean b) {
		tipoDocumentoDD.setEnabled(b);
		documentoTF.setEnabled(b);
		telefonoTF.setEnabled(b);
		sexoR.setEnabled(b);
		provinciaDD.setEnabled(b);
		localidadTF.setEnabled(b);
		planR.setEnabled(b);
		serviciosCBMC.setEnabled(b);
		
	}

	
	
	

}
