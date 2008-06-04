/* $Id: Loesung33.java,v 1.3 2008-06-04 18:23:02 aj3 Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */


import java.util.LinkedList;


/**
 * Hilfsprogramm fuer die Loesung von Aufgabe 3-3.
 * <p>
 * Aufruf im Terminal z. B. mit:
 * <br>$ <kbd>time java Loesung33 30000 10 3-1</kbd>
 * <br>$ <kbd>time java Loesung33 30000 10 3-2</kbd>
 * 
 * @see <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/ss08/ad2-t/aufgaben/blatt3/">Aufgabenblatt 3</A>
 * @author <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/">Arne Johannessen</A>
 * @version $Revision: 1.3 $
 */
public class Loesung33 {
	
	
	// this class is not to be instantiated
	private Loesung33() {
	}
	
	
	
	/**
	 * Hilfsmethode zur Zeitmessung: sucht wiederholt Zufallswerte in
	 * Zufallslisten. Alle Suchergebnisse werden ignoriert, eine
	 * Zeitmessung erfolgt hier nicht und muss daher vom Aufrufer
	 * erbracht werden.
	 * 
	 * @param listSize die Groesse der Liste, in der gesucht werden
	 *  soll (<var>n</var>)
	 * @param repetitions die Anzahl der Wiederholungen der Suche
	 * @param algorithm <code>"3-1"</code> fuer das Loesungsverfahren
	 *  aus Aufgabe 3-1, <code>"3-2"</code> fuer das Loesungsverfahren
	 *  aus Aufgabe 3-2
	 */
	static void run (final int listSize, final int repetitions, final String algorithm) {
		LinkedList list = StringListFactory.createStringList(listSize);
		
		boolean algorithm1 = algorithm.equals("3-1");
		if (! algorithm1 && ! algorithm.equals("3-2")) {
			throw new IllegalArgumentException();
		}
		Loesung31 loesung31 = new Loesung31();
		Loesung32 loesung32 = new Loesung32();
		
		for (int i = 0; i < repetitions; i++) {
			// :KLUDGE: this kind of condition should have been avoided by an interface...
			if (algorithm1) {
				loesung31.find(list, StringListFactory.pickRandomString(list));
			}
			else {
				loesung32.find(list, StringListFactory.pickRandomString(list));
			}
		}
	}
	
	
	
	/**
	 * Hilfsprogramm zur Zeitmessung: sucht wiederholt Zufallswerte in
	 * Zufallslisten. Alle Suchergebnisse werden ignoriert, eine
	 * Zeitmessung erfolgt hier nicht und muss daher vom Aufrufer
	 * erbracht werden.
	 * <p>
	 * Diese Methode ruft lediglich <code>run()</code> auf.
	 * 
	 * @param args Array der Kommandozeilenparameter. Erwartete
	 *  Eingaben: <ul>
	 *  <li><code>[0]</code> die Groesse der Liste, in der gesucht
	 *  werden soll (<var>n</var>)
	 *  <li><code>[1]</code> die Anzahl der Wiederholungen der Suche
	 *  <li><code>[2]</code> <code>"3-1"</code> fuer das
	 *  Loesungsverfahren aus Aufgabe 3-1, <code>"3-2"</code> fuer
	 *  das Loesungsverfahren aus Aufgabe 3-2
	 */
	public static void main (final String[] args) {
		Loesung33.run(Integer.parseInt(args[0]), Integer.parseInt(args[1]), args[2]);
	}
	
}
