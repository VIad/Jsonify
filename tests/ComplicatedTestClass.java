import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ComplicatedTestClass {

	private int[] ints;
	
	private Integer x;
	
	private int yeet;
	
	private SimpleTestClass stc;
	
	private double what = 54.12;
	private Float rly;
	
	private java.awt.Point point;
	
	private String[] st;
	
	private ArrayList<Character> chars;
	
	private List<String[]> weird;
	
	private List<Point>[] locs;
	
	private List<Point> points;

	private Rectangle rect;
	
	public ComplicatedTestClass() {
		yeet = 5;
		stc = new SimpleTestClass();
		locs = new ArrayList[2];
		locs[0] = new ArrayList<>();
		locs[1] = new ArrayList<>();
		locs[0].add(new Point(2,3));
		locs[0].add(new Point(2,4));
		locs[0].add(new Point(2,5));
		locs[1].add(new Point(5,3));
		locs[1].add(new Point(5,4));
		locs[1].add(new Point(5,5));
		weird = Arrays.asList(new String[] {
				"arr1", "is","good"
		}, new String[] {
			"but","arr2","is","better"	
		});
		rly = 124.56f;
		points = Arrays.asList(new Point(12, 5), new Point(6, 7), new Point(-12, 5664));
		rect = new Rectangle(5, 5, 12, -412);
		point = new Point(5, 10);
		x = 56;
		ints = new int[] {
				1,
				2,
				3,
				4
		};
		st = new String[] {
				"dab", "on","them"
		};
		chars = new ArrayList<>();
		chars.add('1');
		chars.add('2');
		chars.add('3');
	}

	@Override
	public String toString() {
		return "ComplicatedTestClass [ints=" + Arrays.toString(ints) + ", x=" + x + ", yeet=" + yeet + ", stc=" + stc
		        + ", what=" + what + ", rly=" + rly + ", point=" + point + ", st=" + Arrays.toString(st) + ", chars="
		        + chars + ", weird=" + weird + ", locs=" + Arrays.toString(locs) + ", points=" + points + ", rect="
		        + rect + "]";
	}
	
	
	public SimpleTestClass getStc() {
		return stc;
	}



	
	
	
}
