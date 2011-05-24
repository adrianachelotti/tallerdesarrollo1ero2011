package web;


import org.apache.wicket.markup.html.link.Link;

import org.apache.wicket.markup.html.panel.Panel;


import web.formularios.EliminarUsuario;
import web.formularios.FormularioDefault;

import web.formularios.RegistrarUsuario;
import web.formularios.SeleccionarUsuario;

public class NavigationAdministrador extends Panel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Menu menu;

	
	public NavigationAdministrador(Menu menu) {
		super("navigation");
		
		this.menu=menu;
		
		agregarLinks();
		
	}
	


	private void agregarLinks() {

		this.add(new Link("linkRegistrarUsuario") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {

				menu.cambiarFormulario(new RegistrarUsuario(menu));

			}

		});
		
		
		this.add(new Link("linkModificarUsuario") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {

			menu.cambiarFormulario(new SeleccionarUsuario(menu));

			}

		});
		
		this.add(new Link("linkEliminarUsuario") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {

				menu.cambiarFormulario(new EliminarUsuario(menu));

			}

		});
		
		
		this.add(new Link("linkActualizarBaseDeDatos") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {

				menu.cambiarFormulario(new FormularioDefault(
						menu,"Opciones"));

			}

		});

	}

	

}
