package vehiculos.domain;

import java.util.Date;

/**
 * Modela una infraccion de transito.
 * @author grupo 2
 *
 */
public class Infraccion {

	Integer numero;
	String descripcion;
	Date fecha;
	String lugar;
	ActaDeInfraccion acta;
	String estadoUACF;
	String patente;
	Integer idConductor;

	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getLugar() {
		return lugar;
	}
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	public ActaDeInfraccion getActa() {
		return acta;
	}
	public void setActa(ActaDeInfraccion acta) {
		this.acta = acta;
	}
	public String getEstadoUACF() {
		return estadoUACF;
	}
	public void setEstadoUACF(String estadoUACF) {
		this.estadoUACF = estadoUACF;
	}
	public String getPatente() {
		return patente;
	}
	public void setPatente(String patente) {
		this.patente = patente;
	}
	public Integer getIdConductor() {
		return idConductor;
	}
	public void setIdConductor(Integer idConductor) {
		this.idConductor = idConductor;
	}
	
}
