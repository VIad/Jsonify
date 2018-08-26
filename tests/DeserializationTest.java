import org.json.JSONException;
import org.json.JSONObject;

import com.viad.jsonify.Jsonify;

public class DeserializationTest {

	public static void main(String[] args) throws JSONException {
		SimpleTestClass stc = Jsonify.deserialize(new JSONObject("{\"strs\":[[\"This\"],[\"Is\"],[\"A\"],[\"Test\"]],\"ints\":[1,2,3,4],\"x\":5,\"y\":1}"),
		        SimpleTestClass.class);
		System.out.println(stc.getInts().getClass());

		ComplicatedTestClass pt = Jsonify.deserialize(new JSONObject(
"{\"st\":[\"dab\",\"on\",\"them\"],\"locs\":[[{\"x\":2,\"y\":3},{\"x\":2,\"y\":4},{\"x\":2,\"y\":5}],[{\"x\":5,\"y\":3},{\"x\":5,\"y\":4},{\"x\":5,\"y\":5}]],\"yeet\":5,\"stc\":{\"strs\":[[\"This\"],[\"Is\"],[\"A\"],[\"Test\"]],\"x\":5,\"y\":1},\"point\":{\"x\":5,\"y\":10},\"points\":[{\"x\":12,\"y\":5},{\"x\":6,\"y\":7},{\"x\":-12,\"y\":5664}],\"weird\":[[\"arr1\",\"is\",\"good\"],[\"but\",\"arr2\",\"is\",\"better\"]],\"rect\":{\"x\":5,\"width\":12,\"y\":5,\"height\":-412},\"what\":54.12,\"ints\":[1,2,3,4],\"x\":56,\"rly\":124.56,\"chars\":[\"1\",\"2\",\"3\"]}"),
		        ComplicatedTestClass.class);

		System.out.println(pt.getStc().strs[3][0]);
		
		System.out.println(pt);
	}

}
