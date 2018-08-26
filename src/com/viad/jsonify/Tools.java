package com.viad.jsonify;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

 class Tools {

	final static sun.misc.Unsafe ALLOCATOR;

	static {
		ALLOCATOR = (sun.misc.Unsafe) Reflect.get(null, sun.misc.Unsafe.class, "theUnsafe");
	}

	 static int size(Iterable<?> iterable) {
		return (int) java.util.stream.StreamSupport
				               		 .stream(iterable.spliterator(), false)
				               		 .count();
	}
	
	 static boolean isValidJSONArray(String jsonArray) {
		try {
			new JSONArray(jsonArray);
			return true;
		} catch (JSONException e) {
			return false;
		}
	}

	 static Iterable<?> iterate(JSONArray array){
		List<Object> list = new ArrayList<>();
		for (int i = 0; i < array.length(); i++) {
			try {
				list.add(array.get(i));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	 static <T> T unsafeAllocateInstance(Class<T> clazz) {
		try {
			return (T) ALLOCATOR.allocateInstance(clazz);
		} catch (InstantiationException e) {
			return null;
		}
	}

	 static JSONObject jsonPut(JSONObject object, String key, Object value) {
		try {
			object.put(key, value);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object;
	}

	 static Object jsonGet(String k, JSONObject object) {
		try {
			return object.get(k);
		} catch (JSONException e) {
			return null;
		}
	}
	
	 static JSONArray jsonGetArray(String k, JSONObject object) {
		try {
			return object.getJSONArray(k);
		} catch (JSONException e) {
			return null;
		}
	}

}
