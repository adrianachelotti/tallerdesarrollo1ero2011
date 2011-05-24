package web.formularios;

import java.io.Serializable;
import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;


import hibernate.AdministradorAdministradores;
import hibernate.AdministradorClientes;
import hibernate.AdministradorUsuarios;
import hibernate.domain.usuarios.Administrador;
import hibernate.domain.usuarios.Cliente;
import hibernate.domain.usuarios.Permiso;
import hibernate.domain.usuarios.Usuario;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.DropDownChoice;
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

public class RegistrarUsuario extends Formulario {

	/**
	 * 
	
	 */

	public class PermisoItem implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		Permiso permiso;
		boolean selected;

	}

	private static final long serialVersionUID = 1L;

	private TextField<String> usernameTF;
	private PasswordTextField passTF;
	private TextField<String> nombreTF;
	private TextField<String> apellidoTF;
	private TextField<String> emailTF;
	private final ValueMap properties = new ValueMap();
	private Model<String> tipoModel;
	private List data; // contiene los items que muestra el listview
	private PageableListView listView; // listview con los datos

	public RegistrarUsuario(Menu menu) {
		super(menu, "Modificar datos");

		Form<Void> formulario = new Form<Void>("formularioModificarDatos") {

			private static final long serialVersionUID = 1L;

			protected void onSubmit() {

				String username = usernameTF.getModelObject();
				String pass = passTF.getDefaultModelObjectAsString();
				String nombre = nombreTF.getModelObject();
				String apellido = apellidoTF.getModelObject();
				String email = emailTF.getModelObject();
				String tipo = tipoModel.getObject();

				if (!AdministradorUsuarios.existeUsuario(username)) {

					if (tipo.compareTo("Cliente") == 0) {
						Cliente cliente = new Cliente();
						cliente.setUsername(username);
						cliente.setPass(pass);
						cliente.setNombre(nombre);
						cliente.setApellido(apellido);
						cliente.setEmail(email);
						AdministradorClientes.agregarCliente(cliente);
						agregarPermisos(cliente);
						info("Cliente registrado con exito.");
						
					} else {
						// Administrador

						Administrador administrador = new Administrador();
						administrador.setUsername(username);
						administrador.setPass(pass);
						administrador.setNombre(nombre);
						administrador.setApellido(apellido);
						administrador.setEmail(email);
						AdministradorAdministradores
								.agregarAdministrador(administrador);
						agregarPermisos(administrador);
						info("Administrador registrado con exito.");

					}
					;

				} else
					info("El nombre de usuario ya existe. Elija otro, por favor.");

			}
		};

		this.getContenido().add(formulario);

		// tipo de usuario

		List<String> tipos = new ArrayList<String>();
		tipos.add("Cliente");
		tipos.add("Administrador");
		tipoModel = new Model<String>("Cliente");
		DropDownChoice<String> tipo = new DropDownChoice<String>("tipo",
				tipoModel, tipos);
		formulario.add(tipo);

		// text field de username
		usernameTF = new TextField<String>("username", new Model<String>(),
				String.class);
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
		nombreTF = new TextField<String>("nombre", new Model<String>(),
				String.class);
		nombreTF.setRequired(true);
		formulario.add(nombreTF);

		// text field de nombre
		apellidoTF = new TextField<String>("apellido", new Model<String>(),
				String.class);
		apellidoTF.setRequired(true);
		formulario.add(apellidoTF);

		// text field de email
		emailTF = new TextField<String>("email", new Model<String>(),
				String.class);
		formulario.add(emailTF);
		emailTF.add(EmailAddressValidator.getInstance());

		// permisos

		crearListView();

		formulario.add(listView);

	}

	protected void agregarPermisos(Usuario usuario) {
		 
		
		Iterator<PermisoItem> it = data.iterator();
        while (it.hasNext()) {

        
                PermisoItem pi =  it.next();
                
                if (pi.selected) {
                
                	
                	AdministradorUsuarios.agregarPermiso(usuario,pi.permiso);
                }
                ;

        }
        ;
        
        

		
	}

	private void crearListView() {
		/* cargo los permisos en el array data */
		data = new ArrayList();

		Iterator<Permiso> it = AdministradorUsuarios.obtenerPermisos()
				.iterator();
		while (it.hasNext()) {

			
			PermisoItem pi = new PermisoItem();
			pi.permiso = it.next();
			pi.selected = false;
			data.add(pi);

		}
		;

		listView = new PageableListView("rows", data, 10) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(final ListItem item) {

				PermisoItem pi = (PermisoItem) item.getModelObject();

				item.add(new Label("descripcion", pi.permiso.getDescripcion()));
				item.add(new CheckBox("check",new PropertyModel(pi, "selected")));

			}
		};
		
	};
}
