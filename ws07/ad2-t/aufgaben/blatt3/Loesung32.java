/* $Id: Loesung32.java,v 1.2 2007-10-28 22:50:20 arne Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */



/**
 * Loesungsvorschlag fuer Aufgabe 3-2.
 * 
 * @see <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/ws07/ad2-t/aufgaben/blatt3/">Aufgabenblatt 3</A>
 * @author <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/">Arne Johannessen</A>
 * @version $Revision: 1.2 $
 */
public class Loesung32 {
	
	
	
	/**
	 * Berechnet rekursiv die Fakultaet einer Zahl. Diese Methode
	 * liefert ein korrektes Ergebnis fuer Eingabewerte bis
	 * einschliesslich 20; bei <code>n > 20</code> findet ein
	 * binaerer Ueberlauf statt.
	 * 
	 * @param n die Zahl, von der die Fakultaet zu berechnen ist
	 * @return die Fakultaet von <code>n</code> fuer
	 * <code>n &lt;= 20</code>
	 * @throws IllegalArgumentException falls <code>n &lt; 0</code>
	 * @see Loesung35
	 */
	public static long factorial (int n) {
		if (n < 0) {
			throw new IllegalArgumentException("Fakultaet kleiner null ist nicht definiert");
		}
		if (n == 0) {
			return 1;
		}
		return n * factorial(n - 1);
	}
	
	
	
	/** Treiber fuer Aufruf von der Kommandozeilenschnittstelle. */
	public static void main (String[] args) {
		int n = Integer.parseInt(args[0]);
		System.out.println(n+"! = "+factorial(n));
	}
	
}
