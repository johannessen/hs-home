/* $Id: Loesung64.java,v 1.3 2007/12/12 03:59:48 arne Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */



/**
 * Loesungsvorschlag fuer Aufgabe 6-4.
 * 
 * @see <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/ws07/ad2-t/aufgaben/blatt6/">Aufgabenblatt 6</A>
 * @author <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/">Arne Johannessen</A>
 * @version $Revision: 1.3 $
 */
public class Loesung64 {
	
	private Stack left;
	private Stack center;
	private Stack right;
	
	private Stack[] towers;
	
	
	/** konstruktor */
	public Loesung64 () {
		
		// Pfosten erzeugen
		left = new Stack("[Links]");
		center = new Stack("[Mitte]");
		right = new Stack("[Rechts]");
		
		// einen Array fuer indizierten Pfostenzugriff bereitstellen
		towers = new Stack[] {left, center, right};
	}
	
	
	
	
	
	/** oberste Scheibe von from nach to bewegen */
	public void moveDisc (Stack from, Stack to) {
		
		// bewegung mit Stapel-Operationen durchfuehren
		String disc = from.pop();
		to.push(disc);
		
		// bewegung protokollieren
		System.err.println("Scheibe "+disc+" von Pfosten "+from.name()+" nach Pfosten "+to.name());
		printAllTowers();
	}
	
	
	/** Turm der Hoehe height von from nach to bewegen, mit Hilfspfosten temp */
	public void moveTower (int height, Stack from, Stack to, Stack temp) {
		if (height <= 0) {
			return;
		}
		moveTower(height - 1, from, temp, to);
		moveDisc(from, to);
		moveTower(height - 1, temp, to, from);
	}
	
	
	/** Spiel loesen fuer die uebergebene Liste von Scheiben */
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
	
	
	
	
	
	/** alle Pfosten mitsamt Scheiben in ASCII-Grafik ausgeben */
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
		for (int height = maxHeight; height > 0; height--) {
			for (int index = 0; index < towers.length; index++) {
				String level = "";
				if (towers[index].size() >= height) {
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
	
	
	/** den STring text bis zur Laenge width mit space auffuellen */
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
		
		// Scheiben erzeugen
		String[] discs;
		if (args.length == 0) {
			discs = new String[] {"*", "**", "***"};
		}
		else {
			// Scheibenzahl von Kommandozeile einlesen
			int discCount = Integer.parseInt(args[0]);
			discs = new String[discCount];
			for (int discIndex = 0; discIndex < discCount; discIndex++) {
				StringBuffer buffer = new StringBuffer(discIndex + 1);
				for (int asterisk = 1; asterisk <= discIndex + 1; asterisk++) {
					buffer.append('*');
				}
				discs[discIndex] = buffer.toString();
			}
		}
		
		// Spiel loesen
		Loesung64 hanoi = new Loesung64();
		hanoi.solvePuzzle(discs);
	}
	
	
	
	
	
	/** um mit einfachen Mitteln eine brauchbare Ausgabe zu bekommen, ist es praktisch, den Stapeln einen Namen geben zu koennen */
	protected class Stack extends Loesung63 {
		
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
