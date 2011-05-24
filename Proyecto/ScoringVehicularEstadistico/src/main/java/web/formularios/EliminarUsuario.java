package web.formularios;

import hibernate.AdministradorUsuarios;
import org.apache.wicket.markup.html.form.Form;

import org.apache.wicket.markup.html.form.TextField;

import org.apache.wicket.model.Model;


import web.Formulario;

import web.Menu;



public class EliminarUsuario extends Formulario {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TextField<String> usernameTF;


	public EliminarUsuario(Menu menu) {
		super(menu, "Eliminar usuario");

		Form<Void> formulario = new Form<Void>("formularioModificarDatos") {

			private static final long serialVersionUID = 1L;

			protected void onSubmit() {

				String username = usernameTF.getModelObject();
	

				if (!AdministradorUsuarios.existeUsuario(username)) {

					info("El nombre de usuario ingresado no existe.");

				} else{
					AdministradorUsuarios.eliminarUsuario(username);
					info("Usuario eliminado con exito.");
					
				}
					

			}
		};

		this.getContenido().add(formulario);

		

		// text field de username
		usernameTF = new TextField<String>("username", new Model<String>(),
				String.class);
		usernameTF.setRequired(true);
		formulario.add(usernameTF);

	

	};
}
