package web.utils;
import hibernate.domain.conductores.Conductor;
import hibernate.domain.sistemaFinanciero.DeudaSistemaFinanciero;
import hibernate.domain.sistemaJudicial.ExpedienteJudicial;
import hibernate.domain.vehiculos.Infraccion;
import hibernate.domain.vehiculos.ScoringVehicular;
import hibernate.domain.vehiculos.Siniestro;
import hibernate.domain.vehiculos.VerificacionTecnica;
import jxl.*; 
import jxl.read.biff.BiffException;

import java.io.*; 
import java.lang.reflect.Constructor;
import java.text.SimpleDateFormat;

import javax.persistence.Transient;

public class ActualizadorDeDatos {
	
	private static final String NOMBRE_ARCHIVO_CONDUCTOR = "A001-conductores";
	private static final String NOMBRE_ARCHIVO_INFRACCIONES = "A002-infracciones";
	private static final String NOMBRE_ARCHIVO_BCRA = "A003-bcra";
	private static final String NOMBRE_ARCHIVO_JUDICIAL = "A004-judicial";
	private static final String NOMBRE_ARCHIVO_SINIESTRO = "A005-siniestro";
	private static final String NOMBRE_ARCHIVO_VTV = "A006-vtv";
	private static final String NOMBRE_ARCHIVO_SCORING_VEHICULAR = "A007-scoring_vehicular";

	public ActualizadorDeDatos(){
		
	}
	
	public boolean actualizar(String nombreArchivo){
		@SuppressWarnings("rawtypes")
		String metodoCargadorDeDatos = "cargarDatos";
		String metodoEjecutor ="persistir";
		Class clase = null;
		Object obj = null;
		if (nombreArchivo.contains(NOMBRE_ARCHIVO_CONDUCTOR)){
			clase = Conductor.class;
			
		}else if (nombreArchivo.contains(NOMBRE_ARCHIVO_INFRACCIONES)){
			clase = Infraccion.class;
		}else if(nombreArchivo.contains(NOMBRE_ARCHIVO_VTV)){
			clase = VerificacionTecnica.class;
		}else if(nombreArchivo.contains(NOMBRE_ARCHIVO_SCORING_VEHICULAR)){
			clase = ScoringVehicular.class;
			metodoCargadorDeDatos = "actualizarDatos";
			metodoEjecutor="actualizar";
		}else if (nombreArchivo.contains(NOMBRE_ARCHIVO_BCRA)){
			clase = DeudaSistemaFinanciero.class;
		}else if (nombreArchivo.contains(NOMBRE_ARCHIVO_JUDICIAL)){
			clase = ExpedienteJudicial.class;
		}else if (nombreArchivo.contains(NOMBRE_ARCHIVO_SINIESTRO)){
			clase = Siniestro.class;
		}
		
		Workbook archivoExcel;
		try {
			archivoExcel = Workbook.getWorkbook(new File (nombreArchivo));
			Sheet hoja = archivoExcel.getSheet(0); 
			int numFilas = hoja.getRows();
			int numCols = hoja.getColumns();
			String data; 
			String[] datosActuales = new String[numCols];
			for (int fila = 1; fila < numFilas; fila++) {  
				Cell[] filaActual = hoja.getRow(fila);
				
				for (int j = 0; j < numCols; j++) {
					datosActuales[j]= filaActual[j].getContents();
				}
				
				try {
					obj = clase.newInstance();
					clase.getMethod(metodoCargadorDeDatos,String[].class).invoke(obj,(Object)datosActuales);
					clase.getMethod(metodoEjecutor,null).invoke(obj,null);
	
					
				} catch (Exception e) {
					
					e.printStackTrace();
				} 
			} 
		 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return true;
		
	}
	
	

	
	
	public static void main(String arg[]) { 
		ActualizadorDeDatos excel = new ActualizadorDeDatos();
//		String nombreArchivo = "C:\\Users\\Dario\\Desktop\\A001-conductores_DDJJ.xls";
//		if (nombreArchivo.contains(NOMBRE_ARCHIVO_CONDUCTOR)){
//			System.out.println("ENTRE");
//		}else{
//			System.out.println("no entro");
//		}
		excel.actualizar("C:\\Users\\Dario\\Desktop\\datos_DDJJ.xls"); 
	} 
}