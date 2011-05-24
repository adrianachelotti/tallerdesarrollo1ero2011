package hibernate.domain.vehiculos;

import hibernate.domain.conductores.Conductor;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;



/**
 * Modela una infraccion de transito.
 * @author grupo 2
 *
 */

@Entity
@Table(name="infracciones")
public class Infraccion {

	private Integer numero;
	private String descripcion;
	private Date fecha;
	private String lugar;
	private ActaDeInfraccion acta;
	private String estadoUACF;
	private String patente;
	
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
	
	
    @Id
    @Column (name="infraccion_id")
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
	
	@OneToOne
	public ActaDeInfraccion getActaDeInfraccion() {
		return acta;
	}
	public void setActaDeInfraccion(ActaDeInfraccion acta) {
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

	
}
