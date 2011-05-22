package domain.usuarios;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="authorities")
public class Permiso {
	
	private String authority = null;
	
	 /**
     * Gets id (primary key).
     */
    @Id
    public String getAuthority() {
        return authority;
    }

    /**
     * Sets id (primary key).
     */
    public void setAuthority(String auth) {
        this.authority = auth;
    }
    
    @Override
    public String toString(){
    	return authority;
    }

}
