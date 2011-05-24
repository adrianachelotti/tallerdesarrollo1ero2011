package hibernate.domain.sistemaJudicial;



import hibernate.domain.conductores.Conductor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import javax.persistence.Id;
import javax.persistence.Table;





/**
 * Modela un expediente judicial.
 * @author grupo 2
 *
 */

@Entity
@Table(name="expedientesjudiciales")
public class ExpedienteJudicial {

	Integer numero;
	String situacion;
	String objeto;
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
    @Column (name="expedienteJudicial_id")
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

		
}
