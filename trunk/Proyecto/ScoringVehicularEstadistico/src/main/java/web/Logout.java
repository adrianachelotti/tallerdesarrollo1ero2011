package web;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;


public class Logout extends AuthenticatedWebPage {



	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Logout() {

		
		add(new FeedbackPanel("mensajes"));
		info("Sesion finalizada correctamente.");
		add(new BookmarkablePageLink("nuevasesion",Login.class));
		getSession().invalidate();

	};

	

}
