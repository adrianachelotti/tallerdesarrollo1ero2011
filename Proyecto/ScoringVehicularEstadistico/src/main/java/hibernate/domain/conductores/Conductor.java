package hibernate.domain.conductores;

import hibernate.domain.sistemaFinanciero.DeudaSistemaFinanciero;
import hibernate.domain.sistemaJudicial.ExpedienteJudicial;
import hibernate.domain.vehiculos.Infraccion;
import hibernate.domain.vehiculos.Vehiculo;

import java.util.Date;

import java.util.Set;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Cascade;



/**
 * Modela a un conductor.
 * @author grupo 2
 *
 */

@Entity
@Table(name="conductores")
public class Conductor {

	private Integer id;
	private String tipoDocumento;
	private Integer numeroDocumento;
	private String sexo;
	private Date fechaNacimiento;
	private String lugarResidencia;
	private String estadoCivil;
	private String profesion;
	private String nivelDeEstudio;
	private Float ingresoPromedio;
	private Integer cantidadHijos;
	private String nombre;
	private String apellidos;
	private Integer saldoScoring;
	private Integer cantidadCerosEnScoring;
	private Integer edad;
	private Set<Infraccion> infracciones;
	private Set<DeudaSistemaFinanciero> deudasSistemaFinanciero;
	private Set<ExpedienteJudicial> expedientesJudiciales;
	private Set<Vehiculo> vehiculos;
	


    @OneToMany (cascade = CascadeType.ALL,fetch= FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    @JoinTable(
        name="conductor_expedientes_judiciales",
        joinColumns = @JoinColumn( name="conductor_id"),
        inverseJoinColumns = @JoinColumn( name="expedienteJudicial_id"))
	public Set<ExpedienteJudicial> getExpedientesJudiciales() {
		return expedientesJudiciales;
	}
	public void setExpedientesJudiciales(
			Set<ExpedienteJudicial> expedientesJudiciales) {
		this.expedientesJudiciales = expedientesJudiciales;
	}
	
	
	
    @OneToMany (cascade = CascadeType.ALL,fetch= FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    @JoinTable(
        name="conductor_infracciones",
        joinColumns = @JoinColumn( name="conductor_id"),
        inverseJoinColumns = @JoinColumn( name="infraccion_id"))
	public Set<Infraccion> getInfracciones() {
		return infracciones;
	}
	public void setInfracciones(Set<Infraccion> infracciones) {
		this.infracciones = infracciones;
	}
	
	
    @OneToMany (cascade = CascadeType.ALL,fetch= FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    @JoinTable(
        name="conductor_deuda_sistema_financiero",
        joinColumns = @JoinColumn( name="conductor_id"),
        inverseJoinColumns = @JoinColumn( name="deudaSistemaFinanciero_id"))
	public Set<DeudaSistemaFinanciero> getDeudasSistemaFinanciero() {
		return deudasSistemaFinanciero;
	}
	public void setDeudasSistemaFinanciero(
			Set<DeudaSistemaFinanciero> deudasSistemaFinanciero) {
		this.deudasSistemaFinanciero = deudasSistemaFinanciero;
	}
	
    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    @Column (name="conductor_id")
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
	public void setVehiculos(Set<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}
	
    @OneToMany (cascade = CascadeType.ALL,fetch= FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    @JoinTable(
        name="conductor_vehiculo",
        joinColumns = @JoinColumn( name="conductor_id"),
        inverseJoinColumns = @JoinColumn( name="vehiculo_id"))
	public Set<Vehiculo> getVehiculos() {
		return vehiculos;
	}
	
}