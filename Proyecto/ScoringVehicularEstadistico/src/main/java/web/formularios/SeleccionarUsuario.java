package web.formularios;

import java.util.List;




import hibernate.domain.usuarios.Usuario;


import org.apache.wicket.markup.html.form.Form;


import org.apache.wicket.markup.html.list.PageableListView;


import web.Formulario;

import web.Menu;


public class SeleccionarUsuario extends Formulario {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private List<Usuario> data; // contiene los items que muestra el listview
	@SuppressWarnings({ "unused", "rawtypes" })
	private PageableListView listView; // listview con los datos

	public SeleccionarUsuario(final Menu menu) {
		super(menu);

		
		 Form<Void> formulario = new Form<Void>("formularioSeleccion") {

				private static final long serialVersionUID = 1L;

				protected void onSubmit() {

					menu.cambiarFormulario(new ModificarUsuario(menu));

				}
			};
			this.getContenido().add(formulario);

		

	};
}
