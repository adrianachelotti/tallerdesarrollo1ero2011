package web.validators;

import java.util.Map;

import hibernate.AdministradorUsuarios;

import org.apache.wicket.validation.IValidatable;

import org.apache.wicket.validation.validator.AbstractValidator;

@SuppressWarnings("rawtypes")
public class UsernameValidator extends AbstractValidator{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String usernameViejo;

	public UsernameValidator(String username) {
		usernameViejo=username;
		
	}
	
	public UsernameValidator(){
		usernameViejo=null;
		
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	protected void onValidate(IValidatable usuario) {
        String value = ( String )usuario.getValue();
        if((usernameViejo!=null)&&(usernameViejo.compareTo(value)==0)) return;
	    if(AdministradorUsuarios.existeUsuario(value))	error( usuario);
	    
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected Map variablesMap(IValidatable validatable) {
	    Map map = super.variablesMap(validatable);
	    String value = ( String )validatable.getValue();
	    map.put( "value", value );
	    return map;
	}



}
