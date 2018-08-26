package com.viad.jsonify;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ObjectConstructor {

	@SuppressWarnings("unchecked")
	static Object construct(Class<?> clazz, JSONObject template) {
		Object instance = Tools.unsafeAllocateInstance(clazz);
		template.keys().forEachRemaining(objKey -> {
			String key = (String) objKey;
			Field current = Reflect.getField(clazz, key);
			if (Reflect.isArray(current.getType())) {
				Reflect.set(instance, current, recurseFillArray(current.getType(), Tools.jsonGetArray(key, template)));
			} else
				Reflect.set(instance, current, Tools.jsonGet(key, template));
		});
		return instance;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	static void arraySet(Object container, Object element, int index) {
		if (List.class.isAssignableFrom(container.getClass())) {
			List list = (List) container;
			if (index >= list.size())
				list.add(element);
			else
				list.set(index, element);
			list.set(index, element);
		}
		if (container.getClass().isArray()) {
			Array.set(container, index, element);
		}
	}

	static List<Integer> getArrayDimensions(JSONArray array, List<Integer> dimensions) {
		dimensions.add(array.length());
		try {
			if (Tools.isValidJSONArray(array.get(0).toString())) {
				return getArrayDimensions(new JSONArray(array.get(0).toString()), dimensions);
			}
		} catch (JSONException e) {
		}
		return dimensions;
	}

	static Object recurseFillArray(Class<?> containerType, JSONArray array) {
		Object dataContainer = createContainer(containerType, getArrayDimensions(array, new ArrayList<>()));
		for (int i = 0; i < array.length(); i++) {
			try {
				if (Tools.isValidJSONArray(array.get(i).toString())) {
					arraySet(dataContainer, recurseFillArray(
					        containerType.getComponentType() != null ? containerType.getComponentType() : containerType,
					        array.getJSONArray(i)), i);
				} else {
					arraySet(dataContainer, array.get(i), i);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return dataContainer;
	}

	/**
	 * Todo add support for Stack, Queue, LL, Maps
	 * @param type
	 * @param dimensions
	 * @return
	 */
	static Object createContainer(Class<?> type, List<Integer> dimensions) {
		if (type.isInterface()) {
			if (List.class.isAssignableFrom(type)) {
				List<?> array = new ArrayList<>();
				return array;
			}
			if(Queue.class.isAssignableFrom(type)) {
				Queue<?> queue = new LinkedList<>();
				return queue;
			}
			if(Set.class.isAssignableFrom(type)) {
				Set<?> set = new HashSet<>();
				return set;
			}
		}
		if (type.equals(ArrayList.class)) {
			return new ArrayList<>();
		}
		if (type.equals(LinkedList.class)) {
			return new LinkedList<>();
		}
		if(type.equals(HashSet.class)) {
			return new HashSet<>();
		}
		
		if (type.isArray()) {
			Object array = null;
			Class<?> compType = Reflect.getArrayComponentType(type);
			int[] arr = dimensions.stream()
					  	          .mapToInt(i -> i)
					  	          .limit(dimensions.size() - 1)
					  	          .toArray();
			if (Iterable.class.isAssignableFrom(Reflect.getArrayComponentType(type))) {
				array = Array.newInstance(compType, arr);
			} else
				array = Array.newInstance(compType, dimensions.stream()
						                                      .mapToInt(i -> i)
						                                      .toArray());
			return array;
		}
		return null;
	}

}
