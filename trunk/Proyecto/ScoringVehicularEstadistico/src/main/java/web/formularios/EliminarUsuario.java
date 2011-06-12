package web.formularios;


import hibernate.AdministradorUsuarios;



import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;



import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;


import web.Formulario;

import web.Menu;
import web.utils.ErrorLevelsFeedbackMessageFilter;
import web.utils.FeedbackLabel;
import web.validators.ExisteUsuarioValidator;



public class EliminarUsuario extends Formulario {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private TextField<String> usernameTF;
	FeedbackPanel panel;

	@SuppressWarnings("unchecked")
	public EliminarUsuario(final Menu menu) {
		super(menu);
		
		

		
		 Form<Void> formulario = new Form<Void>("formularioEliminar") {

				private static final long serialVersionUID = 1L;

				protected void onSubmit() {

					if(usernameTF.getModelObject().compareTo(menu.getUsuario().getUsername())==0){
						info("No puede eliminarse a sí mismo.");
						return;
					}
					AdministradorUsuarios.eliminarUsuario(usernameTF.getModelObject());
					info("Usuario eliminado con exito.");
					this.setVisible(false);

				}
			};
			this.getContenido().add(formulario);
			
			this.getContenido().add(formulario);
			panel=new FeedbackPanel("mensajes");
			this.getContenido().add(panel);
			

			
	        // filteredErrorLevels will not be shown in the FeedbackPanel
	        int[] filteredErrorLevels = new int[]{FeedbackMessage.ERROR};
	        panel.setFilter(new ErrorLevelsFeedbackMessageFilter(filteredErrorLevels));
	        
	            
			
			// text field de username
			usernameTF = new TextField<String>("Nombre de usuario", new Model<String>(),	String.class);
			usernameTF.setRequired(true);
			usernameTF.add(new ExisteUsuarioValidator());
			formulario.add(usernameTF);
	   		formulario.add(new FeedbackLabel("usernameF", usernameTF));

		

	};
}
