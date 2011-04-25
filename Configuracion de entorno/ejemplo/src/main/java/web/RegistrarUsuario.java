package web;

import hibernate.AdministradorUsuarios;
import hibernate.domain.Usuario;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.value.ValueMap;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.apache.wicket.validation.validator.StringValidator;

public class RegistrarUsuario extends WebPage {

	private TextField<String> usernameTF;
	private PasswordTextField passTF;
	private TextField<String> nombreTF;
	private TextField<String> emailTF;
	private final ValueMap properties = new ValueMap();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public RegistrarUsuario() {

		add(new FeedbackPanel("mensajes"));
		Form<Void> formulario = new Form<Void>("formulario") {

			private static final long serialVersionUID = 1L;

			protected void onSubmit() {

				String username = usernameTF.getModelObject();
				String pass = passTF.getDefaultModelObjectAsString();
				String nombre = nombreTF.getModelObject();
				String email = emailTF.getModelObject();

				if (!AdministradorUsuarios.existeUsuario(username)) {
					Usuario usuario = new Usuario(username, pass, nombre, email);
					AdministradorUsuarios.agregarUsuario(usuario);
					info("Usuario registrado con exito.");

				} else
					info("El nombre de usuario ya existe en la bd.");

			}
		};

		add(formulario);

		// text field de username
		usernameTF = new TextField<String>("username", new Model<String>(),String.class);
		usernameTF.setRequired(true);
		formulario.add(usernameTF);

		// textfield de contraseña
		passTF = new PasswordTextField("pass", new PropertyModel<String>(properties, "password"));
		passTF.setRequired(true);
		passTF.add(StringValidator.maximumLength(20));
		passTF.add(StringValidator.minimumLength(5));
		formulario.add(passTF);

		// text field de nombre
		nombreTF = new TextField<String>("nombre", new Model<String>(),String.class);
		nombreTF.setRequired(true);
		formulario.add(nombreTF);

		// text field de email
		emailTF = new TextField<String>("email", new Model<String>(),String.class);
		formulario.add(emailTF);
		emailTF.add(EmailAddressValidator.getInstance());

		// link volver
		formulario.add(new BookmarkablePageLink("volver", Login.class));

	};

}
