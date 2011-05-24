package web;


import org.apache.wicket.markup.html.link.Link;

import org.apache.wicket.markup.html.panel.Panel;

import web.formularios.ConsultarPagos;
import web.formularios.FormularioDefault;

import web.formularios.ModificarDatosCuenta;


public class NavigationCliente extends Panel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Menu menu;

	
	public NavigationCliente(Menu menu) {
		super("navigation");
		
		this.menu=menu;
		
		agregarLinks();
		
	}
	


	private void agregarLinks() {

		this.add(new Link("linkOpciones") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {

				menu.cambiarFormulario(new FormularioDefault(
						menu,"Opciones"));

			}

		});
		
		
		this.add(new Link("linkModificarDatos") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {

				menu.cambiarFormulario(new ModificarDatosCuenta(menu ));
				menu.mostrarMensajes(true);
				info("Modifique los datos de la cuenta y luego seleccione Aplicar cambios.");

			}

		});
		
		this.add(new Link("linkLogout") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				

                this.getRequestCycle().setResponsePage(Logout.class);
                this.getRequestCycle().setRedirect(true);
                
				
			}

		});
		
		
		this.add(new Link("linkConsultarPagos") {

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

				menu.cambiarFormulario(new FormularioDefault(
						menu, "Realizar consulta"));

			}

		});
		
		this.add(new Link("linkHistorialInformes") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {

				menu.cambiarFormulario(new FormularioDefault(
						menu, "Historial informe"));

			}

		});
		
		this.add(new Link("linkEstadisticas") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {

				menu.cambiarFormulario(new FormularioDefault(
						menu,"Estadisticas"));

			}

		});

	}

	

}
