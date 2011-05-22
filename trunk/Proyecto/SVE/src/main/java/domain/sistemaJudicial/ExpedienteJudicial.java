package domain.sistemaJudicial;

import domain.conductores.Conductor;

/**
 * Modela un expediente judicial.
 * @author grupo 2
 *
 */
public class ExpedienteJudicial {

	Integer numero;
	String situacion;
	String objeto;
	Conductor conductor;
	
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public String getSituacion() {
		return situacion;
	}
	public void setSituacion(String situacion) {
		this.situacion = situacion;
	}
	public String getObjeto() {
		return objeto;
	}
	public void setObjeto(String objeto) {
		this.objeto = objeto;
	}
	public Conductor getConductor() {
		return conductor;
	}
	public void setConductor(Conductor conductor) {
		this.conductor = conductor;
	}
		
}
