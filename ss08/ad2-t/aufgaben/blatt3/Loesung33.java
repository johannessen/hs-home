
import java.util.*;

public class Loesung33 {
	
	// $ time java Loesung33 10000 20 100 1
	// $ time java Loesung33 10000 20 100 2
	
	public static void main (String[] args) {
		StringListFactory factory = new StringListFactory();
		LinkedList list = factory.createStringList(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
		Loesung31 l1 = new Loesung31();
		Loesung32 l2 = new Loesung32();
		for (int i = 0; i < Integer.parseInt(args[2]); i++) {
			if (args[3].equals("1")) {
				l1.find(list, factory.pickRandomString());
			}
			else if (args[3].equals("2")) {
				l2.find(list, factory.pickRandomString());
			}
			else {
				throw new IllegalArgumentException();
			}
		}
	}
	
}
