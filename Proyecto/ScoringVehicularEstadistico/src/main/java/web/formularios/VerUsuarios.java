package web.formularios;

import hibernate.AdministradorServicios;
import hibernate.AdministradorUsuarios;
import hibernate.domain.usuarios.Administrador;
import hibernate.domain.usuarios.Cliente;
import hibernate.domain.usuarios.Operador;
import hibernate.domain.usuarios.Rubro;
import hibernate.domain.usuarios.Servicio;
import hibernate.domain.usuarios.Usuario;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.CheckBoxMultipleChoice;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.value.ValueMap;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.apache.wicket.validation.validator.StringValidator;

import web.Formulario;

import web.Menu;
import web.utils.ErrorLevelsFeedbackMessageFilter;
import web.utils.FeedbackLabel;
import web.validators.UsernameValidator;



public class VerUsuarios extends Formulario {

	
	public class UsuarioItem {

		@SuppressWarnings("unused")
		private boolean eliminar;
		@SuppressWarnings("unused")
		private boolean activar;
		private Usuario usuario;
		

	};

	
	

	
	
	

	FeedbackPanel panel;
	
	private ArrayList<UsuarioItem> listaUsuarios = new ArrayList<UsuarioItem>();

	private PageableListView listView;

	
	
	
	protected Form<Void> formulario;
	
	@SuppressWarnings({  "unchecked", "rawtypes" })
	public VerUsuarios(final Menu menu){
		super(menu);
		
		formulario = new Form<Void>("formularioVer") {

			private static final long serialVersionUID = 1L;

			protected void onSubmit() {
				
				 
                
                Iterator<UsuarioItem> it = listaUsuarios.iterator();
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
		
		this.getContenido().add(formulario);
		panel=new FeedbackPanel("mensajes");
		this.getContenido().add(panel);
		

		
		Iterator<Usuario> iteradorUsuarios = AdministradorUsuarios.obtenerUsuarios().iterator();
		while(iteradorUsuarios.hasNext()){
			UsuarioItem usuarioItem=new UsuarioItem();
			usuarioItem.eliminar=false;
			usuarioItem.usuario=iteradorUsuarios.next();
			usuarioItem.activar=usuarioItem.usuario.isActivado();
			listaUsuarios.add(usuarioItem);		
			
		}

		listView = new PageableListView("usuarios", listaUsuarios, 10) {

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

		this.formulario.add(listView);

		PagingNavigator pager = new PagingNavigator("pager", listView); // navegador
		formulario.add(pager);
	
		
		
	}

	
}
