package domain.vehiculos;

import java.util.List;

/**
 * Modela un vehiculo.
 * @author grupo 2
 *
 */
public class Vehiculo {

	String patente;
	Integer modelo;
	String estadoInterior;
	String estadoExterior;
	String estadoMecanica;
	List<VerificacionTecnica> verificacionesTecnicas;
	List<Siniestro> siniestros;
	
	public List<Siniestro> getSiniestros() {
		return siniestros;
	}
	public void setSiniestros(List<Siniestro> siniestros) {
		this.siniestros = siniestros;
	}
	public List<VerificacionTecnica> getVerificacionesTecnicas() {
		return verificacionesTecnicas;
	}
	public void setVerificacionesTecnicas(
			List<VerificacionTecnica> verificacionesTecnicas) {
		this.verificacionesTecnicas = verificacionesTecnicas;
	}
	public String getPatente() {
		return patente;
	}
	public void setPatente(String patente) {
		this.patente = patente;
	}
	public Integer getModelo() {
		return modelo;
	}
	public void setModelo(Integer modelo) {
		this.modelo = modelo;
	}
	public String getEstadoInterior() {
		return estadoInterior;
	}
	public void setEstadoInterior(String estadoInterior) {
		this.estadoInterior = estadoInterior;
	}
	public String getEstadoExterior() {
		return estadoExterior;
	}
	public void setEstadoExterior(String estadoExterior) {
		this.estadoExterior = estadoExterior;
	}
	public String getEstadoMecanica() {
		return estadoMecanica;
	}
	public void setEstadoMecanica(String estadoMecanica) {
		this.estadoMecanica = estadoMecanica;
	}
	
	
}
