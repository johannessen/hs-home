/* $Id: Loesung61.java,v 1.3 2008/06/04 19:51:14 aj3 Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */


/**
 * Loesungsvorschlag fuer Aufgabe 6-1: Maximum-Sub-Array:
 * Naive Loesung (brute-force) mit O(n^3).
 * 
 * @see <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/ss08/ad2-t/aufgaben/blatt6/">Aufgabenblatt 6</A>
 * @author <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/">Arne Johannessen</A>
 * @version $Revision: 1.3 $
 */
class Loesung61 {
	
	
	
	/**
	 * Loest das Maximum-Sub-Array--Problem fuer den uebergebenen
	 * Array auf naive Weise. Es werden saemtliche moeglichen
	 * Teilfolgen errechnet, deren Summe bestimmt und diese dann
	 * miteinander verglichen.
	 * 
	 * @param array der fuer die Bestimmung der Problemloesung
	 *  heranzuziehende Gesamt-Array
	 * @return Wert der groessten ermittelten Teilsumme
	 */
	int findMaximumSum (int[] array) {
		
		int maximumSum = Integer.MIN_VALUE;  // -2147483648
		
		// alle moeglichen Sub-Arrays durchlaufen
		for (int lower = 0; lower < array.length; lower++) {
			for (int upper = 0; upper < array.length; upper++) {
				
				// von jedem die Summe berechnen
				int sum = 0;
				for (int i = lower; i <= upper; i++) {
					sum += array[i];
				}
				
				// falls die Summe bisher die hoechste ist, merken
				if (sum >= maximumSum) {
					maximumSum = sum;
				}
			}
		}
		
		// Ergebnis ausgeben
		return maximumSum;
	}
	
}
