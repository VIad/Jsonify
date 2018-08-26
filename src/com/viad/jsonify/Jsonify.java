package com.viad.jsonify;

import org.json.JSONObject;

public class Jsonify {

	public static JSONObject serialize(Object object) {
		return JsonifySerializator.doSerialize(object);
	}
	
	public static <T> T deserialize(JSONObject object, Class<T> clazz){ 
		return JsonifyDeserializator.doDeserialize(object, clazz);
	}
	
}
