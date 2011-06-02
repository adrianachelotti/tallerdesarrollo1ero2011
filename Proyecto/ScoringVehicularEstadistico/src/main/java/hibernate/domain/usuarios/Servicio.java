package hibernate.domain.usuarios;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="servicios")
public class Servicio {

	private String codigo;
	private String descripcion;
	private double precio;
	private String unidad;
	private Integer maximoConsultas; //TODO: va esto al final?
	private Boolean solicitaAnalisisConsulta;
	private Boolean solicitaAnalisisIndividuo;
	
	public Servicio(){
		
	}
	
	public Servicio(String codigo, String desc, double precio, String unidad, Integer maxCons,Boolean ac, Boolean ai ){
		this.setCodigo(codigo);
		this.descripcion=desc;
		this.precio=precio;
		this.unidad=unidad;
		this.maximoConsultas=maxCons;
		this.solicitaAnalisisConsulta=ac;
		this.solicitaAnalisisIndividuo=ai;
	}
	
	
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public double getPrecio() {
		return precio;
	}
	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}
	public String getUnidad() {
		return unidad;
	}
	public void setMaximoConsultas(Integer maximoConsultas) {
		this.maximoConsultas = maximoConsultas;
	}
	public Integer getMaximoConsultas() {
		return maximoConsultas;
	}
	public void setSolicitaAnalisisConsulta(Boolean solicitaAnalisisConsulta) {
		this.solicitaAnalisisConsulta = solicitaAnalisisConsulta;
	}
	public Boolean getSolicitaAnalisisConsulta() {
		return solicitaAnalisisConsulta;
	}
	public void setSolicitaAnalisisIndividuo(Boolean solicitaAnalisisIndividuo) {
		this.solicitaAnalisisIndividuo = solicitaAnalisisIndividuo;
	}
	public Boolean getSolicitaAnalisisIndividuo() {
		return solicitaAnalisisIndividuo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Id
	public String getCodigo() {
		return codigo;
	}
	
}
