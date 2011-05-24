package web.formularios;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import hibernate.AdministradorUsuarios;

import hibernate.domain.usuarios.Permiso;

import hibernate.domain.usuarios.Usuario;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;

import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.value.ValueMap;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.apache.wicket.validation.validator.StringValidator;

import web.Formulario;

import web.Menu;


public class ModificarDatosCuenta extends Formulario {

	

	private static final long serialVersionUID = 1L;

	private TextField<String> usernameTF;
	private PasswordTextField passTF;
	private TextField<String> nombreTF;
	private TextField<String> apellidoTF;
	private TextField<String> emailTF;
	private final ValueMap properties = new ValueMap();
	protected Usuario usuario;
	protected Form<Void> formulario;
	private Label servicioL;
	
	private List data; // contiene los items que muestra el listview
	private PageableListView listView; // listview con los datos

	public ModificarDatosCuenta(Menu menu) {
		super(menu, "Modificar datos");
		this.usuario = menu.getUsuario();

		formulario = new Form<Void>("formularioModificarDatos") {

			private static final long serialVersionUID = 1L;

			protected void onSubmit() {

				String username = usernameTF.getModelObject();
				String pass = passTF.getDefaultModelObjectAsString();
				String nombre = nombreTF.getModelObject();
				String apellido = apellidoTF.getModelObject();
				String email = emailTF.getModelObject();

				if ((usuario.getUsername() == username)
						|| (!AdministradorUsuarios.existeUsuario(username))) {
					usuario.setUsername(username);
					usuario.setPass(pass);
					usuario.setNombre(nombre);
					usuario.setApellido(apellido);
					usuario.setEmail(email);
					
					AdministradorUsuarios.modificarUsuario(usuario);
					
					
					info("Se actualizaron los datos correctamente.");

				} else
					info("El nombre de cliente ya existe. Elija otro, por favor.");

			}
		};

		this.getContenido().add(formulario);

		

		// text field de username
		usernameTF = new TextField<String>("username", new Model<String>(
				usuario.getUsername()), String.class);
		usernameTF.setRequired(true);
		formulario.add(usernameTF);

		// textfield de contraseña
		passTF = new PasswordTextField("pass", new PropertyModel<String>(
				properties, "password"));
		passTF.setRequired(true);
		passTF.add(StringValidator.maximumLength(20));
		passTF.add(StringValidator.minimumLength(5));
		formulario.add(passTF);

		// text field de nombre
		nombreTF = new TextField<String>("nombre", new Model<String>(
				usuario.getNombre()), String.class);
		nombreTF.setRequired(true);
		formulario.add(nombreTF);

		// text field de nombre
		apellidoTF = new TextField<String>("apellido", new Model<String>(
				usuario.getApellido()), String.class);
		apellidoTF.setRequired(true);
		formulario.add(apellidoTF);

		// text field de email
		emailTF = new TextField<String>("email", new Model<String>(
				usuario.getEmail()), String.class);
		formulario.add(emailTF);
		emailTF.add(EmailAddressValidator.getInstance());

		// servicios
		servicioL = new Label("servicioContratado", "Servicio contratado: "+usuario.getServicio().getDescripcion());

		formulario.add(servicioL);
		
		
		

	}

	
	
	

}
