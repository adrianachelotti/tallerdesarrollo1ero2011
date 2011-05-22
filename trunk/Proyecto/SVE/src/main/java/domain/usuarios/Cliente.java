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

package domain.usuarios;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CLIENTE")
public class Cliente implements Serializable {

    private static final long serialVersionUID = -8712872385957386182L;

    private Integer id = null;
    private String nombre = null;
    private String apellido = null;
    private Date creado = null;
    
    public Cliente(String fn, String ln){
    	this.nombre=fn;
    	this.apellido=ln;
    	
    }
    
    public Cliente(){
    	
    }

    /**
     * Gets id (primary key).
     */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    /**
     * Sets id (primary key).
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets first name.
     */
    @Column(name="NOMBRE")
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets first name.
     */
    public void setNombre(String firstName) {
        this.nombre = firstName;
    }

    /**
     * Gets last name.
     */
    @Column(name = "APELLIDO")
    public String getApellido() {
        return apellido;
    }

    /**
     * Sets last name.
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

 
    /**
     * Gets date created.
     */
    public Date getCreado() {
        return creado;
    }

    /**
     * Sets date created.
     */
    public void setCreado(Date created) {
        this.creado = created;
    }

 

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(this.getClass().getName() + "-");
        sb.append("  id=" + id);
        sb.append("  firstName=" + nombre);
        sb.append("  lastName=" + apellido);

        sb.append("  created=" + creado);

        return sb.toString();
    }

    /**
     * Returns a hash code value for the object.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((id == null) ? 0 : id.hashCode());

        return result;
    }

    /**
     * Indicates whether some other object is equal to this one.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;

        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }

        return true;
    }

}
