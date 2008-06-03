/* $Id: Loesung62c.java,v 1.1 2008-06-03 23:54:14 aj3 Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */


/**
 * Loesungsvorschlag fuer Aufgabe 6-2c. Halb-naive Loesung fuer
 * das Maximum-Sub-Array--Problem mit O(n^2)-Effizienz.
 * 
 * @see <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/ss08/ad2-t/aufgaben/blatt6/">Aufgabenblatt 6</A>
 * @author <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/">Arne Johannessen</A>
 * @version $Revision: 1.1 $
 */
class Loesung62c {
	
	
	
	/**
	 * Loest das Maximum-Sub-Array--Problem fuer den uebergebenen
	 * Array auf naive Weise. Es werden saemtliche moeglichen
	 * Teilfolgen errechnet und gleichzeitig deren Summe bestimmt.
	 * 
	 * @param array der fuer die Bestimmung der Problemloesung
	 *  heranzuziehende Gesamt-Array
	 * @return Wert der groessten errechneten Teilsumme
	 * @throws NullPointerException falls <code>array == null</code>
	 */
	int findMaximumSubArray (int[] array) {
		
		int maximumSum = Integer.MIN_VALUE;  // -2147483648
		
		// alle moeglichen Sub-Arrays durchlaufen und deren Summe berechnen
		for (int lower = 0; lower < array.length; lower++) {
			int sum = 0;
			for (int upper = lower; upper < array.length; upper++) {
				sum += array[upper];
				
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
