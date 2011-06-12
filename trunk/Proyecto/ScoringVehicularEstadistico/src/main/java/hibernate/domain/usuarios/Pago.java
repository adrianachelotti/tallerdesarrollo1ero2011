package hibernate.domain.usuarios;

import javax.persistence.*;

import org.hibernate.annotations.Type;
import java.sql.Date;
import org.hibernate.annotations.GenericGenerator;




@Entity
@Table(name="pagos")
public class Pago {
	
	
	private long id;
	private Cliente cliente;
	//TODO: esto puede ser onetoone
	private String formaPago;
	private Double monto;
	@Type(type="date")
	private String concepto;
	private Date fecha;
	
	
    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    @Column(name = "transaccion_id")
    public long getId() {
            return id;
    }
    public void setId(long id) {
            this.id = id;
    }
    
    @OneToOne
    public Cliente getCliente() {
            return cliente;
    }
    
    public void setCliente(Cliente cliente) {
            this.cliente = cliente;
    }
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}
	public String getFormaPago() {
		return formaPago;
	}
	public void setMonto(Double monto) {
		this.monto = monto;
	}
	public Double getMonto() {
		return monto;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	public String getConcepto() {
		return concepto;
	}



	

}
