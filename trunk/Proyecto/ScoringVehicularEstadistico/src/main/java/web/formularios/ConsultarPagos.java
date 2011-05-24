package web.formularios;

import java.util.List;


import hibernate.AdministradorTransacciones;
import hibernate.domain.usuarios.Transaccion;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;

import web.Formulario;

import web.Menu;


public class ConsultarPagos extends Formulario {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List data; // contiene los items que muestra el listview
	private PageableListView listView; // listview con los datos

	public ConsultarPagos(Menu menu) {
		super(menu, "Consultar Pagos");

		/* obtengo las transacciones del cliente */
		data = AdministradorTransacciones.obtenerTransaccionesDeCliente(menu
				.getUsuario().getUsername());

		listView = new PageableListView("pagos", data, 10) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem item) {

				Transaccion transaccion = (Transaccion) item.getModelObject();

				item.add(new Label("fecha", transaccion.getFecha().toString()));
				item.add(new Label("monto", Double.toString(transaccion.getMonto())));
				item.add(new Label("periodo", transaccion.getPeriodo()));
				item.add(new Label("formaPago", transaccion.getFormaPago()));

			}
		};

		this.getContenido().add(listView);

		PagingNavigator pager = new PagingNavigator("pager", listView); // navegador
		this.getContenido().add(pager);

	};
}
