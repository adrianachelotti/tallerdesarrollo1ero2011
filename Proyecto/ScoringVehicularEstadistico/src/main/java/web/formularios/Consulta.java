package web.formularios;




import org.apache.wicket.markup.html.form.Form;

import web.Formulario;
import web.Menu;

public class Consulta extends Formulario{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Consulta(final Menu menu){
        super(menu);
        
        Form<Void> formulario = new Form<Void>("formularioConsulta") {

			private static final long serialVersionUID = 1L;

			protected void onSubmit() {

				menu.cambiarFormulario(new ResultadoConsulta(menu));

			}
		};
		this.getContenido().add(formulario);
}


}