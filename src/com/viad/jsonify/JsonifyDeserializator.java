package com.viad.jsonify;

import java.lang.reflect.Field;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonifyDeserializator {

	static <T> T doDeserialize(JSONObject from, Class<T> clazz) {
		T instance = Tools.unsafeAllocateInstance(clazz);
		setFields(from, instance);
		return instance;
	}

	@SuppressWarnings("unchecked")
	private static <T> void setFields(JSONObject object, T instance) {
		object.keys().forEachRemaining(objKey -> {
			String key = (String) objKey;
			Object value = Tools.jsonGet(key, object);
			Field field = Reflect.getField(instance.getClass(), key);
			if (field != null) {
				if (!Reflect.isArray(field.getType())) {
					if (Reflect.isPrimitive(field.getType())) {
						/**
						 * Since json doesn't have a float datatype, whenever jsonify finds a float field it casts the json double accordingly
						 */
						if (field.getType() == float.class || field.getType() == Float.class) {
							try {
								float wh = (float) object.getDouble(key);
								Reflect.set(instance, field, wh);
							} catch (JSONException e) {
							}
						} else {
							Reflect.set(instance, field, value);
						}
					} else {
						fillData(instance, field, value);
					}
				} else {
					tryHandleArray(instance, field, value);
				}
			}
		});
	}

	private static void fillData(Object instance, Field field, Object data) {
		try {
			Reflect.set(instance, field, ObjectConstructor.construct(field.getType(), new JSONObject(data.toString())));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	private static void tryHandleArray(Object instance, Field field, Object data) {
		try {
			Reflect.set(instance, field, ObjectConstructor.recurseFillArray(field.getType(), new JSONArray(data.toString())));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
