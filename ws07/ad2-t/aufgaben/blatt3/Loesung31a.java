/* $Id: Loesung31a.java,v 1.1 2007-10-27 19:59:55 arne Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */


/**
 * Loesungsvorschlag fuer Aufgabe 3-1a.
 * @author <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/">Arne Johannessen</A>
 * @version $Revision: 1.1 $
 */
public class Loesung31a extends ArraySum {
	
	
	
	/**
	 * Liefert die Summe aller Elemente eines Arrays
	 * (Berechnung mit <code>while</code>-Schleife).
	 * @param array das aufzusummierende Array
	 * @return die Summe aller Elemente von <code>array</code>
	 * @throws NullObjectException falls <code>array == null</code>
	 */
	public static int sum (int[] array) {
		int sum = 0;
		
		int index = 0;
		while (index < array.length) {
			sum += array[index];
			index++;
		}
		
		return sum;
	}
	
	
	
	/** Treiber fuer Aufruf von der Kommandozeilenschnittstelle. */
	public static void main (String[] args) {
		int[] array = ARRAY;
		printArray(array);
		System.out.println("Summe der Elemente (mit while berechnet): "+sum(array));
	}
	
}
