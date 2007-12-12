/* $Id: Loesung64.java,v 1.1 2007-12-12 02:58:40 arne Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */



/**
 * Loesungsvorschlag fuer Aufgabe 6-4.
 * 
 * @see <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/ws07/ad2-t/aufgaben/blatt6/">Aufgabenblatt 6</A>
 * @author <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/">Arne Johannessen</A>
 * @version $Revision: 1.1 $
 */
public class Loesung64 {
	
	
	private Stack left;
	private Stack center;
	private Stack right;
	private Stack[] towers = null;
	
	public Loesung64 () {
		left = new Stack("[Links]");
		center = new Stack("[Mitte]");
		right = new Stack("[Rechts]");
		towers = new Stack[] {left, center, right};
	}
	
	
	public void moveDisc (Stack from, Stack to) {
		String disc = from.pop();
		to.push(disc);
		System.err.println("Scheibe "+disc+" von Pfosten "+from.name()+" nach Pfosten "+to.name());
		printAllTowers();
	}
	
	
	public void moveTower (int height, Stack from, Stack to, Stack temp) {
		if (height <= 0) {
			return;
		}
		moveTower(height - 1, from, temp, to);
		moveDisc(from, to);
		moveTower(height - 1, temp, to, from);
	}
	
	
	public void solvePuzzle (String[] discs) {
		
		// Pfosten und Turm vorbereiten
		left.clear();
		center.clear();
		right.clear();
		for (int index = discs.length - 1; index >= 0; index--) {
			left.push(discs[index]);
		}
		printAllTowers();
		
		// und los geht's
		moveTower(discs.length, left, right, center);
	}
	
	
	public void printAllTowers () {
		final int WIDTH = 20;
		final char AIR = ' ';
		final char BASE = '=';
		final String SPACER = "   ";
		System.out.println();
		int maxHeight = Math.max(Math.max(left.size(), center.size()), right.size());
		java.util.Iterator[] iterators = new java.util.Iterator[towers.length];
		for (int index = 0; index < iterators.length; index++) {
			iterators[index] = towers[index].iterator();
		}
		for (int height = maxHeight; height >= 0; height--) {
			for (int index = 0; index < towers.length; index++) {
				String level = "";
				if (towers[index].size() > height) {
					level = (String)iterators[index].next();
				}
				System.out.print(space(level, WIDTH, AIR)+SPACER);
			}
			System.out.println();
		}
		for (int index = 0; index < towers.length; index++) {
			System.out.print(space("", WIDTH, BASE)+SPACER);
		}
		System.out.println();
		for (int index = 0; index < towers.length; index++) {
			System.out.print(space(towers[index].name(), WIDTH, AIR)+SPACER);
		}
		System.out.println();
		System.out.println();
	}
	
	private String space (String text, int width, char space) {
		StringBuffer buffer = new StringBuffer(width);
		buffer.append(text);
		for (int length = buffer.length(); length < width; length++) {
			buffer.append(space);
		}
		return buffer.toString();
	}
	
	
	/** Treiber fuer Aufruf von der Kommandozeilenschnittstelle. */
	public static void main (String[] args) {
		Loesung64 hanoi = new Loesung64();
		String[] discs = new String[] {"*", "**", "***"};
		hanoi.solvePuzzle(discs);
	}
	
	
	
	// um mit einfachen Mitteln eine brauchbare Ausgabe zu bekommen, ist es praktisch, den Stapeln einen Namen geben zu koennen
	class Stack extends Loesung63 {
		
		private final String name;
		
		public Stack (String name) {
			super();
			this.name = name;
		}
		
		public String name () {
			return this.name;
		}
		
	}
	
}
