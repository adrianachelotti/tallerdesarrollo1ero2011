package hibernate.domain.sistemaFinanciero;


import hibernate.AdministradorDeudas;
import hibernate.domain.conductores.Conductor;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;


/**
 * Modela una deuda con el sistema financiero
 * @author grupo 2
 *
 */

@Entity
@Table(name="deudassistemafinanciero")
public class DeudaSistemaFinanciero {

	
	private String tipoClaveUnica;
	private String claveUnica;
	private Boolean inhabilitacionJudicial;
	private Boolean inhabilitacionMulta;
	private Integer cantidadChequesRechazados;
	private Integer cantidadPrestamos;
	private Integer cantidadPrestamosCalificacionMayor;
	private Float montoDeudaNormal;
	private Float montoDeudaEspecial;
	private Float montoDeudaEnProblemas;
	private Float montoDeudaInsolvente;
	private Float montoDeudaIrrecuperable;
	private Float promedioDiasAtraso;
	private Integer id;
	private Conductor conductor;
	
	public void setConductor(Conductor conductor) {
		this.conductor = conductor;
	}
	
    @ManyToOne(

            cascade = { CascadeType.PERSIST, CascadeType.MERGE },
            fetch = FetchType.EAGER,
            targetEntity = hibernate.domain.conductores.Conductor.class

            )

	public Conductor getConductor() {
		return conductor;
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
	
	
    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    @Column (name="deudaSistemaFinanciero_id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Transient
	public void cargarDatos(String[] celdas){
		//TODO mapear los datos del bcra
		
	}
	@Transient
	public void persistir(){
		AdministradorDeudas.agregarConductor(this);
	}
		
}
