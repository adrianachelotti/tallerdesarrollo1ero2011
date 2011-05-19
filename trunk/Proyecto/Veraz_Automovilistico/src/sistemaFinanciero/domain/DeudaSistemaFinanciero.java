package sistemaFinanciero.domain;

import conductores.domain.Conductor;

/**
 * Modela una deuda con el sistema financiero
 * @author grupo 2
 *
 */
public class DeudaSistemaFinanciero {

	String tipoClaveUnica;
	String claveUnica;
	Boolean inhabilitacionJudicial;
	Boolean inhabilitacionMulta;
	Integer cantidadChequesRechazados;
	Integer cantidadPrestamos;
	Integer cantidadPrestamosCalificacionMayor;
	Float montoDeudaNormal;
	Float montoDeudaEspecial;
	Float montoDeudaEnProblemas;
	Float montoDeudaInsolvente;
	Float montoDeudaIrrecuperable;
	Float promedioDiasAtraso;
	Integer id;
	Conductor conductor;
	
	public Conductor getIdConductor() {
		return conductor;
	}
	public void setIdConductor(Conductor conductor) {
		this.conductor = conductor;
	}
	public String getTipoClaveUnica() {
		return tipoClaveUnica;
	}
	public void setTipoClaveUnica(String tipoClaveUnica) {
		this.tipoClaveUnica = tipoClaveUnica;
	}
	public String getClaveUnica() {
		return claveUnica;
	}
	public void setClaveUnica(String claveUnica) {
		this.claveUnica = claveUnica;
	}
	public Boolean getInhabilitacionJudicial() {
		return inhabilitacionJudicial;
	}
	public void setInhabilitacionJudicial(Boolean inhabilitacionJudicial) {
		this.inhabilitacionJudicial = inhabilitacionJudicial;
	}
	public Boolean getInhabilitacionMulta() {
		return inhabilitacionMulta;
	}
	public void setInhabilitacionMulta(Boolean inhabilitacionMulta) {
		this.inhabilitacionMulta = inhabilitacionMulta;
	}
	public Integer getCantidadChequesRechazados() {
		return cantidadChequesRechazados;
	}
	public void setCantidadChequesRechazados(Integer cantidadChequesRechazados) {
		this.cantidadChequesRechazados = cantidadChequesRechazados;
	}
	public Integer getCantidadPrestamos() {
		return cantidadPrestamos;
	}
	public void setCantidadPrestamos(Integer cantidadPrestamos) {
		this.cantidadPrestamos = cantidadPrestamos;
	}
	public Integer getCantidadPrestamosCalificacionMayor() {
		return cantidadPrestamosCalificacionMayor;
	}
	public void setCantidadPrestamosCalificacionMayor(
			Integer cantidadPrestamosCalificacionMayor) {
		this.cantidadPrestamosCalificacionMayor = cantidadPrestamosCalificacionMayor;
	}
	public Float getMontoDeudaNormal() {
		return montoDeudaNormal;
	}
	public void setMontoDeudaNormal(Float montoDeudaNormal) {
		this.montoDeudaNormal = montoDeudaNormal;
	}
	public Float getMontoDeudaEspecial() {
		return montoDeudaEspecial;
	}
	public void setMontoDeudaEspecial(Float montoDeudaEspecial) {
		this.montoDeudaEspecial = montoDeudaEspecial;
	}
	public Float getMontoDeudaEnProblemas() {
		return montoDeudaEnProblemas;
	}
	public void setMontoDeudaEnProblemas(Float montoDeudaEnProblemas) {
		this.montoDeudaEnProblemas = montoDeudaEnProblemas;
	}
	public Float getMontoDeudaInsolvente() {
		return montoDeudaInsolvente;
	}
	public void setMontoDeudaInsolvente(Float montoDeudaInsolvente) {
		this.montoDeudaInsolvente = montoDeudaInsolvente;
	}
	public Float getMontoDeudaIrrecuperable() {
		return montoDeudaIrrecuperable;
	}
	public void setMontoDeudaIrrecuperable(Float montoDeudaIrrecuperable) {
		this.montoDeudaIrrecuperable = montoDeudaIrrecuperable;
	}
	public Float getPromedioDiasAtraso() {
		return promedioDiasAtraso;
	}
	public void setPromedioDiasAtraso(Float promedioDiasAtraso) {
		this.promedioDiasAtraso = promedioDiasAtraso;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
		
}
