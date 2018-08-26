package com.viad.jsonify;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonifySerializator {

	static JSONObject doSerialize(Object obj) {
		JSONObject object = new JSONObject();
		writeFields(obj, object);
		return object;
	}

	private static void writeFields(Object obj, JSONObject res) {
		List<Field> fields = Arrays.stream(obj.getClass().getDeclaredFields()).filter(
		        field -> !Modifier.isStatic(field.getModifiers())).collect(Collectors.toList());

		fields.forEach(field -> {
			Object fieldData = Reflect.get(obj, field);
//			if (Reflect.isArray(field.getType())) {
//				doWriteArray(field.getName(), fieldData, res);
//			} else {
//				doWriteObject(field, fieldData, res);
//			}
			doWriteObject(field, fieldData, res);
		});
	}

	private static Object prepareObject(Object fieldData) {
		if (Reflect.isPrimitive(fieldData.getClass())) {
			return fieldData;
		}
		JSONObject current = new JSONObject();
		if (Reflect.isArray(fieldData.getClass())) {
			return doWriteArray(null, fieldData, null);
		} else
			writeFields(fieldData, current);
		return current;
	}

	private static void doWriteObject(Field field, Object fieldData, JSONObject target) {
		if (fieldData == null)
			return;
		Tools.jsonPut(target, field.getName(), prepareObject(fieldData));
	}

	private static JSONArray doWriteArray(String field, Object fieldData, JSONObject target) {
		JSONArray array = new JSONArray();
		if (fieldData == null) {
			if (target != null)
				Tools.jsonPut(target, field, null);
			return null;
		}
		if (fieldData instanceof boolean[]) {
			boolean[] arr = (boolean[]) fieldData;
			for (int i = 0; i < arr.length; i++) {
				try {
					array.put(i, arr[i]);
				} catch (JSONException e) {
				}
			}
		} else if (fieldData instanceof byte[]) {
			byte[] arr = (byte[]) fieldData;
			for (int i = 0; i < arr.length; i++) {
				try {
					array.put(i, arr[i]);
				} catch (JSONException e) {
				}
			}
		} else if (fieldData instanceof short[]) {
			short[] arr = (short[]) fieldData;
			for (int i = 0; i < arr.length; i++) {
				try {
					array.put(i, arr[i]);
				} catch (JSONException e) {
				}
			}
		} else if (fieldData instanceof char[]) {
			char[] arr = (char[]) fieldData;
			for (int i = 0; i < arr.length; i++) {
				try {
					array.put(i, arr[i]);
				} catch (JSONException e) {
				}
			}
		} else if (fieldData instanceof int[]) {
			int[] arr = (int[]) fieldData;
			for (int i = 0; i < arr.length; i++) {
				try {
					array.put(i, arr[i]);
				} catch (JSONException e) {
				}
			}
		} else if (fieldData instanceof float[]) {
			float[] arr = (float[]) fieldData;
			for (int i = 0; i < arr.length; i++) {
				try {
					array.put(i, arr[i]);
				} catch (JSONException e) {
				}
			}
		} else if (fieldData instanceof long[]) {
			long[] arr = (long[]) fieldData;
			for (int i = 0; i < arr.length; i++) {
				try {
					array.put(i, arr[i]);
				} catch (JSONException e) {
				}
			}
		} else if (fieldData instanceof double[]) {
			double[] arr = (double[]) fieldData;
			for (int i = 0; i < arr.length; i++) {
				try {
					array.put(i, arr[i]);
				} catch (JSONException e) {
				}
			}
		} else if (fieldData instanceof String[]) {
			String[] arr = (String[]) fieldData;
			for (int i = 0; i < arr.length; i++) {
				try {
					array.put(i, arr[i]);
				} catch (JSONException e) {
				}
			}
		} else if (fieldData instanceof Iterable) {
			Iterator<?> it = ((Iterable<?>) fieldData).iterator();
			final int[] index = { 0 };
			it.forEachRemaining(element -> {
				try {
					array.put(index[0]++, prepareObject(element));
				} catch (JSONException e) {
				}
			});
		}else {
			Object[] arr = (Object[]) fieldData;
			for (int i = 0; i < arr.length; i++) {
				try {
					array.put(i, prepareObject(arr[i]));
				} catch (JSONException e) {
				}
			}
		}
		if (target != null)
			Tools.jsonPut(target, field, array);
		return array;
	}

}
