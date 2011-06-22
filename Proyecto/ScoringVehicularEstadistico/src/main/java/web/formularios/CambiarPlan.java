package web.formularios;



import hibernate.AdministradorClientes;
import hibernate.AdministradorServicios;
import hibernate.domain.usuarios.Cliente;
import hibernate.domain.usuarios.Servicio;



import java.util.ArrayList;


import java.util.Iterator;




import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;


import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;

import org.apache.wicket.markup.html.panel.FeedbackPanel;

import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import web.Formulario;

import web.Menu;



public class CambiarPlan extends Formulario {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public class ServicioItem {

		
		
		
		@SuppressWarnings("unused")
		private boolean seleccionado;
		private Servicio servicio;
		@SuppressWarnings("unused")
		private boolean mostrar;
		

	};
	
	/*

    private class ActualizacionAjax extends AjaxFormComponentUpdatingBehavior {

            private static final long serialVersionUID = 1L;

            public ActualizacionAjax() {
                    super("onchange");

            }

            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                    // esto es lo que hace al seleccionar una opción de alguno de los dd
                    // o ingresar un valor en los textboxes
                    cargarNuevaConsulta();
                    target.addComponent(listContainer);

            }

    }

*/
	
	
    private WebMarkupContainer listContainer;
	private FeedbackPanel panel;
	private ArrayList<ServicioItem> servicios = new ArrayList<ServicioItem>();
	private ArrayList<ServicioItem> serviciosMostrados = new ArrayList<ServicioItem>(); // items
	@SuppressWarnings("rawtypes")
	private PageableListView listView;
	protected Form<Void> formulario;
	@SuppressWarnings("rawtypes")
	private RadioGroup grupo;
	private String servicioSeleccionado;
	private Cliente cliente;
	
    
    /*
    private Model<Integer> cantidadPorPaginaModel;
    private TextField<Integer> cantidadPorPaginaTF;*/

	
	
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public CambiarPlan(final Menu menu){
		super(menu);
		
		listContainer = new WebMarkupContainer("theContainer"); 
        listContainer.setOutputMarkupId(true);
        
        cliente=(Cliente) menu.getUsuario();

		
		formulario = new Form<Void>("formularioVer") {

			private static final long serialVersionUID = 1L;

			protected void onSubmit() {
				
				cliente.setServicio(AdministradorServicios.obtenerServicio(servicioSeleccionado));
				menu.actualizarTexto();
				AdministradorClientes.modificarCliente(cliente);			 
                info("Se aplicaron los cambios con exito.");
                this.setVisible(false);
                
                

        };

				
				
				

			};

			
	
		
		
		this.getContenido().add(formulario);
		
		panel=new FeedbackPanel("mensajes");
		this.getContenido().add(panel);
		

		formulario.add(listContainer);
		

		listView = crearListView();
		//listContainer.add(listView);
		listView.setReuseItems(true);
	/*	PagingNavigator pager = new PagingNavigator("pager", listView); // navegador
		listContainer.add(pager);*/
		
      
        
        
      //armo el textbox de cantidad de paginas
      /*  cantidadPorPaginaModel = new Model<Integer>();
        cantidadPorPaginaTF = new TextField<Integer>("Cantidad por pagina",cantidadPorPaginaModel, Integer.class);
        cantidadPorPaginaTF.add(new ActualizacionAjax());
        listContainer.add(cantidadPorPaginaTF);*/
          

		
		 //cargo la consulta inicial
        cargarServicios();
        
        grupo=new RadioGroup("opciones",new PropertyModel(this, "servicioSeleccionado"));
        listContainer.add(grupo);
        grupo.add(listView);
        
        
        
        servicioSeleccionado=cliente.getServicio().getCodigo();

	
		
		
	}

	 @SuppressWarnings({ "rawtypes", "unchecked" })
	private PageableListView crearListView() {
		 
		 /* Dejo que sea "pageable" por si se agregan muchos mas servicio, así queda */
		 PageableListView listview=new PageableListView("servicios", serviciosMostrados, 10) {

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				protected void populateItem(ListItem item) {

					final ServicioItem si=(ServicioItem) item.getModelObject();
					Servicio servicio = si.servicio;

					item.add(new Label("nombre", servicio.getDescripcion()));
					item.add(new Label("descripcion", servicio.getDetalle()));
					item.add(new Label("maximoConsultas", servicio.getMaximoConsultas()==-1?"Ilimitadas":servicio.getMaximoConsultas().toString()));
					item.add(new Label("precio", "$"+servicio.getPrecio()+" por "+servicio.getUnidad()));
					item.add(new Radio("seleccionado",new Model(servicio.getCodigo())));
					
					
	                

					

				}
			};
			
			return listview;
	}

	/*protected void cargarNuevaConsulta() {
		
		
		Integer cantPaginas=cantidadPorPaginaModel.getObject();


         Iterator<ServicioItem> it = servicios.iterator();
         Cliente cliente=(Cliente) menu.getUsuario();
         

        
         while (it.hasNext()) {

                 ServicioItem si = it.next();
                 si.mostrar=cliente.getServicio().mejor(si.servicio);                  	 
                  

         }
         
         //actualizo los usuarios mostrados
         serviciosMostrados.clear();
         Iterator<ServicioItem> itMostrados=servicios.iterator();
         while(itMostrados.hasNext()){
                 ServicioItem si=itMostrados.next();
                 if(si.mostrar)
                         serviciosMostrados.add(si);
                 
                 
         }
         
         //actualizo el listview
     if(cantPaginas!=null)
         listView.setRowsPerPage(cantPaginas);

 }*/
	 
	 
	 private void cargarServicios() {
         serviciosMostrados.clear();
         servicios.clear();
         Iterator<Servicio> iteradorServicios=AdministradorServicios.obtenerServicios().iterator();
         
         
		while (iteradorServicios.hasNext()) {

			ServicioItem si = new ServicioItem();
			si.servicio = iteradorServicios.next();

				
								
				servicios.add(si);
				si.mostrar=false;
				if(agregar(si.servicio)){
					si.mostrar=true;
					serviciosMostrados.add(si);
				};
			
		}
		;
         
         
         
 }

	private boolean agregar(Servicio servicio) {
		if(servicio.getMaximoConsultas()==-1) return true;
		
		if(cliente.getCantidadConsultas()<=servicio.getMaximoConsultas()) return true;
		else return false;
		
	}



	
}
