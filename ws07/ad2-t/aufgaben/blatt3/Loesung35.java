/* $Id: Loesung35.java,v 1.1 2007-10-27 19:59:55 arne Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */


import java.math.BigInteger;



/**
 * Loesungsvorschlag fuer Aufgabe 3-5.
 * <p>Fuer beliebig grosse Eingabewerte ist in Java eine Rekursion keine
 * Loesung. Aus diesem Grund muss der Algorithmus aus Aufgabe 3-2 zunaechst
 * in eine Iteration umgeformt werden. Erst danach kann von den primitiven
 * Typen auf solche gewechselt werden, die mit beliebig grossen Zahlen
 * hantieren koennen.
 * @see Loesung32
 * @see <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/ws07/ad2-t/aufgaben/blatt3/">Aufgabenblatt 3</A>
 * @author <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/">Arne Johannessen</A>
 * @version $Revision: 1.1 $
 */
public class Loesung35 {
	
	
	
/*	// iterativer Algorithmus mit long:
	public static long factorial (int n) {
		if (n < 0) {
			throw new IllegalArgumentException("Fakultaet kleiner null ist nicht definiert");
		}
		
		long f = 1;
		while (n > 1) {
			f = f * n;
			n = n - 1;
		}
		return f;
	}
*/	
	
	
	/**
	 * Berechnet rekursiv die Fakultaet einer Zahl. Diese Methode
	 * liefert ein korrektes Ergebnis fuer Eingabewerte bis
	 * einschliesslich 20; bei <code>n > 20</code> findet ein
	 * binaerer Ueberlauf statt.
	 * @param n die Zahl, von der die Fakultaet zu berechnen ist
	 * @return die Fakultaet von <code>n</code> fuer
	 * <code>n &lt;= 20</code>
	 * @throws IllegalArgumentException falls <code>n &lt; 0</code>
	 */
	public static BigInteger factorial (BigInteger n) {
		if (n.compareTo(BigInteger.ZERO) < 0) {
			throw new IllegalArgumentException("Fakultaet kleiner null ist nicht definiert");
		}
		
		BigInteger f = BigInteger.ONE;
		while (n.compareTo(BigInteger.ONE) > 0) {
			f = f.multiply(n);
			n = n.subtract(BigInteger.ONE);
		}
		return f;
	}
	
	
	
	/** Treiber fuer Aufruf von der Kommandozeilenschnittstelle. */
	public static void main (String[] args) {
		BigInteger n = new BigInteger(args[0]);
		System.out.println(n+"! = "+factorial(n));
	}
	
}
