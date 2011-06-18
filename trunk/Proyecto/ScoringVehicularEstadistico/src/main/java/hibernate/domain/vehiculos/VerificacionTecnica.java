package hibernate.domain.vehiculos;

import hibernate.AdministradorConductores;
import hibernate.AdministradorInfracciones;
import hibernate.AdministradorVehiculo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Transient;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



/**
 * Modela una verificacion tecnica.
 * @author grupo 2
 *
 */

@Entity
@Table(name="verificacionestecnicas")
public class VerificacionTecnica {

	Integer numeroOblea;
	String zona;
	String estacion;
	String patente;
	Date fechaInspeccion;
	String tipoVerificacion;
	Date fechaVencimiento;
	String resultado;
	private Vehiculo vehiculo;
	
	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}
	
	@ManyToOne(

            cascade = { CascadeType.PERSIST, CascadeType.MERGE },
            fetch = FetchType.EAGER,
            targetEntity = hibernate.domain.vehiculos.Vehiculo.class

            )
	public Vehiculo getVehiculo() {
		return vehiculo;
	}
	
    @Id
    @Column (name="vehiculo_id")
	public Integer getNumeroOblea() {
		return numeroOblea;
	}
	public void setNumeroOblea(Integer numeroOblea) {
		this.numeroOblea = numeroOblea;
	}
	public String getZona() {
		return zona;
	}
	public void setZona(String zona) {
		this.zona = zona;
	}
	public String getEstacion() {
		return estacion;
	}
	public void setEstacion(String estacion) {
		this.estacion = estacion;
	}
	public String getPatente() {
		return patente;
	}
	public void setPatente(String patente) {
		this.patente = patente;
	}
	public Date getFechaInspeccion() {
		return fechaInspeccion;
	}
	public void setFechaInspeccion(Date fechaInspeccion) {
		this.fechaInspeccion = fechaInspeccion;
	}
	public String getTipoVerificacion() {
		return tipoVerificacion;
	}
	public void setTipoVerificacion(String tipoVerificacion) {
		this.tipoVerificacion = tipoVerificacion;
	}
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	@Transient
	public void cargarDatos(String[] celdas){
		SimpleDateFormat fecha =  new SimpleDateFormat("dd/mm/yyyy");
		this.setZona(celdas[0]);
		this.setEstacion(celdas[1]);
		this.setPatente(celdas[2]);
		try {
			this.setFechaInspeccion(fecha.parse(celdas[3]));
			this.setTipoVerificacion(celdas[4]);
			this.setFechaVencimiento(fecha.parse(celdas[5]));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.setNumeroOblea(Integer.parseInt(celdas[6]));
		this.setResultado(celdas[7]);
		
		
	}
	public void persistir(){
		AdministradorVehiculo.agregarVtv(this);
	}
}
