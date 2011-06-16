package web;




import org.apache.wicket.markup.html.link.Link;

import org.apache.wicket.markup.html.panel.Panel;


import web.formularios.ActualizarBaseDeDatos;
import web.formularios.AdministrarPerfiles;
import web.formularios.Ayuda;
import web.formularios.ConsultaHistorialGeneral;
import web.formularios.Consultar;
import web.formularios.ConsultaHistorial;
import web.formularios.ConsultarPagos;
import web.formularios.Contactenos;
import web.formularios.EliminarUsuario;
import web.formularios.FormularioDefault;
import web.formularios.ModificarDatosCuenta;
import web.formularios.RegistrarUsuario;
import web.formularios.SeleccionarUsuarioAModificar;
import web.formularios.VerUsuarios;


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

				menu.cambiarFormulario(new ModificarDatosCuenta(menu));

			}

		});
		
	
		
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

				menu.cambiarFormulario(new SeleccionarUsuarioAModificar(menu));

			}

		});
		
		this.add(new Link("linkEliminarUsuario") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {

				menu.cambiarFormulario(new EliminarUsuario(menu));

				
			}

		});
		
		this.add(new Link("linkVerUsuarios") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {

				menu.cambiarFormulario(new VerUsuarios(menu));

			}

		});
		
		this.add(new Link("linkAdministrarPerfiles") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {

				menu.cambiarFormulario(new AdministrarPerfiles(menu));

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

				menu.cambiarFormulario(new Consultar(menu));

			}

		});
		
		
		this.add(new Link("linkVerHistorial") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {

				menu.cambiarFormulario(new ConsultaHistorial(menu));
			}

		});
		
		this.add(new Link("linkVerHistorialGeneral") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {

				menu.cambiarFormulario(new ConsultaHistorialGeneral(menu));
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
