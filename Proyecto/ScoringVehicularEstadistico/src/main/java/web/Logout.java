package web;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;


public class Logout extends WebPage {
	



	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Logout() {
        getRequestCycle().setRedirect(false);
		getSession().invalidate();
		add(new FeedbackPanel("mensajes"));
		info("Sesion finalizada correctamente.");
		add(new BookmarkablePageLink("nuevasesion",Login2.class));
		

	};

	

}
