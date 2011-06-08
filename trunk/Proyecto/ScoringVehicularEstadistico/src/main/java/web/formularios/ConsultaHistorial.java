package web.formularios;

import java.sql.Date;
import java.util.ArrayList;

import java.util.Iterator;

import hibernate.AdministradorConsultas;

import hibernate.domain.consultas.Consulta;

import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import web.Formulario;
import web.Menu;
import web.utils.ErrorLevelsFeedbackMessageFilter;
import web.utils.FeedbackLabel;

public class ConsultaHistorial extends Formulario {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public class ConsultaItem {

		private Consulta consulta;
		private boolean mostrar;

	};

	

	private WebMarkupContainer listContainer;
	private FeedbackPanel panel;
	private ArrayList<ConsultaItem> consultas = new ArrayList<ConsultaItem>();
	private ArrayList<ConsultaItem> consultasMostradas = new ArrayList<ConsultaItem>(); // items
	@SuppressWarnings("rawtypes")
	private PageableListView listView;
	protected Form<Void> formulario;

	// compontentes de la consulta
	private Model<Date> desdeModel;
	private TextField<Date> desdeTF;

	private Model<Date> hastaModel;
	private TextField<Date> hastaTF;

	
	public ConsultaHistorial(final Menu menu) {
		super(menu);

		listContainer = new WebMarkupContainer("theContainer");
		listContainer.setOutputMarkupId(true);

		formulario = new Form<Void>("formularioVer") {

			private static final long serialVersionUID = 1L;

			protected void onSubmit() {
				
				cargarNuevaConsulta();


			};

		};

		this.getContenido().add(formulario);

		panel = new FeedbackPanel("mensajes");
		this.getContenido().add(panel);
		
        // filteredErrorLevels will not be shown in the FeedbackPanel
        int[] filteredErrorLevels = new int[]{FeedbackMessage.ERROR};
        panel.setFilter(new ErrorLevelsFeedbackMessageFilter(filteredErrorLevels));

		formulario.add(listContainer);

		listView = crearListView();
		listContainer.add(listView);
		PagingNavigator pager = new PagingNavigator("pager", listView); // navegador
		listContainer.add(pager);

		
		desdeModel = new Model<Date>();
		desdeTF = new TextField<Date>("desde", desdeModel,Date.class);
		formulario.add(desdeTF);
		formulario.add(new FeedbackLabel("desdeF", desdeTF));
		
		
		hastaModel = new Model<Date>();
		hastaTF = new TextField<Date>("hasta", hastaModel,Date.class);
		formulario.add(new FeedbackLabel("hastaF", hastaTF));
		formulario.add(hastaTF);

		

		
		// cargo la consulta inicial
		cargarConsultas();

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private PageableListView crearListView() {
		PageableListView listview = new PageableListView("consultas",
				consultasMostradas, 10) {

			/**
				 * 
				 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem item) {

				final ConsultaItem ci = (ConsultaItem) item.getModelObject();
				Consulta consulta = ci.consulta;

				item.add(new Label("tipoDoc", consulta.getTipoDoc()));
				item.add(new Label("documento", consulta.getDocumento().toString()));
				item.add(new Label("nombre", consulta.getNombre()));
				item.add(new Label("apellido", consulta.getApellido()));
				item.add(new Label("fecha", consulta.getFecha().toString().substring(0, 10)));
							
				item.add(new Link("link") {

					private static final long serialVersionUID = 1L;

					@Override
					public void onClick() {

						menu.cambiarFormulario(new Informe(menu,ci.consulta));

					}

				});

			}
		};

		return listview;
	}

	protected void cargarNuevaConsulta() {

		Date desde = desdeTF.getModelObject();
		Date hasta = hastaTF.getModelObject();
		
		boolean mostrar = true;
		Iterator<ConsultaItem> it = consultas.iterator();

		
		while (it.hasNext()) {
			mostrar = true;
			ConsultaItem ci = it.next();
			if((desde!=null)&&(ci.consulta.getFecha().before(desde))) mostrar=false;
			if((mostrar)&&(hasta!=null)&&(ci.consulta.getFecha().after(hasta))) mostrar=false;

			ci.mostrar = mostrar;

		}

		// actualizo los usuarios mostrados
		consultasMostradas.clear();
		Iterator<ConsultaItem> itMostrados = consultas.iterator();
		while (itMostrados.hasNext()) {
			ConsultaItem ci = itMostrados.next();
			if (ci.mostrar)
				consultasMostradas.add(ci);

		}

	}

	private void cargarConsultas() {
		consultasMostradas.clear();
		consultas.clear();
		Iterator<Consulta> iteradorConsultas= AdministradorConsultas.obtenerConsultasPorUsuario(menu.getUsuario().getUsername()).iterator();

		while (iteradorConsultas.hasNext()) {

			ConsultaItem ci = new ConsultaItem();
			ci.consulta = iteradorConsultas.next();
			ci.mostrar = true;
			consultas.add(ci);
			consultasMostradas.add(ci);

		}
		;

	}

}
