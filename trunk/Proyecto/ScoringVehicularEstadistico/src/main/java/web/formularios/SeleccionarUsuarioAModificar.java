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



public class SeleccionarUsuarioAModificar extends Formulario {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private TextField<String> usernameTF;
	FeedbackPanel panel;

	@SuppressWarnings("unchecked")
	public SeleccionarUsuarioAModificar(final Menu menu) {
		super(menu);
		
		

		
		 Form<Void> formulario = new Form<Void>("formularioSeleccion") {

				private static final long serialVersionUID = 1L;

				protected void onSubmit() {
					String username=usernameTF.getModelObject();
					if(username.compareTo(menu.getUsuario().getUsername())==0){
						info("No puede modificar sus datos usando esta opción. Modifique los datos de su cuenta a través de la opción 'Modificar datos' de la sección 'Cuenta de usuario'");
						return;
					};

					menu.cambiarFormulario(new ModificarUsuario(menu,AdministradorUsuarios.obtenerUsuario(usernameTF.getModelObject())));

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
