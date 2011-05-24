package web.formularios;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import hibernate.AdministradorAdministradores;
import hibernate.AdministradorClientes;
import hibernate.AdministradorServicios;
import hibernate.AdministradorUsuarios;

import hibernate.domain.usuarios.Administrador;
import hibernate.domain.usuarios.Cliente;
import hibernate.domain.usuarios.Permiso;
import hibernate.domain.usuarios.Servicio;

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

public class ModificarUsuario extends Formulario {

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
	protected Usuario usuario;
	protected Form<Void> formulario;
	private Model<String> servicioModel;

	private List data; // contiene los items que muestra el listview
	private PageableListView listView; // listview con los datos

	public ModificarUsuario(Menu menu, final Usuario usuario) {
		super(menu, "Modificar datos");
		this.usuario = usuario;

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
					//actualizarPermisos(usuario);
					if(usuario instanceof Cliente){
						Cliente cliente=(Cliente) usuario;
						cliente.setServicio(AdministradorServicios.obtenerServicio(servicioModel.getObject()));
						AdministradorClientes.modificarCliente(cliente);
					}else{
						Administrador administrador=(Administrador) usuario;
						AdministradorAdministradores.modificarAdministrador(administrador);
					}

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

		// permisos
		crearListView();

		formulario.add(listView);

		// crear servicio

		crearServicio();

	}

	protected void actualizarPermisos(Usuario usuario) {
		usuario.eliminarTodosLosPermisos();
		Iterator<PermisoItem> it=data.iterator();
		while(it.hasNext()){
			PermisoItem pi=it.next();
			if(pi.selected){
				pi.permiso.eliminarUsuario(usuario);
				AdministradorUsuarios.modificarPermiso(pi.permiso);
			}
			
		}
		
	}

	private void crearServicio() {
// 			servicio
		
		
		List<String> servicios = new ArrayList<String>();
		Iterator<Servicio> iteradorServicios = AdministradorServicios
				.obtenerServicios().iterator();
		while (iteradorServicios.hasNext()) {
			servicios.add(iteradorServicios.next().getDescripcion());

		}
		
		if(usuario instanceof Cliente){
		
		Cliente cliente=(Cliente) usuario;

		

		//servicioModel = new Model<String>(usuario.getServicio().getDescripcion());

		servicioModel = new Model<String>("");
		DropDownChoice<String> servicioDD = new DropDownChoice<String>(
				"servicio", servicioModel, servicios);
		formulario.add(servicioDD);
		
		}else{
			
			
			servicioModel = new Model<String>("");
			DropDownChoice<String> servicioDD = new DropDownChoice<String>(
					"servicio", servicioModel, servicios);
			formulario.add(servicioDD);
			servicioDD.setEnabled(false);
			

			
		}
		

		
			
		
		
		
	}

	private void crearListView() {
		/* cargo los permisos en el array data */
		data = new ArrayList();

		Iterator<Permiso> it = AdministradorUsuarios.obtenerPermisos()
				.iterator();
		while (it.hasNext()) {

			PermisoItem pi = new PermisoItem();
			pi.permiso = it.next();
			if (usuario.tienePermiso(pi.permiso.getId()))
				pi.selected = true;
			else
				pi.selected = false;
			data.add(pi);

		}

		listView = new PageableListView("rows", data, 10) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(final ListItem item) {

				PermisoItem pi = (PermisoItem) item.getModelObject();

				item.add(new Label("descripcion", pi.permiso.getDescripcion()));
				item.add(new CheckBox("check",
						new PropertyModel(pi, "selected")));

			}
		};

	};

}
