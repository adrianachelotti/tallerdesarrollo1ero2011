package web.formularios;

import java.util.List;


import hibernate.AdministradorUsuarios;
import hibernate.domain.usuarios.Cliente;

import hibernate.domain.usuarios.Usuario;

import org.apache.wicket.markup.html.basic.Label;


import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;

import web.Formulario;

import web.Menu;


public class SeleccionarUsuario extends Formulario {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Usuario> data; // contiene los items que muestra el listview
	private PageableListView listView; // listview con los datos

	public SeleccionarUsuario(final Menu menu) {
		super(menu, "Seleccionar usuario");

		/* obtengo las transacciones del cliente */
		data = AdministradorUsuarios.obtenerUsuarios();

		listView = new PageableListView("usuarios", data, 10) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem item) {

				final Usuario usuario = (Usuario) item.getModelObject();

				item.add(new Label("username", usuario.getUsername()));
				item.add(new Label("nombre", usuario.getNombre()));
				item.add(new Label("apellido", usuario.getApellido()));
				item.add(new Label("tipo", usuario instanceof Cliente?"Cliente":"Administrador"));
				item.add(new Link("link") {

                    
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void onClick() {
                            
                            menu.cambiarFormulario(new ModificarUsuario(menu,usuario));
                            
                            
				

                    }

            });

				

			}
		};

		this.getContenido().add(listView);

		PagingNavigator pager = new PagingNavigator("pager", listView); // navegador
		this.getContenido().add(pager);

	};
}
