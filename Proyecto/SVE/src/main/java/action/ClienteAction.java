package action;


import java.util.List;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.Preparable;

import dao.ClienteDao;
import domain.usuarios.Cliente;



public class ClienteAction implements Preparable {
    private ClienteDao service;
    private List<Cliente> clientes;
    private Cliente cliente;
    private Integer id;

    public ClienteAction(ClienteDao service) {
        this.service = service;
    }

    public String execute() {
        this.clientes = service.findClientes();
        return Action.SUCCESS;
    }

    public String save() {
        this.service.save(cliente);
        this.cliente = new Cliente();
        return execute();
    }

  

    public List<Cliente> getUsuarios() {
    	return clientes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void prepare() throws Exception {
        if (id != null)
            cliente = service.obtenerCliente(id);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
