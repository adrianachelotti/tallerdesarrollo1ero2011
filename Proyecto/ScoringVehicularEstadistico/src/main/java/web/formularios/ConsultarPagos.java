package web.formularios;




import hibernate.AdministradorPagos;
import hibernate.AdministradorUsuarios;

import hibernate.domain.usuarios.Cliente;
import hibernate.domain.usuarios.Pago;


import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;

import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;


import web.Formulario;

import web.Menu;
import web.utils.FeedbackLabel;




public class ConsultarPagos extends Formulario {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public class PagoItem {

		
		
		private Pago pago;

		private boolean mostrar;
		

	};
	
	

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


	
	
    private WebMarkupContainer listContainer;
	private FeedbackPanel panel;
	private ArrayList<PagoItem> pagos = new ArrayList<PagoItem>();
	private ArrayList<PagoItem> pagosMostrados = new ArrayList<PagoItem>(); // items
	@SuppressWarnings("rawtypes")
	private PageableListView listView;
	protected Form<Void> formulario;
	
    // compontentes de la consulta
    private Model<String> usernameModel;
    private TextField<String> usernameTF;
    
    private Model<String> nombreModel;
    private TextField<String> nombreTF;
    
    private Model<String> apellidoModel;
    private TextField<String> apellidoTF;
    

	private Model<String> formaPagoModel;
	private DropDownChoice<String> formaPagoDD;
	
	private Model<String> servicioModel;
	private DropDownChoice<String> servicioDD;
	
	// compontentes de la consulta
	private Model<Date> desdeModel;
	private TextField<Date> desdeTF;

	private Model<Date> hastaModel;
	private TextField<Date> hastaTF;

	
	
	public ConsultarPagos(final Menu menu){
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
		
		panel=new FeedbackPanel("mensajes");
		this.getContenido().add(panel);
		

		formulario.add(listContainer);
		

		listView = crearListView();
		listContainer.add(listView);
		PagingNavigator pager = new PagingNavigator("pager", listView); // navegador
		listContainer.add(pager);
		
        //armo el textbox de username
        usernameModel = new Model<String>();
        usernameTF = new TextField<String>("usernameTB",usernameModel, String.class);
        usernameTF.add(new ActualizacionAjax());
        listContainer.add(usernameTF);
        
        //armo el textbox de apellido
        apellidoModel = new Model<String>();
        apellidoTF = new TextField<String>("apellidoTB",apellidoModel, String.class);
        apellidoTF.add(new ActualizacionAjax());
        listContainer.add(apellidoTF);
        
        //armo el textbox de Nombre
        nombreModel = new Model<String>();
        nombreTF = new TextField<String>("nombreTB",nombreModel, String.class);
        nombreTF.add(new ActualizacionAjax());
        listContainer.add(nombreTF);
        
        desdeModel = new Model<Date>();
		desdeTF = new TextField<Date>("desde", desdeModel,Date.class);
		formulario.add(desdeTF);
		formulario.add(new FeedbackLabel("desdeF", desdeTF));
		
		
		hastaModel = new Model<Date>();
		hastaTF = new TextField<Date>("hasta", hastaModel,Date.class);
		formulario.add(new FeedbackLabel("hastaF", hastaTF));
		formulario.add(hastaTF);
        
        //dd forma de pago
        formaPagoModel = new Model<String>("Todos");
		formaPagoDD = new DropDownChoice<String>("formaPagoDD",
				formaPagoModel, Arrays.asList(new String[] { "Todos", "Tarjeta de crédito",
						"Tarjeta de débito", "Cheque","Contado" })){
			
			 /**
							 * 
							 */
							private static final long serialVersionUID = 1L;

			protected String getDefaultChoice(final Object selected)
	            {
	                return ""; //get rid of "Choose One" default
	            }  
			
			
		};
		formaPagoDD.add(new ActualizacionAjax());
		listContainer.add(formaPagoDD);
		
		 
        servicioModel = new Model<String>("Todos");
		servicioDD = new DropDownChoice<String>("servicioDD",
				servicioModel, Arrays.asList(new String[] { "Todos", "Consulta puntual",
						"Consulta mixta", "Premium" })){
			
			 /**
							 * 
							 */
							private static final long serialVersionUID = 1L;

			protected String getDefaultChoice(final Object selected)
	            {
	                return ""; //get rid of "Choose One" default
	            }  
			
		};
		servicioDD.add(new ActualizacionAjax());
		listContainer.add(servicioDD);

		
		 //cargo la consulta inicial
        cargarPagos();

	
		
		
	}

