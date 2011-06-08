package web.formularios;


import hibernate.AdministradorConductores;
import hibernate.AdministradorConsultas;
import hibernate.AdministradorUsuarios;

import hibernate.domain.conductores.Conductor;
import hibernate.domain.consultas.Consulta;


import java.util.ArrayList;


import java.util.Iterator;


import org.apache.wicket.markup.html.basic.Label;

import org.apache.wicket.markup.html.form.Form;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.FeedbackPanel;


import web.Formulario;
import web.Menu;



public class ResultadoConsulta extends Formulario {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public class ConsultaItem {

		
		private Consulta consulta;


	};
	
	

	private FeedbackPanel panel;
	private ArrayList<ConsultaItem> consultasMostradas = new ArrayList<ConsultaItem>(); // items
	@SuppressWarnings("rawtypes")
	private PageableListView listView;
	protected Form<Void> formulario;
	private Consulta consultaBase;
	


	
	
	public ResultadoConsulta(Consulta consultaObtenida,final Menu menu){
		super(menu);
		
		
			
		consultaBase=consultaObtenida;
		
		
		panel=new FeedbackPanel("mensajes");
		this.getContenido().add(panel);
		

		
		

		listView = crearListView();
		this.getContenido().add(listView);
		PagingNavigator pager = new PagingNavigator("pager", listView); // navegador
		this.getContenido().add(pager);
		    		
		
        cargarConsultas();

	
		
		
	}

	 @SuppressWarnings({ "rawtypes", "unchecked" })
	private PageableListView crearListView() {
		 PageableListView listview=new PageableListView("consultas", consultasMostradas, 10) {

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				protected void populateItem(ListItem item) {

					final ConsultaItem ci=(ConsultaItem) item.getModelObject();
					Conductor conductor=AdministradorConductores.obtenerConductor(ci.consulta.getTipoDoc(), ci.consulta.getDocumento());
					

					
					item.add(new Label("nombre", conductor.getNombre()));
					item.add(new Label("apellido",conductor.getApellidos()));
					item.add(new Label("tipoDoc", conductor.getTipoDocumento()));
					item.add(new Label("documento", conductor.getNumeroDocumento().toString()));
					item.add(new Label("sexo",conductor.getSexo()));
					item.add(new Label("residencia",conductor.getLugarResidencia()));
					item.add(new Label("profesion",conductor.getProfesion()));
					item.add(new Label("fechanacimiento",conductor.getFechaNacimiento().toString().substring(0,10)));
					
					
					
					
					
					
					
					
					
					
	                item.add(new Link("link") {

	                        private static final long serialVersionUID = 1L;

	                        @Override
	                        public void onClick() {
	                                //actualizar datos de la consulta y mostrar informe
	                        		ci.consulta.setUsuario(menu.getUsuario());
	                        		menu.getUsuario().aumentarConsutlas();
	                        		AdministradorUsuarios.modificarUsuario(menu.getUsuario());
	                        		java.util.Date hoy = new java.util.Date();
	                        		ci.consulta.setFecha(new java.sql.Date(hoy.getTime()));
	                        		ci.consulta.copiarInformacionMostrar(consultaBase);
	                                AdministradorConsultas.agregarConsulta(ci.consulta);
	                                menu.cambiarFormulario(new Informe(menu,ci.consulta));

	                        }

	                });

					

				}
			};
			
			return listview;
	}


	 
	 private void cargarConsultas() {
         consultasMostradas.clear();
         
         Iterator<Consulta> iteradorConsultas=AdministradorConsultas.obtenerConsultasPosibles(consultaBase).iterator();
         
         
         while(iteradorConsultas.hasNext()){

        	 
                 ConsultaItem ci=new ConsultaItem();
                 ci.consulta=iteradorConsultas.next();
                 consultasMostradas.add(ci);       
                 
                 
                 
         };
         
         
         
 }


}
