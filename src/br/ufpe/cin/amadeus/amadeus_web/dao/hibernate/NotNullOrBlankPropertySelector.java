package br.ufpe.cin.amadeus.amadeus_web.dao.hibernate;

import org.hibernate.criterion.Example.PropertySelector;
import org.hibernate.type.Type;

public final class NotNullOrBlankPropertySelector implements PropertySelector {

	private static final long serialVersionUID = 1L;

	public boolean include(Object object, String propertyName, Type type) {  
		return object!=null || (  
				(object instanceof String) && !( (String) object ).equals("")  
		);  
	} 
}
