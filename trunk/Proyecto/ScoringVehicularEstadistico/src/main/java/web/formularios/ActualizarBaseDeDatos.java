package web.formularios;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.util.file.File;
import org.apache.wicket.util.lang.Bytes;


import web.Formulario;
import web.Menu;
import web.utils.ActualizadorDeDatos;



public class ActualizarBaseDeDatos extends Formulario {
	private FileUploadField fileUpload;
	
	FeedbackPanel panel;
	protected Form<Void> formulario;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("serial")
	public ActualizarBaseDeDatos(Menu menu){
		super(menu);
//		add(new FeedbackPanel("feedback"));
		 
		formulario = new Form<Void>("formularioUpload") {
		 @Override
		 
		 protected void onSubmit() {
 
			final org.apache.wicket.markup.html.form.upload.FileUpload uploadedFile = fileUpload.getFileUpload();
			if (uploadedFile != null) {
 
				// write to a new file
				File newFile = new File(uploadedFile.getClientFileName());	
				
				try {
					ActualizadorDeDatos actualizador = new ActualizadorDeDatos();
					 if ( newFile.exists() )actualizador.actualizar(uploadedFile.getClientFileName());
					    
						
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					info("Error al actualizar la base de dato. Dirigase a un administrador");
					return;
					
				}
			   
				info("Los datos han sido actualizados");
				return;
				

			 }
		 }
			
 
		};
		this.getContenido().add(formulario);
		panel=new FeedbackPanel("mensajes");
		this.getContenido().add(panel);
		// Enable multipart mode (need for uploads file)
		formulario.setMultiPart(true);
		
		// max upload size, 10k
		formulario.setMaxSize(Bytes.kilobytes(10000));
 
		formulario.add(fileUpload = new FileUploadField("fileUpload"));
 
 
	}
}
