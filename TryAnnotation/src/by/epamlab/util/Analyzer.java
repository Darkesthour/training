package by.epamlab.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import by.epamlab.annotation.Equal;
import by.epamlab.annotation.Equal.EqualType;

public class Analyzer {
	
	// Get all annotated fields including superclass' fields
	List<Field> getAnnotatedFields(Class<?> cl) {
		List<Field> fieldsAnnotated = new ArrayList<>();
		Field[] fields = cl.getDeclaredFields();
		
		for(Field field: fields) {
			if(field.isAnnotationPresent(Equal.class)) {
				fieldsAnnotated.add(field);
			}
		}
		
		Class<?> supCl = cl.getSuperclass();
		
		if(!supCl.equals(Object.class)) {
			fieldsAnnotated.addAll(getAnnotatedFields(supCl));
		}
		
		return fieldsAnnotated;
	}

	boolean compareObjects(Object firstObj, Object secondObj) throws IllegalArgumentException, IllegalAccessException {
		final String MSG_ERR_DIFF_NUMBER_FIELD = "Comparing objects have different number of annotated fields";
		final String MSG_INTRO_COMPARING = "Compare object %s with object %s%n";
		final String MSG_RESULT_FIELDS_COMPARING = "Fields %s are compared by %s and they are %s%n";
		final String MSG_RESULT_OBJ_COMPARING = "Result of comparing: %s%n";
		final String DELIMITER = new String(new char[75]).replace("\0", "_") + "%n%n";
		final String MSG_EQUALS = "equals";
		final String MSG_NOT_EQUALS = "not equals";
		
		System.out.format(MSG_INTRO_COMPARING, firstObj, secondObj);
		
		boolean result = false;
		
		Class<?> firstObjClass		= firstObj.getClass();
		Class<?> secondObjClass		= secondObj.getClass();

		List<Field> firstObjFieldsAnnotated		= getAnnotatedFields(firstObjClass);
		List<Field> secondObjFieldsAnnotated	= getAnnotatedFields(secondObjClass);
		
		if(firstObjFieldsAnnotated.size() != secondObjFieldsAnnotated.size()) {
			System.out.println(MSG_ERR_DIFF_NUMBER_FIELD);
			System.out.format(DELIMITER);
			return false;
		}
		
		firstObjLoop:
		for(Field firstObjField: firstObjFieldsAnnotated) {
			for(Field secondObjField: secondObjFieldsAnnotated) {
				String firstObjFieldName = firstObjField.getName();
				EqualType firstObjEqualType = firstObjField.getAnnotation(Equal.class).compareBy();
				
				if(firstObjFieldName.equals(secondObjField.getName()) &&
						firstObjEqualType == secondObjField.getAnnotation(Equal.class).compareBy()) {
					
					firstObjField.setAccessible(true);
					secondObjField.setAccessible(true);
					
					if(firstObjEqualType == EqualType.REFERENCE) {
						result = firstObjField.get(firstObj) == secondObjField.get(secondObj);
					}
					else {
						result = firstObjField.get(firstObj).equals(secondObjField.get(secondObj));
					}
					
					System.out.format(MSG_RESULT_FIELDS_COMPARING, 
							firstObjFieldName,
							firstObjEqualType.toString().toLowerCase(),
							result ? MSG_EQUALS : MSG_NOT_EQUALS);
					
					// If result is false (fields with the same name aren't equals) break outer loop
					if (!result) {
						break firstObjLoop;
					}
				}
			}
		}
		
		System.out.format(MSG_RESULT_OBJ_COMPARING, result ? MSG_EQUALS : MSG_NOT_EQUALS);
		System.out.format(DELIMITER);
		
		return result;
	}

}
