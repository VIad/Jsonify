import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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

	@Override
	public String toString() {
		return "SimpleTestClass [x=" + x + ", y=" + y + ", strs=" + Arrays.toString(strs) + ", ints=" + ints + "]";
	}


	public LinkedList<Integer> getInts() {
		return ints;
	}

	
	

}
