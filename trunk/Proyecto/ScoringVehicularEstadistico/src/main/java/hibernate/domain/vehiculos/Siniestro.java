package hibernate.domain.vehiculos;



import hibernate.AdministradorConductores;
import hibernate.AdministradorVehiculo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * Modela un siniestro.
 * @author grupo 2
 *
 */

@Entity
@Table(name="siniestros")
public class Siniestro {

	private Integer identificador;
	private String tipo;
	private Date fecha;
	private String descripcion;
	private String lugar;
	private Float monto;
	private String patente;
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
	
	public String getPatente() {
		return patente;
	}
	public void setPatente(String patente) {
		this.patente = patente;
	}
	
    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    @Column (name="siniestro_id")
	public Integer getIdentificador() {
		return identificador;
	}
	public void setIdentificador(Integer identificador) {
		this.identificador = identificador;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getLugar() {
		return lugar;
	}
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	public Float getMonto() {
		return monto;
	}
	public void setMonto(Float monto) {
		this.monto = monto;
	}
	
	@Transient
	public void cargarDatos(String[] celdas){
		SimpleDateFormat fecha =  new SimpleDateFormat("dd/mm/yyyy");
		this.setPatente(celdas[0]);
		this.setTipo(celdas[1]);
		try {
			this.setFecha(fecha.parse(celdas[2]));
		} catch (Exception e) {
				e.printStackTrace();
		}
		this.setDescripcion(celdas[3]);
		this.setLugar(celdas[4]);
		this.setMonto(Float.parseFloat(celdas[5]));
		
		
	}
	@Transient
	public void persistir(){
		AdministradorVehiculo.agregarSiniestro(this);
	}
	
}
