package hibernate.domain.consultas;

import java.sql.Date;

import hibernate.domain.conductores.Conductor;
import hibernate.domain.usuarios.Usuario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="consultas")
public class Consulta {

	private Boolean expedientesJudiciales;
	private Boolean vtv;
	private Boolean siniestros;
	private Boolean scoring;
	private Boolean infracciones;
	private Boolean situacionFinanciera;
	private Integer id;
	private Usuario usuario;
	private String nombre;
	private String apellido;
	private String tipoDoc;
	private Integer documento;
	private Date fecha;	

	
	public Consulta(){
		usuario=null;
		nombre="";
		apellido="";
		tipoDoc="";
		
	}
	
	
	public void setId(Integer id) {
		this.id = id;
	}
	
    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    @Column (name="consulta_id")
	public Integer getId() {
		return id;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
	@OneToOne
	public Usuario getUsuario() {
		return usuario;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getApellido() {
		return apellido;
	}

	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public String getTipoDoc() {
		return tipoDoc;
	}

	public void setDocumento(Integer documento) {
		this.documento = documento;
	}

	public Integer getDocumento() {
		return documento;
	}

	

	public void setExpedientesJudiciales(Boolean expedientesJudiciales) {
		this.expedientesJudiciales = expedientesJudiciales;
	}

	public Boolean isExpedientesJudiciales() {
		return expedientesJudiciales;
	}

	public void setVtv(Boolean vtv) {
		this.vtv = vtv;
	}

	public Boolean isVtv() {
		return vtv;
	}

	public void setSiniestros(Boolean siniestros) {
		this.siniestros = siniestros;
	}

	public Boolean isSiniestros() {
		return siniestros;
	}

	public void setScoring(Boolean scoring) {
		this.scoring = scoring;
	}

	public Boolean isScoring() {
		return scoring;
	}

	public void setInfracciones(Boolean infracciones) {
		this.infracciones = infracciones;
	}

	public Boolean isInfracciones() {
		return infracciones;
	}

	public void setSituacionFinanciera(Boolean situacionFinanciera) {
		this.situacionFinanciera = situacionFinanciera;
	}

	public Boolean isSituacionFinanciera() {
		return situacionFinanciera;
	}

	public void setValoresConductor(Conductor conductor) {
		this.apellido=conductor.getApellidos();
		this.nombre=conductor.getNombre();
		this.tipoDoc=conductor.getTipoDocumento();
		this.documento=conductor.getNumeroDocumento();
		
	}

	public void copiarInformacionMostrar(Consulta consultaBase) {
		this.expedientesJudiciales=consultaBase.expedientesJudiciales;
		this.infracciones=consultaBase.infracciones;
		this.scoring=consultaBase.scoring;
		this.siniestros=consultaBase.siniestros;
		this.situacionFinanciera=consultaBase.situacionFinanciera;
		this.vtv=consultaBase.vtv;
		
		
		
		
		
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	public Date getFecha() {
		return fecha;
	}



}
