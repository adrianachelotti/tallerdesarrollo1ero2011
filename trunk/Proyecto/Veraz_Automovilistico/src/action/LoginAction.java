package action;

import java.util.Map;

import usuarios.appl.UsuarioAppl;
import usuarios.domain.UsuarioDO;
import com.opensymphony.xwork2.*;


public class LoginAction extends ActionSupport {
    private static final long serialVersionUID = 2228074990625416790L;
    private static final String USUARIO_NO_AUTORIZADO    = "El usuario introducido no está autorizado.";
    private static final String ERROR_INTERNO                  = "Error interno. Por favor, inténtelo otra vez en unos minutos.";
    private UsuarioDO usuario;
    private String mensaje;
    
    @SuppressWarnings("unchecked")
	public String execute () {
          String destino = INPUT;
          try {
                if (UsuarioAppl.esUsuarioAutorizado(usuario)) {
                      Map session = ActionContext.getContext().getSession();
                      session.put("usuario", usuario);
                      destino = SUCCESS;
                } else {
                      setMensaje(USUARIO_NO_AUTORIZADO);
                }
          } catch (Exception e) {
                e.printStackTrace();
                setMensaje(ERROR_INTERNO);
          }
          return destino;
    }
    
    public UsuarioDO getUsuario() {
          return usuario;
    }
    
    public void setUsuario(UsuarioDO usuario) {
          this.usuario = usuario;
    }
    public String getMensaje() {
          return mensaje;
    }
    public void setMensaje(String mensaje) {
          this.mensaje = mensaje;
    }
}
