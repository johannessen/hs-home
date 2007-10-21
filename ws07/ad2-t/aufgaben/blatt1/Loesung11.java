/* $Id: Loesung11.java,v 1.1 2007-10-21 01:08:19 arne Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */


/**
 * Loesungsvorschlag fuer Aufgabe 1-2.
 * @author <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/">Arne Johannessen</A>
 * @version $Revision: 1.1 $
 */
public class Loesung12 extends ArraySum {
	
	
	/**
	 * Liefert die Summe aller Elemente eines Arrays.
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
