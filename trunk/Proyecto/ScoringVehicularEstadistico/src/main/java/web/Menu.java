package web;

import org.apache.wicket.markup.html.WebMarkupContainer;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;

import web.formularios.FormularioDefault;


import hibernate.domain.usuarios.Cliente;
import hibernate.domain.usuarios.Usuario;

public class Menu extends AuthenticatedWebPage{
	
	private Usuario usuario;
	Formulario formularioActual;
	FeedbackPanel mensajes;
	WebMarkupContainer navigation ;
	WebMarkupContainer location ;
	WebMarkupContainer containerMensajes ;
	Label seccion;
	
	String textoCuenta;
	Label lCuenta;
	

	
	@SuppressWarnings("rawtypes")
	public Menu(Usuario usuario){
	
		this.usuario=usuario;
		
		if(usuario.esCliente()) 
			this.navigation=new NavigationCliente(this);
		else if(usuario.esAdministrador())
			this.navigation=new NavigationAdministrador(this);
		else
			this.navigation=new NavigationOperador(this);
					
		add(navigation);
		
		this.formularioActual = new FormularioDefault(this,"Default");
		add(this.formularioActual);
		
		
		this.actualizarTexto();
		
		lCuenta=new Label("infoCuenta",new PropertyModel(this, "textoCuenta"));
		lCuenta.setEscapeModelStrings(false);
		add(lCuenta);
		
		
		
		

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

	public void cambiarCantConsultas() {
		actualizarTexto();
		
	}

	public void actualizarTexto() {
		
		textoCuenta="Bienvenido <b>"+usuario.getNombre()+"</b> !";
		if(usuario.esOperador()) textoCuenta+="<br> Usted ha ingresado como <b>Operador</b>";
		if(usuario.esAdministrador()) textoCuenta+="<br> Usted ha ingresado como <b>Administrador</b>";
		if(!usuario.esOperador()) textoCuenta+="<br><b>Total de consultas realizadas:</b> "+usuario.getCantidadConsultas();
		if(usuario.esCliente()) {
			
			Cliente cliente=(Cliente) usuario;
			textoCuenta+="<br> <b>Servicio contratado:</b> "+(cliente.getServicio()!=null?cliente.getServicio().getDescripcion():"-");
			if(cliente.getServicio()!=null){
			
				if(cliente.getServicio().getCodigo().compareTo("CP")!=0)
				textoCuenta+="<br> <b>Consultas disponibles:</b> "+(cliente.getServicio().getMaximoConsultas()-cliente.getCantidadConsultas());
			}
			
		}
		
		
		
		
	}



}
