package web.formularios;



import hibernate.AdministradorUsuarios;


import hibernate.domain.usuarios.Administrador;
import hibernate.domain.usuarios.Cliente;
import hibernate.domain.usuarios.Operador;
import hibernate.domain.usuarios.Usuario;


import java.util.ArrayList;


import java.util.Iterator;



import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.TextField;

import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import web.Formulario;

import web.Menu;



public class AdministrarPerfiles extends Formulario {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public class UsuarioItem {

		
		
		
		
		private Usuario usuario;
		private String tipo;

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
    
    private Model<Integer> cantidadPorPaginaModel;
    private TextField<Integer> cantidadPorPaginaTF;

	
	
	
	
	public AdministrarPerfiles(final Menu menu){
		super(menu);
		
		listContainer = new WebMarkupContainer("theContainer"); 
        listContainer.setOutputMarkupId(true);

		
		formulario = new Form<Void>("formularioVer") {

			private static final long serialVersionUID = 1L;

			protected void onSubmit() {
				
				 
                
                Iterator<UsuarioItem> it = usuarios.iterator();
                while (it.hasNext()) {

                        UsuarioItem ui = it.next();
                        
                    /*    if((ui.tipo.compareTo("cliente")==0)&&(!ui.usuario.esCliente())){
                        	Cliente cliente=new Cliente();
                        	cliente.copiar(ui.usuario);
                        	ui.usuario.setActivado(false);
                        	ui.usuario.setObsoleto(true);
                        	cliente.setObsoleto(false);
                        	AdministradorUsuarios.modificarUsuario(ui.usuario);
                        	AdministradorUsuarios.agregarUsuario(cliente);
                        	
                        	
                        }else */
                            if((ui.tipo.compareTo("administrador")==0)&&(!ui.usuario.esAdministrador())){
                            	Administrador administrador=new Administrador();
                            	administrador.copiar(ui.usuario);
                            	ui.usuario.setActivado(false);
                            	ui.usuario.setObsoleto(true);
                            	administrador.setObsoleto(false);
                            	AdministradorUsuarios.modificarUsuario(ui.usuario);
                            	AdministradorUsuarios.agregarUsuario(administrador);
                            	
                            	
                            }else if((ui.tipo.compareTo("operador")==0)&&(!ui.usuario.esOperador())){
                            	Operador operador=new Operador();
                            	operador.copiar(ui.usuario);
                            	ui.usuario.setActivado(false);
                            	ui.usuario.setObsoleto(true);
                            	operador.setObsoleto(false);
                            	AdministradorUsuarios.modificarUsuario(ui.usuario);
                            	AdministradorUsuarios.agregarUsuario(operador);
                            	
                            	
                            }
                        
                               

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
		listView.setReuseItems(true);
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
        
        
      //armo el textbox de cantidad de paginas
        cantidadPorPaginaModel = new Model<Integer>();
        cantidadPorPaginaTF = new TextField<Integer>("Cantidad por pagina",cantidadPorPaginaModel, Integer.class);
        cantidadPorPaginaTF.add(new ActualizacionAjax());
        listContainer.add(cantidadPorPaginaTF);
        
       

		
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
					// add the radio group to select system or department (organizational) administrators
					if(usuario.esAdministrador()) ui.tipo="administrador";
					else if(usuario.esCliente()) ui.tipo="cliente";
					else if(usuario.esOperador()) ui.tipo="operador";
					final RadioGroup radioGroup = new RadioGroup("perfiles", new PropertyModel(ui, "tipo"));
					item.add(radioGroup);
					//radioGroup.add(new Radio("cliente", new Model("cliente")));
					radioGroup.add(new Radio("administrador", new Model("administrador")));
					radioGroup.add(new Radio("operador", new Model("operador")));
	                ;

					

				}
			};
			
			return listview;
	}

	protected void cargarNuevaConsulta() {
		
		String username=usernameModel.getObject();
		String nombre=nombreModel.getObject();
		String apellido=apellidoModel.getObject();
		
		Integer cantPaginas=cantidadPorPaginaModel.getObject();

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
         
         //actualizo el listview
         if(cantPaginas!=null)
         listView.setRowsPerPage(cantPaginas);

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
				
				ui.mostrar = true;
				

				usuarios.add(ui);
				usuariosMostrados.add(ui);
			}
			;

		}
		;
         
         
         
 }



	
}
