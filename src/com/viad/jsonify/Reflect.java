package com.viad.jsonify;

import java.lang.reflect.Field;
import java.util.Map;

 class Reflect {

	/**
	 * TODO Treat java.lang.String as primitive ??
	 * @param clazz
	 * @return
	 */
	static boolean isPrimitive(Class<?> clazz) {
		return clazz.isPrimitive() || Integer.class.equals(clazz)
				                   || Character.class.equals(clazz)
				                   || String.class.equals(clazz)
				                   || Short.class.equals(clazz)
				                   || Byte.class.equals(clazz)
				                   || Long.class.equals(clazz)
				                   || Float.class.equals(clazz)
				                   || Double.class.equals(clazz);
	}
	
	static Class<?> getArrayComponentType(Class<?> clazz){
		Class<?> ret = clazz;
		while(true) {
			if(ret.getComponentType() != null)
				ret = ret.getComponentType();
			else
				break;
		}
		return ret;
	}
	
	static boolean isArray(Class<?> clazz) {
		return clazz.isArray() ||
			   Iterable.class.isAssignableFrom(clazz) ||
			   Map.class.isAssignableFrom(clazz);
	}
	
	static boolean isArray(Object object) {
		return object.getClass().isArray() || object instanceof Iterable<?>;
	}
	
	 static Object get(Object obj, Field f) {
		try {
			f.setAccessible(true);
			return f.get(obj);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	 static void set(Object object, Field field, Object value) {
		try {
			field.setAccessible(true);
			field.set(object, value);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	 static Object get(Object obj, Class<?> clazz, String fieldName) {
		try {
			Field field = clazz.getDeclaredField(fieldName);
			field.setAccessible(true);
			return field.get(obj);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			return null;
		}
	}
	
	 static Field getField(Class<?> clazz, String fieldName) {
		try {
			return clazz.getDeclaredField(fieldName);
		} catch (NoSuchFieldException | SecurityException e) {
			return null;
		}
	}
	
}
