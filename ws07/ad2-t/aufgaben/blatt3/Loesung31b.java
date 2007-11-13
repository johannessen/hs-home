/* $Id: Loesung31b.java,v 1.3 2007-11-13 02:12:36 arne Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */


/**
 * Loesungsvorschlag fuer Aufgabe 3-1b.
 * 
 * @see <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/ws07/ad2-t/aufgaben/blatt3/">Aufgabenblatt 3</A>
 * @author <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/">Arne Johannessen</A>
 * @version $Revision: 1.3 $
 */
public class Loesung31b extends ArraySum {
	
	
	
	/**
	 * Liefert die Summe aller Elemente eines Arrays
	 * (rekursive Berechnung).
	 * 
	 * @param array das aufzusummierende Array
	 * @return die Summe aller Elemente von <code>array</code>
	 * @throws NullPointerException falls <code>array == null</code>
	 */
	public static int sum (int[] array) {
		return sum(array, 0);
	}
	
	
	
	/**
	 * Berechnet rekursiv die Summe aller Elemente eines
	 * Arrays von einem bestimmten Element an.
	 * 
	 * @param array das aufzusummierende Array
	 * @param index der Index des ersten aufzusummierenden
	 * Elementsersten
	 * @return die Summe aller Elemente von <code>array</code>;
	 * <code>0</code>, falls <code>index >= array.length</code>
	 * @throws NullPointerException falls <code>array == null</code>
	 */
	private static int sum (int[] array, int index) {
		if (index >= array.length) {
			return 0;
		}
		return array[index] + sum(array, index + 1);
	}
	
	
	
	/** Treiber fuer Aufruf von der Kommandozeilenschnittstelle. */
	public static void main (String[] args) {
		int[] array = ARRAY;
		printArray(array);
		System.out.println("Summe der Elemente (rekursiv berechnet): "+sum(array));
	}
	
}
