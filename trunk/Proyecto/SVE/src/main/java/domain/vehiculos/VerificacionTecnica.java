package domain.vehiculos;

import java.util.Date;

/**
 * Modela una verificacion tecnica.
 * @author grupo 2
 *
 */
public class VerificacionTecnica {

	Integer numeroOblea;
	String zona;
	String estacion;
	String patente;
	Date fechaInspeccion;
	String tipoVerificacion;
	Date fechaVencimiento;
	String resultado;
	
	public Integer getNumeroOblea() {
		return numeroOblea;
	}
	public void setNumeroOblea(Integer numeroOblea) {
		this.numeroOblea = numeroOblea;
	}
	public String getZona() {
		return zona;
	}
	public void setZona(String zona) {
		this.zona = zona;
	}
	public String getEstacion() {
		return estacion;
	}
	public void setEstacion(String estacion) {
		this.estacion = estacion;
	}
	public String getPatente() {
		return patente;
	}
	public void setPatente(String patente) {
		this.patente = patente;
	}
	public Date getFechaInspeccion() {
		return fechaInspeccion;
	}
	public void setFechaInspeccion(Date fechaInspeccion) {
		this.fechaInspeccion = fechaInspeccion;
	}
	public String getTipoVerificacion() {
		return tipoVerificacion;
	}
	public void setTipoVerificacion(String tipoVerificacion) {
		this.tipoVerificacion = tipoVerificacion;
	}
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
		
}
