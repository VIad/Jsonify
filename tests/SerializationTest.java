import org.json.JSONException;

import com.viad.jsonify.Jsonify;

public class SerializationTest {

	public static void main(String[] args) throws JSONException {
		ComplicatedTestClass pt = new ComplicatedTestClass();
		System.out.println(Jsonify.serialize(pt));
		SimpleTestClass t = new SimpleTestClass();
		System.out.println(Jsonify.serialize(t));
	}
	
}
