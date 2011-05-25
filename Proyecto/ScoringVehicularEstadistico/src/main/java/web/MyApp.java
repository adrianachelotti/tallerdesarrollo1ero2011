package web;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.Request;
import org.apache.wicket.Response;
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.Session;
import org.apache.wicket.authorization.Action;
import org.apache.wicket.authorization.IAuthorizationStrategy;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WebRequestCycleProcessor;
import org.apache.wicket.protocol.http.request.CryptedUrlWebRequestCodingStrategy;
import org.apache.wicket.protocol.http.request.WebRequestCodingStrategy;
import org.apache.wicket.request.IRequestCodingStrategy;
import org.apache.wicket.request.IRequestCycleProcessor;

public class MyApp extends WebApplication {

	@Override
	public Class<? extends Page> getHomePage() {
		return Login2.class;
	}

	@Override
	public Session newSession(Request request, Response response) {
		return new Sesion(request);
	}

	@Override
	protected void init() {
		super.init();
		getSecuritySettings().setAuthorizationStrategy(
				new IAuthorizationStrategy() {
					public boolean isActionAuthorized(Component component,Action action) {
						return true;
					}

					public <T extends Component> boolean isInstantiationAuthorized(Class<T> componentClass) {
						if (AuthenticatedWebPage.class.isAssignableFrom(componentClass)) {
							
							if (((Sesion) Session.get()).signedIn()) {
							
								return true;
							}

							
							throw new RestartResponseAtInterceptPageException(Login.class);
						}
						return true;
					}
				});
	}

	@Override
	protected IRequestCycleProcessor newRequestCycleProcessor() {
		return new WebRequestCycleProcessor() {
			@Override
			protected IRequestCodingStrategy newRequestCodingStrategy() {
				return new CryptedUrlWebRequestCodingStrategy(
						new WebRequestCodingStrategy());
			}
		};
	}

}
