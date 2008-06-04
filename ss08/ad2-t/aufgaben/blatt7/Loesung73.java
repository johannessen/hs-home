/* $Id: Loesung73.java,v 1.1 2008-06-04 14:29:41 aj3 Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */


/**
 * Loesungsvorschlag fuer Aufgabe 7-3. Naive Loesung (brute-force)
 * fuer das Maximum-Sub-Array--Problem mit O(n^3)-Effizienz.
 * <p>
 * Diese Klasse ist wiederverwendbar: Statt der maximalen Teilsumme
 * wird hier tatsaechlich der maximale Sub-Array ermittelt.
 * 
 * @see <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/ss08/ad2-t/aufgaben/blatt7/">Aufgabenblatt 7</A>
 * @author <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/">Arne Johannessen</A>
 * @version $Revision: 1.1 $
 */
class Loesung73 {
	
	
	
	/**
	 * Loest das Maximum-Sub-Array--Problem fuer den uebergebenen
	 * Array auf naive Weise. Es werden saemtliche moeglichen
	 * Teilfolgen errechnet, deren Summe bestimmt und diese dann
	 * miteinander verglichen.
	 * <p>
	 * Im Gegensatz zu anderen Methoden wird hier statt der
	 * maximalen Teilsumme hier tatsaechlich der maximale
	 * Sub-Array ermittelt und zurueckgegeben.
	 * 
	 * @param array der fuer die Bestimmung der Problemloesung
	 *  heranzuziehende Gesamt-Array
	 * @return eine neu erstellte Instanz der Klasse
	 *  <code>SubArray</code>, deren Gesamt-Array identisch mit dem
	 *  dieser Methode uebergebenen <code>array</code> ist und deren
	 *  Sub-Array--Definition derart ist, dass sie eine korrekte Loesung
	 *  des Maximum-Sub-Array--Problems darstellt.
	 * @throws NullPointerException falls <code>array == null</code>
	 */
	SubArray findMaximumSubArray (int[] array) {
		
		int maximumSum = Integer.MIN_VALUE;  // -2147483648
		int maximumLower = -1;
		int maximumUpper = -1;
		
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
					maximumLower = lower;
					maximumUpper = upper;
				}
			}
		}
		
		// als Ergebnis das SubArray-Objekt erstellen
		SubArray maximumSubArray = new SubArray(array);
		maximumSubArray.setStart(maximumLower);
		maximumSubArray.setLength(maximumUpper - maximumLower + 1);
		return maximumSubArray;
	}
	
}
