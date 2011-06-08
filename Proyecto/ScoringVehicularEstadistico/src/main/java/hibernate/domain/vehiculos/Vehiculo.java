package hibernate.domain.vehiculos;

import hibernate.domain.conductores.Conductor;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;


/**
 * Modela un vehiculo.
 * @author grupo 2
 *
 */
@Entity
@Table(name="vehiculos")
public class Vehiculo {

	private String patente;
	private String modelo;
	private String marca;
	private String estadoInterior;
	private String estadoExterior;
	private String estadoMecanica;
	private Set<VerificacionTecnica> verificacionesTecnicas;
	private Set<Siniestro> siniestros;
	private Set<Conductor> conductores;
	

	

	
	
    @OneToMany (cascade = CascadeType.ALL,fetch= FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    @JoinTable(
        name="vehiculo_siniestros",
        joinColumns = @JoinColumn( name="vehiculo_id"),
        inverseJoinColumns = @JoinColumn( name="siniestro_id"))
	public Set<Siniestro> getSiniestros() {
		return siniestros;
	}
	public void setSiniestros(Set<Siniestro> siniestros) {
		this.siniestros = siniestros;
	}
	
	@OneToMany (cascade = CascadeType.ALL,fetch= FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    @JoinTable(
        name="vehiculo_siniestros",
        joinColumns = @JoinColumn( name="vehiculo_id"),
        inverseJoinColumns = @JoinColumn( name="verificacionesTecnicas_id"))
	public Set<VerificacionTecnica> getVerificacionesTecnicas() {
		return verificacionesTecnicas;
	}
	public void setVerificacionesTecnicas(
			Set<VerificacionTecnica> verificacionesTecnicas) {
		this.verificacionesTecnicas = verificacionesTecnicas;
	}
	
    @Id
    @Column (name="vehiculo_id")
	public String getPatente() {
		return patente;
	}
	public void setPatente(String patente) {
		this.patente = patente;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
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

	public void setConductores(Set<Conductor> conductores) {
		this.conductores = conductores;
	}

	
    @ManyToMany(

            cascade = { CascadeType.ALL },
            fetch = FetchType.EAGER,
            targetEntity = hibernate.domain.conductores.Conductor.class,
            mappedBy = "vehiculos"

            )
	public Set<Conductor> getConductores() {
		return conductores;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getMarca() {
		return marca;
	}
	
	
}
