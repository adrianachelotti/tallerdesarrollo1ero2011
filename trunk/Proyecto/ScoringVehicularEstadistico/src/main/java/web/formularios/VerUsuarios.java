package web.formularios;


import hibernate.AdministradorUsuarios;

import hibernate.domain.usuarios.Cliente;

import hibernate.domain.usuarios.Usuario;


import java.util.ArrayList;
import java.util.Arrays;


import java.util.Iterator;



import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import web.Formulario;

import web.Menu;



public class VerUsuarios extends Formulario {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public class UsuarioItem {

		
		private boolean eliminar;
		
		private boolean activar;
		private Usuario usuario;

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
	private ArrayList<UsuarioItem> usuarios = new ArrayList<UsuarioItem>();
	private ArrayList<UsuarioItem> usuariosMostrados = new ArrayList<UsuarioItem>(); // items
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
    
	private Model<String> tipoUsuarioModel;
	private DropDownChoice<String> tipoUsuarioDD;
	
	private Model<String> servicioModel;
	private DropDownChoice<String> servicioDD;

	
	
	public VerUsuarios(final Menu menu){
		super(menu);
		
		listContainer = new WebMarkupContainer("theContainer"); 
        listContainer.setOutputMarkupId(true);

		
		formulario = new Form<Void>("formularioVer") {

			private static final long serialVersionUID = 1L;

			protected void onSubmit() {
				
				 
                
                Iterator<UsuarioItem> it = usuarios.iterator();
                while (it.hasNext()) {

                        UsuarioItem ui = it.next();
                        
                        ui.usuario.setActivado(ui.activar);
                        AdministradorUsuarios.modificarUsuario(ui.usuario);
                        
                        if (ui.eliminar) AdministradorUsuarios.eliminarUsuario(ui.usuario.getUsername());
                               

                }
                info("Se aplicaron los cambios con exito.");
                this.setVisible(false);
                
                

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
        
        //dd tipo
        tipoUsuarioModel = new Model<String>("Todos");
		tipoUsuarioDD = new DropDownChoice<String>("tipo",
				tipoUsuarioModel, Arrays.asList(new String[] { "Todos", "Cliente",
						"Administrador", "Operador" })){
			
			 /**
							 * 
							 */
							private static final long serialVersionUID = 1L;

			protected String getDefaultChoice(final Object selected)
	            {
	                return ""; //get rid of "Choose One" default
	            }  
			
			
		};
		tipoUsuarioDD.add(new ActualizacionAjax());
		listContainer.add(tipoUsuarioDD);
		
		 //dd tipo
        servicioModel = new Model<String>("Todos");
		servicioDD = new DropDownChoice<String>("servicio",
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
        cargarUsuarios();

	
		
		
	}

	 @SuppressWarnings({ "rawtypes", "unchecked" })
	private PageableListView crearListView() {
		 PageableListView listview=new PageableListView("usuarios", usuariosMostrados, 10) {

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				protected void populateItem(ListItem item) {

					final UsuarioItem ui=(UsuarioItem) item.getModelObject();
					Usuario usuario = ui.usuario;

					item.add(new Label("username", usuario.getUsername()));
					item.add(new Label("nombre", usuario.getNombre()));
					item.add(new Label("apellido", usuario.getApellido()));
					item.add(new Label("tipo", usuario.esCliente()?"Cliente":usuario.esAdministrador()?"Administrador":"Operador"));
					item.add(new Label("email", usuario.getEmail()));
					if(usuario.esCliente()){
						Cliente cliente=(Cliente) usuario;
						item.add(new Label("telefono", cliente.getTelefono()));
						item.add(new Label("servicio", cliente.getServicio().getDescripcion()));
					}else{
						item.add(new Label("telefono", "-"));
						item.add(new Label("servicio", "-"));
						;
					}
					CheckBox cheliminar=new CheckBox("checkEliminar",new PropertyModel(ui, "eliminar"));
					item.add(cheliminar);
					if(usuario.getUsername().compareTo(menu.getUsuario().getUsername())==0) cheliminar.setEnabled(false);
					item.add(new CheckBox("checkActivar",new PropertyModel(ui, "activar")));
	                item.add(new Link("link") {

	                        private static final long serialVersionUID = 1L;

	                        @Override
	                        public void onClick() {
	                                
	                                
	                                menu.cambiarFormulario(new ModificarUsuario(menu,ui.usuario));

	                        }

	                });

					

				}
			};
			
			return listview;
	}

	protected void cargarNuevaConsulta() {
		
		String username=usernameModel.getObject();
		String nombre=nombreModel.getObject();
		String apellido=apellidoModel.getObject();
		String tipo=tipoUsuarioModel.getObject();
		String servicio=servicioModel.getObject();

         boolean mostrar = true;
         Iterator<UsuarioItem> it = usuarios.iterator();
         

         //TODO: QUE ESTO QUEDE MAS PROLIJO!!
         while (it.hasNext()) {
                 mostrar = true;
                 UsuarioItem ui = it.next();
                 
                 
                 if((username!=null)&&(!ui.usuario.getUsername().startsWith(username)))
                	 mostrar=false;
                 if((mostrar)&&(nombre!=null)&&(!ui.usuario.getNombre().startsWith(nombre)))
                	 mostrar=false;
                 if((mostrar)&&(apellido!=null)&&(!ui.usuario.getApellido().startsWith(apellido)))
                	 mostrar=false;
                 if((mostrar)&&(tipo.compareTo("Todos")!=0)){
                	 if((!ui.usuario.esCliente())&&(tipo.compareTo("Cliente")==0))
                		 mostrar=false;
                	 if((mostrar)&&!ui.usuario.esAdministrador()&&(tipo.compareTo("Administrador")==0))
                		 mostrar=false;
                	 if((mostrar)&&!ui.usuario.esOperador()&&(tipo.compareTo("Operador")==0))
                		 mostrar=false;
                 }
                 
                 if((mostrar)&&(servicio.compareTo("Todos")!=0)&&(ui.usuario.esCliente())){
                	
                	 String servicioUsuario=((Cliente)ui.usuario).getServicio().getDescripcion();
                	 if(servicioUsuario.compareTo(servicio)!=0)
                		 mostrar=false;
                	 
                 }
                	 

                  
                 ui.mostrar = mostrar;

         }
         
         //actualizo los usuarios mostrados
         usuariosMostrados.clear();
         Iterator<UsuarioItem> itMostrados=usuarios.iterator();
         while(itMostrados.hasNext()){
                 UsuarioItem ui=itMostrados.next();
                 if(ui.mostrar)
                         usuariosMostrados.add(ui);
                 
                 
         }

 }
	 
	 
	 private void cargarUsuarios() {
         usuariosMostrados.clear();
         usuarios.clear();
         Iterator<Usuario> iteradorUsuarios=AdministradorUsuarios.obtenerUsuarios().iterator();
         
		while (iteradorUsuarios.hasNext()) {

			UsuarioItem ui = new UsuarioItem();
			ui.usuario = iteradorUsuarios.next();

			if (ui.usuario.getUsername().compareTo(
					menu.getUsuario().getUsername()) != 0) {
				ui.eliminar = false;
				ui.mostrar = true;
				ui.activar = ui.usuario.isActivado();

				usuarios.add(ui);
				usuariosMostrados.add(ui);
			}
			;

		}
		;
         
         
         
 }



	
}
