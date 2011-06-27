package web;



import hibernate.AdministradorUsuarios;
import hibernate.AppInit;
import hibernate.domain.usuarios.Usuario;


import org.apache.wicket.markup.html.WebPage;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.value.ValueMap;

import web.validators.ExisteUsuarioValidator;

public class Login2 extends WebPage {

	private TextField<String> usernameTF;
	private PasswordTextField passTF;
	private final ValueMap properties = new ValueMap();

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Login2() {
		
				
		Form<Void> formulario = new Form<Void>("formulario") {

			private static final long serialVersionUID = 1L;

			protected void onSubmit() {

				String username = usernameTF.getModelObject();
				String pass = passTF.getDefaultModelObjectAsString();
				Sesion sesion = (Sesion) getSession();
				sesion.authenticate(username, pass);
				if (sesion.signedIn()) {

					Usuario usuario=sesion.getUsuario();
					
					setResponsePage(new Menu(AdministradorUsuarios.obtenerUsuario(usuario.getUsername())));
					info("Sesión iniciada correctamente.");
									
				} else
					info("El username y/o contraseña ingrsados son incorrectos o el usuario no fue activado todavía.");
			}
		};

		add(formulario);
		FeedbackPanel panel=new FeedbackPanel("mensajes");
		formulario.add(panel);
		
		if(AppInit.inicializarAplicacion()) info("No hay usuarios cargados en la base de datos. Ingrese al sistema usando username='admin' y constraseña='admin'.");
		else{
			info("Por favor, ingrese su nombre de usuario y constraseña.");
		}
	
		

		// text field de username
		usernameTF = new TextField<String>("Usuario", new Model<String>(),	String.class);
		usernameTF.setRequired(true);
		usernameTF.add(new ExisteUsuarioValidator());
		
		formulario.add(usernameTF);

		// textfield de contraseña
		passTF = new PasswordTextField("Contraseña", new PropertyModel<String>(properties, "password"));
		passTF.setRequired(true);
		formulario.add(passTF);
						
		formulario.add(new BookmarkablePageLink("linkSolicitarUsuario",SolicitarServicio.class));

	}




	

}
