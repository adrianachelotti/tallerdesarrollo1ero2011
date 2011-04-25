package web;

import hibernate.domain.Usuario;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.value.ValueMap;

public class Login extends WebPage {

	private TextField<String> usernameTF;
	private PasswordTextField passTF;
	private Label datosL;
	private Model<String> datosModel = new Model<String>("");
	private final ValueMap properties = new ValueMap();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Login() {

		add(new FeedbackPanel("mensajes"));
		Form<Void> formulario = new Form<Void>("formulario") {

			private static final long serialVersionUID = 1L;

			protected void onSubmit() {

				String username = usernameTF.getModelObject();
				String pass = passTF.getDefaultModelObjectAsString();
				Sesion sesion = (Sesion) getSession();
				sesion.authenticate(username, pass);
				if (sesion.signedIn()) {
					mostrarDatos(sesion.getUsuario());
					info("Sesión iniciada correctamente.");
				} else
					info("El username y/o contraseña ingrsados son incorrectos.");
			}
		};

		add(formulario);

		// text field de username
		usernameTF = new TextField<String>("username", new Model<String>(),	String.class);
		usernameTF.setRequired(true);
		formulario.add(usernameTF);

		// textfield de contraseña
		passTF = new PasswordTextField("pass", new PropertyModel<String>(properties, "password"));
		passTF.setRequired(true);
		formulario.add(passTF);

		// label de datos
		datosL = new Label("datos", datosModel);
		datosL.setVisible(false);
		datosL.setEscapeModelStrings(false);
		formulario.add(datosL);

		// link registrarse

		formulario.add(new BookmarkablePageLink("registrar",RegistrarUsuario.class));

	};

	protected void mostrarDatos(Usuario usuario) {
		datosModel.setObject("<b>DATOS</b><br><br><b>Nombre:</b> "
				+ usuario.getNombre() + "<br>" + "<b>Email:</b> "
				+ usuario.getEmail() + "<br>");
		datosL.setVisible(true);
	}

}