	 @SuppressWarnings({ "rawtypes", "unchecked" })
	private PageableListView crearListView() {
		 PageableListView listview=new PageableListView("pagos", pagosMostrados, 10) {

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				protected void populateItem(ListItem item) {

					final PagoItem pi=(PagoItem) item.getModelObject();
					Pago pago = pi.pago;
					Cliente cliente=(Cliente) AdministradorUsuarios.obtenerUsuario(pi.pago.getCliente().getId());

					item.add(new Label("username", cliente.getUsername()));
					item.add(new Label("nombre", cliente.getNombre()));
					item.add(new Label("apellido", cliente.getApellido()));
					item.add(new Label("servicio", cliente.getServicio().getDescripcion()));
					item.add(new Label("formaPago", pago.getFormaPago()));
					item.add(new Label("fecha", pago.getFecha().toString().substring(0, 10)));
					item.add(new Label("concepto", pago.getConcepto()));
					item.add(new Label("monto", pago.getMonto().toString()));
					
					

					

				}
			};
			
			return listview;
	}

	protected void cargarNuevaConsulta() {
		
		String username=usernameModel.getObject();
		String nombre=nombreModel.getObject();
		String apellido=apellidoModel.getObject();
		String formaPago=formaPagoModel.getObject();
		String servicio=servicioModel.getObject();
		Date desde = desdeTF.getModelObject();
		Date hasta = hastaTF.getModelObject();

         boolean mostrar = true;
         Iterator<PagoItem> it = pagos.iterator();
         

         //TODO: QUE ESTO QUEDE MAS PROLIJO!!
         while (it.hasNext()) {
                 mostrar = true;
                 PagoItem pi = it.next();
                 
                 
                 if((username!=null)&&(!pi.pago.getCliente().getUsername().startsWith(username)))
                	 mostrar=false;
                 if((mostrar)&&(nombre!=null)&&(!pi.pago.getCliente().getNombre().startsWith(nombre)))
                	 mostrar=false;
                 if((mostrar)&&(apellido!=null)&&(!pi.pago.getCliente().getApellido().startsWith(apellido)))
                	 mostrar=false;
                 if((mostrar)&&(formaPago.compareTo("Todos")!=0)){
                	if(formaPago.compareTo(pi.pago.getFormaPago())!=0)
                		mostrar=false;
                 }
                 
                 if((mostrar)&&(servicio.compareTo("Todos")!=0)){
                	
                	 String servicioUsuario=pi.pago.getCliente().getServicio().getDescripcion();
                	 if(servicioUsuario.compareTo(servicio)!=0)
                		 mostrar=false;
                	 
                 }
                 
             	if((desde!=null)&&(pi.pago.getFecha().before(desde))) mostrar=false;
    			if((mostrar)&&(hasta!=null)&&(pi.pago.getFecha().after(hasta))) mostrar=false;

                	 

                  
                 pi.mostrar = mostrar;

         }
         
         //actualizo los usuarios mostrados
         pagosMostrados.clear();
         Iterator<PagoItem> itMostrados=pagos.iterator();
         while(itMostrados.hasNext()){
                 PagoItem ui=itMostrados.next();
                 if(ui.mostrar)
                         pagosMostrados.add(ui);
                 
                 
         }

 }
	 
	 
	 private void cargarPagos() {
         pagosMostrados.clear();
         pagos.clear();
         Iterator<Pago> iteradorPagos=AdministradorPagos.obtenerPagos().iterator();
         
		while (iteradorPagos.hasNext()) {

			PagoItem pi = new PagoItem();
			pi.pago = iteradorPagos.next();

				pi.mostrar = true;
				
				pagos.add(pi);
				pagosMostrados.add(pi);
			}
			;

		}
		;
         
         
         

}
