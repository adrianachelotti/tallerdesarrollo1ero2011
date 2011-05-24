package web;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;


@SuppressWarnings("serial")
public abstract class Formulario extends Panel {

        protected Menu menu;
        private WebMarkupContainer contenido;
        
        
        public Formulario(Menu menu, String nombre){
                super("formulario");
                this.menu = menu;
                WebMarkupContainer header=new WebMarkupContainer("header");
                header.add(new Label("nombre",nombre));
                this.add(header);
                contenido=new WebMarkupContainer("contenido");
                this.add(contenido);
        }


        
        public Menu getMenu() {
                return menu;
        }

        public void setMenu(Menu menu) {
                this.menu = menu;
        }       
        
        public WebMarkupContainer getContenido(){
        
        	return contenido;
        }
}
