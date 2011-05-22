package action;



import java.util.List;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.Preparable;


import dao.UsuarioDao;


import domain.usuarios.Usuario;



public class UsuarioAction implements Preparable {
    private UsuarioDao service;
    private List<Usuario> usuarios;
    private Usuario usuario;
    private String id;

    public UsuarioAction(UsuarioDao service) {
        this.service = service;
    }

    public String execute() {
        this.usuarios = service.findUsuarios();
        return Action.SUCCESS;
    }

    public String save() {
        this.service.save(usuario);
        this.usuario = new Usuario();
        return execute();
    }

  

    public List<Usuario> getUsuarios() {
    	return usuarios;
    }
    
 
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void prepare() throws Exception {
        if (id != null)
            usuario = service.obtenerUsuario(id);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setPerson(Usuario usuario) {
        this.usuario = usuario;
    }
}
