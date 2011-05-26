package web;


import org.apache.wicket.markup.html.link.Link;

import org.apache.wicket.markup.html.panel.Panel;


import web.formularios.Ayuda;
import web.formularios.ConsultarPagos;

import web.formularios.FormularioDefault;
import web.formularios.ModificarDatosCuenta;

import web.formularios.Consulta;
import web.formularios.ConsultaHistorial;
import web.formularios.Contactenos;


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

				menu.cambiarFormulario(new ModificarDatosCuenta(menu));

			}

		});
		
		this.add(new Link("linkVerPagos") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {

				menu.cambiarFormulario(new ConsultarPagos(menu));

			}

		});
		
		
		this.add(new Link("linkRealizarConsulta") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {

				menu.cambiarFormulario(new Consulta(menu));

			}

		});
		
		
		this.add(new Link("linkVerHistorial") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {

				menu.cambiarFormulario(new ConsultaHistorial(menu));
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

	}

	

}
