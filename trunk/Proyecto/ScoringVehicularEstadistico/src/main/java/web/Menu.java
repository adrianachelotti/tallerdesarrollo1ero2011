package web;

import org.apache.wicket.markup.html.WebMarkupContainer;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

import web.formularios.FormularioDefault;


import hibernate.domain.usuarios.Usuario;

public class Menu extends AuthenticatedWebPage{
	
	private Usuario usuario;
	Formulario formularioActual;
	FeedbackPanel mensajes;
	WebMarkupContainer navigation ;
	WebMarkupContainer location ;
	WebMarkupContainer containerMensajes ;
	Label seccion;
	

	
	public Menu(Usuario usuario){
	
		this.usuario=usuario;
		if(usuario.esCliente()) 
			this.navigation=new NavigationCliente(this);
		else if(usuario.esAdministrador())
			this.navigation=new NavigationAdministrador(this);
		else
			this.navigation=new NavigationAdministrador(this);
					
		add(navigation);
		
		this.formularioActual = new FormularioDefault(this,"Default");
		add(this.formularioActual);
		
		
		
		

	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	public void cambiarFormulario(Formulario nuevo) {
		this.remove("formulario");
		this.formularioActual = nuevo;
		this.add(this.formularioActual);
	}
	


	public void mostrarMensajes(boolean mostrar) {
		this.mensajes.setVisible(mostrar);
		this.containerMensajes.setVisible(mostrar);

	}



}
