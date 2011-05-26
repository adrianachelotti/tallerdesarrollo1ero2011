package web.formularios;


import org.apache.wicket.markup.html.link.Link;

import web.Formulario;
import web.Menu;

public class ResultadoConsulta extends Formulario {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	public ResultadoConsulta(final Menu menu){
		super(menu);
		this.getContenido().add(new Link("link1") {

            
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick() {
                    
            	menu.cambiarFormulario(new Informe(menu));
                    
                                    }
		});
		this.getContenido().add(new Link("link2") {

            
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick() {
                    
            	menu.cambiarFormulario(new Informe(menu));
                    
                                    }
		});
		this.getContenido().add(new Link("link3") {

            
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick() {
                    
            	menu.cambiarFormulario(new Informe(menu));
                    
                                    }
		});
		this.getContenido().add(new Link("link4") {

            
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick() {
                    
            	menu.cambiarFormulario(new Informe(menu));
                    
                                    }
		});
		
		this.getContenido().add(new Link("link5") {

            
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick() {
                    
            	menu.cambiarFormulario(new Informe(menu));
                    
                                    }
		});
		
		this.getContenido().add(new Link("link6") {

            
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick() {
                    
            	menu.cambiarFormulario(new Informe(menu));
                    
                                    }
		});
	


		
	}
}
