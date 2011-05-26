package web.formularios;


import org.apache.wicket.markup.html.form.Form;

import org.apache.wicket.markup.html.link.Link;


import web.Formulario;
import web.Menu;

public class ConsultaHistorial extends Formulario {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	public ConsultaHistorial(final Menu menu){
		super(menu);
		 Form<Void> formulario = new Form<Void>("formularioHistorial") {

				private static final long serialVersionUID = 1L;

				protected void onSubmit() {

					

				}
			};
			this.getContenido().add(formulario);
			formulario.add(new Link("link1") {

                
                private static final long serialVersionUID = 1L;

                @Override
                public void onClick() {
                        
                        menu.cambiarFormulario(new Informe(menu));
                        
                                        }
			});
			
			formulario.add(new Link("link2") {	

                
                private static final long serialVersionUID = 1L;

                @Override
                public void onClick() {
                        
                	menu.cambiarFormulario(new Informe(menu));
                        
                                        }
			});
			
formulario.add(new Link("link3") {

                
                private static final long serialVersionUID = 1L;

                @Override
                public void onClick() {
                        
                	menu.cambiarFormulario(new Informe(menu));
                        
                                        }
			});
		
	}
}
