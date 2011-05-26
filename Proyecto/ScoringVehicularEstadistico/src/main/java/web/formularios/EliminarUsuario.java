package web.formularios;

import org.apache.wicket.markup.html.form.Form;

import web.Formulario;

import web.Menu;


public class EliminarUsuario extends Formulario {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*private List<Usuario> data; // contiene los items que muestra el listview
	private PageableListView listView; // listview con los datos*/

	public EliminarUsuario(final Menu menu) {
		super(menu);

		
		 Form<Void> formulario = new Form<Void>("formularioEliminar") {

				private static final long serialVersionUID = 1L;

				protected void onSubmit() {

					menu.cambiarFormulario(new ModificarUsuario(menu));

				}
			};
			this.getContenido().add(formulario);

		

	};
}
