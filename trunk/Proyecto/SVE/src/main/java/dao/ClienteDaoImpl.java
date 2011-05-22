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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import domain.usuarios.Cliente;




/**
 * Person DAO implementation.
 * 
 * @author David Winterfeldt
 */
@Repository
@Transactional(readOnly = true)
public class ClienteDaoImpl implements ClienteDao {

    private EntityManager em = null;

    /**
     * Sets the entity manager.
     */
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

   

    @SuppressWarnings("unchecked")
    public List<Cliente> findClientes() {
    		List<Cliente> c=em.createQuery("select p from Cliente p order by p.apellido, p.nombre").getResultList();
    		
        return c;
    }
    
    
    public Cliente obtenerCliente(Integer id) {
    	return em.find(Cliente.class, id);
    		
        
    }

    


    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public Cliente save(Cliente person) {
        return em.merge(person);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void delete(Cliente person) {
        em.remove(em.merge(person));
    }

  

}
