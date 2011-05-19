package usuarios.domain;

/**
 * Modela un identificador para un usuario.
 * @author grupo 2
 *
 */
public class Documento {

	String tipo;
	Integer numero;
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	
	
}
