package domain.conductores;

import java.util.Date;
import java.util.List;

import domain.sistemaFinanciero.DeudaSistemaFinanciero;
import domain.sistemaJudicial.ExpedienteJudicial;
import domain.vehiculos.Infraccion;

/**
 * Modela a un conductor.
 * @author grupo 2
 *
 */
public class Conductor {

	Integer id;
	String tipoDocumento;
	Integer numeroDocumento;
	String sexo;
	Date fechaNacimiento;
	String lugarResidencia;
	String estadoCivil;
	String profesion;
	String nivelDeEstudio;
	Float ingresoPromedio;
	Integer cantidadHijos;
	String nombre;
	String apellidos;
	Integer saldoScoring;
	Integer cantidadCerosEnScoring;
	Integer edad;
	List<Infraccion> infracciones;
	List<DeudaSistemaFinanciero> deudasSistemaFinanciero;
	List<ExpedienteJudicial> expedientesJudiciales;
	
	
	public List<ExpedienteJudicial> getExpedientesJudiciales() {
		return expedientesJudiciales;
	}
	public void setExpedientesJudiciales(
			List<ExpedienteJudicial> expedientesJudiciales) {
		this.expedientesJudiciales = expedientesJudiciales;
	}
	public List<Infraccion> getInfracciones() {
		return infracciones;
	}
	public void setInfracciones(List<Infraccion> infracciones) {
		this.infracciones = infracciones;
	}
	public List<DeudaSistemaFinanciero> getDeudasSistemaFinanciero() {
		return deudasSistemaFinanciero;
	}
	public void setDeudasSistemaFinanciero(
			List<DeudaSistemaFinanciero> deudasSistemaFinanciero) {
		this.deudasSistemaFinanciero = deudasSistemaFinanciero;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public Integer getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(Integer numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getLugarResidencia() {
		return lugarResidencia;
	}
	public void setLugarResidencia(String lugarResidencia) {
		this.lugarResidencia = lugarResidencia;
	}
	public String getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	public String getProfesion() {
		return profesion;
	}
	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}
	public String getNivelDeEstudio() {
		return nivelDeEstudio;
	}
	public void setNivelDeEstudio(String nivelDeEstudio) {
		this.nivelDeEstudio = nivelDeEstudio;
	}
	public Float getIngresoPromedio() {
		return ingresoPromedio;
	}
	public void setIngresoPromedio(Float ingresoPromedio) {
		this.ingresoPromedio = ingresoPromedio;
	}
	public Integer getCantidadHijos() {
		return cantidadHijos;
	}
	public void setCantidadHijos(Integer cantidadHijos) {
		this.cantidadHijos = cantidadHijos;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public Integer getSaldoScoring() {
		return saldoScoring;
	}
	public void setSaldoScoring(Integer saldoScoring) {
		this.saldoScoring = saldoScoring;
	}
	public Integer getCantidadCerosEnScoring() {
		return cantidadCerosEnScoring;
	}
	public void setCantidadCerosEnScoring(Integer cantidadCerosEnScoring) {
		this.cantidadCerosEnScoring = cantidadCerosEnScoring;
	}
	public Integer getEdad() {
		return edad;
	}
	public void setEdad(Integer edad) {
		this.edad = edad;
	}
	
}
