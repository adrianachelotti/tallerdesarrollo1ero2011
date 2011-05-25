package web;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;

public class Login2 extends WebPage {
	
	public Login2(){
		add(new BookmarkablePageLink("linkContactenos",Contactenos.class));
	}

}
