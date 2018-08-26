# Jsonify
Java to JSON serializer and deserializer

**How to use**

**Serializing a java object**


```java
//SimpleTestClass.java
public class SimpleTestClass {

	int			x, y;

	String[][]	strs;
	
	private LinkedList<Integer> ints;

	public SimpleTestClass() {
        x = 5;
		y = 1;
		ints = new LinkedList<>();
		ints.add(1);
		ints.add(2);
		ints.add(3);
		ints.add(4);
		strs= new String[4][1];
		strs[0][0] = "This";
		strs[1][0] = "Is";
		strs[2][0] = "A";
		strs[3][0] = "Test";
	}
}
```

```java
SimpleTestClass t = new SimpleTestClass();
JSONObject testObject = Jsonify.serialize(t);
//Produces : {"strs":[["This"],["Is"],["A"],["Test"]],"ints":[1,2,3,4],"x":5,"y":1}
```

**Building a java object from JSON**
```java
JSONObject testObject = new JSONObject("{"strs":[["This"],["Is"],["A"],["Test"]],"ints":[1,2,3,4],"x":5,"y":1}");
SimpleTestClass object = Jsonify.deserialize(testObject, SimpleTestClass.class);
```

**Third party libraries used**


org.json : https://github.com/stleary/JSON-java

**Info**

Jsonify uses reflection for field walkthrough and serialization and sun.misc.Unsafe to build the objects

**Currently unsupported data structures**
```java
Map

Stack

Queue

```

