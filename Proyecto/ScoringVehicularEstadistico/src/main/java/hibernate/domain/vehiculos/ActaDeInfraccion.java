package hibernate.domain.vehiculos;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;



/**
 * Modela un acta de infraccion
 * @author grupo 2
 *
 */
@Entity
@Table(name="actasdeinfraccion")
public class ActaDeInfraccion {

	private String tipo;
	private Integer numero;
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
    @Id
    @Column (name="actaDeInfraccion_id")
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
		
}
