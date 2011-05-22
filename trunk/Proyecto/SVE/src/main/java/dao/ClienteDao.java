/*
 * Copyright 2007-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dao;


import java.util.List;

import org.springframework.security.annotation.Secured;


import domain.usuarios.Cliente;




/**
 * Person DAO interface.
 * 
 * @author David Winterfeldt
 */
public interface ClienteDao {

    public List<Cliente> findClientes();
    public Cliente obtenerCliente(Integer id);
    

    
    @Secured ({"ROLE_ADMIN"})
    public Cliente save(Cliente cliente);
    @Secured ({"ROLE_ADMIN"})
    public void delete(Cliente cliente);

    

}

