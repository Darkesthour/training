package by.epamlab.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Equal {
	
	enum EqualType {
		REFERENCE, VALUE
	}
	
	public EqualType compareBy();

}
