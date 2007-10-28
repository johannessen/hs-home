/* $Id: Loesung12.java,v 1.3 2007-10-28 22:44:57 arne Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */


/**
 * Loesungsvorschlag fuer Aufgabe 1-2.
 * 
 * @see <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/ws07/ad2-t/aufgaben/blatt1/">Aufgabenblatt 1</A>
 * @author <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/">Arne Johannessen</A>
 * @version $Revision: 1.3 $
 */
public class Loesung12 extends ArraySum {
	
	
	/**
	 * Liefert die Summe aller Elemente eines Arrays.
	 * 
	 * @param array das aufzusummierende Array
	 * @return die Summe aller Elemente von <code>array</code>
	 * @throws NullObjectException falls <code>array == null</code>
	 */
	public static int sum (int[] array) {
		int sum = 0;
		for (int index = 0; index < array.length; index++) {
			sum += array[index];
		}
		return sum;
	}
	
	
	
	/** Treiber fuer Aufruf von der Kommandozeilenschnittstelle. */
	public static void main (String[] args) {
		int[] array = ARRAY;
		printArray(array);
		System.out.println("Summe der Elemente: "+sum(array));
	}
	
}
