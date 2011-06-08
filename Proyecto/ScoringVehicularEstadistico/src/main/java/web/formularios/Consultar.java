package web.formularios;




import java.util.Arrays;

import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import web.Formulario;
import web.Menu;
import web.utils.ErrorLevelsFeedbackMessageFilter;
import web.utils.FeedbackLabel;

import hibernate.domain.consultas.Consulta;


public class Consultar extends Formulario{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private TextField<String> nombreTF;
	private TextField<String> apellidoTF;
	private Model<String> tipoDocumentoModel;
	private DropDownChoice<String> tipoDocumentoDD;
	private TextField<Integer> documentoTF;
	private FeedbackPanel panel;
	
	
	private CheckBox expedientesJudicialesCB;
	
	private boolean expedientesJudiciales=true;
	
	private CheckBox infraccionesCB;
	private boolean infracciones=true;
	
	private CheckBox scoringCB;
	private boolean scoring=true;
	
	private CheckBox vtvCB;
	private boolean vtv=true;
	
	private CheckBox siniestrosCB;
	private boolean siniestros=true;
	
	private CheckBox situacionFinancieraCB;
	private boolean situacionFinanciera=true;

	public Consultar(final Menu menu){
        super(menu);
        
        Form<Void> formulario = new Form<Void>("formularioConsulta") {

			private static final long serialVersionUID = 1L;

			protected void onSubmit() {
				if((nombreTF.getModelObject().compareTo("")!=0)&&(apellidoTF.getModelObject().compareTo("")==0)){
					info("Se especifica un nombre, deberá especificar un apellido también.");
					return;
				}
				
				Consulta consulta=new Consulta();
				consulta.setNombre(nombreTF.getModelObject());
				consulta.setApellido(apellidoTF.getModelObject());
				consulta.setTipoDoc(tipoDocumentoModel.getObject());
				consulta.setDocumento(documentoTF.getModelObject());
				consulta.setUsuario(menu.getUsuario());
				consulta.setExpedientesJudiciales(expedientesJudiciales);
				consulta.setInfracciones(infracciones);
				consulta.setScoring(scoring);
				consulta.setVtv(vtv);
				consulta.setSiniestros(siniestros);
				consulta.setSituacionFinanciera(situacionFinanciera);
				
							

				menu.cambiarFormulario(new ResultadoConsulta(consulta,menu));

			}
		};
		
		
		this.getContenido().add(formulario);
		panel=new FeedbackPanel("mensajes");
		this.getContenido().add(panel);
		

		
        // filteredErrorLevels will not be shown in the FeedbackPanel
        int[] filteredErrorLevels = new int[]{FeedbackMessage.ERROR};
        panel.setFilter(new ErrorLevelsFeedbackMessageFilter(filteredErrorLevels));
		
		// text field de nombre
		nombreTF = new TextField<String>("Nombre", new Model<String>(),	String.class);
		formulario.add(nombreTF);
		formulario.add(new FeedbackLabel("nombreF", nombreTF));
		
		// text field de apellido
		apellidoTF = new TextField<String>("Apellido", new Model<String>(),	String.class);
		formulario.add(apellidoTF);
		formulario.add(new FeedbackLabel("apellidoF", apellidoTF));
		
		
		
		//dd tipo doc
		tipoDocumentoModel = new Model<String>("DNI");
        tipoDocumentoDD = new DropDownChoice<String>("Tipo de documento",tipoDocumentoModel, Arrays.asList(new String[] { "DNI","Libreta Cívica","Libreta de Enrolamiento","Pasaporte", "Cédula de Identidad" })) ;
        formulario.add(tipoDocumentoDD);
        
		// text field de nro doc
		documentoTF = new TextField<Integer>("Documento", new Model<Integer>(),	Integer.class);
		formulario.add(documentoTF);
		formulario.add(new FeedbackLabel("documentoF", documentoTF));
		
		
		//chs
		expedientesJudicialesCB = new CheckBox("expedientesJudiciales",new PropertyModel<Boolean>(this, "expedientesJudiciales"));
		formulario.add(expedientesJudicialesCB);
		
		infraccionesCB = new CheckBox("infracciones",new PropertyModel<Boolean>(this, "infracciones"));
		formulario.add(infraccionesCB);
		
		scoringCB = new CheckBox("scoring",new PropertyModel<Boolean>(this, "scoring"));
		formulario.add(scoringCB);
		
		vtvCB = new CheckBox("vtv",new PropertyModel<Boolean>(this, "vtv"));
		formulario.add(vtvCB);
		
		siniestrosCB = new CheckBox("siniestros",new PropertyModel<Boolean>(this, "siniestros"));
		formulario.add(siniestrosCB);
		
		situacionFinancieraCB = new CheckBox("situacionFinanciera",new PropertyModel<Boolean>(this, "situacionFinanciera"));
		formulario.add(situacionFinancieraCB);
		
}


}