package hibernate.domain.conductores;

import hibernate.AdministradorConductores;
import hibernate.domain.sistemaFinanciero.DeudaSistemaFinanciero;
import hibernate.domain.sistemaJudicial.ExpedienteJudicial;
import hibernate.domain.vehiculos.Infraccion;
import hibernate.domain.vehiculos.Vehiculo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;

import java.util.Set;

import javax.persistence.*;

import jxl.Cell;

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
	private Set<DeudaSistemaFinanciero> deudasSistemaFinanciero=new HashSet<DeudaSistemaFinanciero>();
	private Set<ExpedienteJudicial> expedientesJudiciales=new HashSet<ExpedienteJudicial>();
	private Set<Vehiculo> vehiculos=new HashSet<Vehiculo>();
	private Integer cantidadConsultas;
	

	

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
	
    @ManyToMany (cascade = {CascadeType.ALL}, fetch= FetchType.EAGER)
    @JoinTable(
        name="conductor_vehiculo",
        joinColumns = @JoinColumn( name="conductor_id"),
        inverseJoinColumns = @JoinColumn( name="vehiculo_id"))
	public Set<Vehiculo> getVehiculos() {
		return vehiculos;
	}
	public void agregarVehiculo(Vehiculo v1) {
		vehiculos.add(v1);
		
	}
	public boolean conduce(String patente) {
		Iterator<Vehiculo> itVehiculos=vehiculos.iterator();
		boolean encontrado=false;
		while((!encontrado)&&(itVehiculos.hasNext())){
			if(itVehiculos.next().getPatente().compareTo(patente)==0) encontrado=true;
		}
		
		return encontrado;
	}
	public void setCantidadConsultas(Integer cantidadConsultas) {
		this.cantidadConsultas = cantidadConsultas;
	}
	public Integer getCantidadConsultas() {
		return cantidadConsultas;
	}
	@Transient
	public void cargarDatos(String[] celdas){
		this.setTipoDocumento(celdas[0]);
		this.setNumeroDocumento(Integer.parseInt(celdas[1]));
		this.setNombre(celdas[2]);
		this.setApellidos(celdas[3]);
		this.setSexo(celdas[4]);
		SimpleDateFormat fecha =  new SimpleDateFormat("dd/mm/yyyy");
		try {
			this.setFechaNacimiento(fecha.parse(celdas[5]));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setLugarResidencia(celdas[6]);
		this.setEstadoCivil(celdas[7]);
		this.setProfesion(celdas[8]);
		this.setNivelDeEstudio(celdas[9]);
		this.setIngresoPromedio(Float.parseFloat(celdas[10]));
		this.setCantidadHijos(Integer.parseInt(celdas[11]));
		
	}
	@Transient
	public void persistir(){
		AdministradorConductores.agregarConductor(this);
	}
	
	
}
