package web.formularios;





import org.apache.wicket.feedback.FeedbackMessage;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;

import web.Formulario;
import web.Menu;
import web.utils.ErrorLevelsFeedbackMessageFilter;
import web.utils.FeedbackLabel;


public class Contactenos extends Formulario{

	/**
	 * 
	 */
	
	private FeedbackPanel panel;
	private static final long serialVersionUID = 1L;
	private TextField<String> asuntoTF;
	@SuppressWarnings("rawtypes")
	private TextArea consultaTA;
	protected Form<Void> formulario;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Contactenos(Menu menu){
		
		

		super(menu);
			
			formulario = new Form<Void>("formularioContactenos") {

				private static final long serialVersionUID = 1L;

				protected void onSubmit() {
					
					
					//TODO: procesar mail
				
					info("Gracias por enviarnos su consulta. Nos comunicaremos con usted a la brevedad.");
					this.setVisible(false);
					
					
					

				}

				
			};

			this.getContenido().add(formulario);
			panel=new FeedbackPanel("mensajes");
			this.getContenido().add(panel);
			

			
	        // filteredErrorLevels will not be shown in the FeedbackPanel
	        int[] filteredErrorLevels = new int[]{FeedbackMessage.ERROR};
	        panel.setFilter(new ErrorLevelsFeedbackMessageFilter(filteredErrorLevels));
	        
	        
			
			
			// text field de apellido
			asuntoTF = new TextField<String>("Asunto", new Model<String>(),	String.class);
			asuntoTF.setRequired(true);
			formulario.add(asuntoTF);
			formulario.add(new FeedbackLabel("asuntoF", asuntoTF));
			
			consultaTA=new TextArea("Consulta",new Model<String>());
			consultaTA.setRequired(true);
			formulario.add(consultaTA);
			formulario.add(new FeedbackLabel("consultaF", consultaTA));
			
			
			
		}
		
	
}
