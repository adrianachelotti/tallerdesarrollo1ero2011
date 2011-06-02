package web;




import org.apache.wicket.markup.html.link.Link;

import org.apache.wicket.markup.html.panel.Panel;


import web.formularios.ActualizarBaseDeDatos;
import web.formularios.Ayuda;
import web.formularios.Contactenos;

import web.formularios.FormularioDefault;
import web.formularios.ModificarDatosAdministrador;


public class NavigationOperador extends Panel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Menu menu;

	
	public NavigationOperador(Menu menu) {
		super("navigation");
		
		this.menu=menu;
		
		agregarLinks();
		
	};
	


	@SuppressWarnings("rawtypes")
	private void agregarLinks() {

		this.add(new Link("linkHome") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {

				menu.cambiarFormulario(new FormularioDefault(
						menu,"Home"));

			}

		});
		
		

		this.add(new Link("linkModificarDatos") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {

				menu.cambiarFormulario(new ModificarDatosAdministrador(menu));

			}

		});
		
	
		
		
		
		this.add(new Link("linkActualizarBD") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {

				menu.cambiarFormulario(new ActualizarBaseDeDatos(menu));

			}

		});
		
		this.add(new Link("linkRespaldarBD") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {

				menu.cambiarFormulario(new FormularioDefault(
						menu,"Respaldar bd"));

			}

		});
		
		
		
		
		this.add(new Link("linkAyuda") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {

				menu.cambiarFormulario(new Ayuda(menu));
			}

		});
		
		this.add(new Link("linkContactenos") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {

				menu.cambiarFormulario(new Contactenos(menu));
			}

		});
		
		this.add(new Link("linkLogout") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {

				setResponsePage(new Logout());
			}

		});
		
		
		
	};
		
	

	

}
