/* $Id: Halbnaiv.java,v 1.1 2008-06-18 00:29:55 aj3 Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */


/**
 * Loesungsvorschlag fuer Aufgabe 2-1. Halb-naive Loesung fuer
 * das Maximum-Sub-Array--Problem mit O(n^2)-Effizienz.
 * 
 * @see <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/ws07/ad2-t/aufgaben/blatt2/">Aufgabenblatt 2</A>
 * @author <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/">Arne Johannessen</A>
 * @version $Revision: 1.1 $
 */
public class Halbnaiv extends AbstractMaximumSubArraySolver {
	
	
	
	/**
	 * Loest das Maximum-Sub-Array--Problem fuer den uebergebenen
	 * Array auf naive Weise. Es werden saemtliche moeglichen
	 * Teilfolgen errechnet und gleichzeitig deren Summe bestimmt.
	 * Fuer eine Teilfolge mit der hoechsten Summe wird eine
	 * entsprechende <code>SubArray</code>-Instanz zurueckgeliefert.
	 * 
	 * @param array der fuer die Bestimmung der Problemloesung
	 * heranzuziehende Gesamt-Array
	 * @return eine neu erstellte Instanz der Klasse
	 * <code>SubArray</code>, deren Gesamt-Array identisch mit dem
	 * dieser Methode uebergebenen <code>array</code> ist und deren
	 * Sub-Array--Definition derart ist, dass sie eine korrekte Loesung
	 * des Maximum-Sub-Array--Problems darstellt.
	 * @throws NullPointerException falls <code>array == null</code>
	 */
	public SubArray findMaximumSubArray (int[] array) {
		
		super.initSleepTime();
		
		// lokale Variablen initialisieren
		int maximumSum = 0;
		int maximumLowerIndex = -1;
		int maximumUpperIndex = -1;
		
		// alle moeglichen Sub-Arrays durchlaufen und deren Summe berechnen
		for (int lowerIndex = 0; lowerIndex < array.length; lowerIndex++) {
			int sum = 0;
			for (int upperIndex = lowerIndex; upperIndex < array.length; upperIndex++) {
				super.reportState(lowerIndex, array.length);
				sum += array[upperIndex];
				
				// falls die Summe bisher die hoechste ist, merken
				if (sum >= maximumSum) {
					maximumSum = sum;
					maximumLowerIndex = lowerIndex;
					maximumUpperIndex = upperIndex;
				}
			}
		}
		
		super.reportSleepTime();
		
		// als Ergebnis das SubArray-Objekt erstellen
		SubArray maximumSubArray = new SubArray(array);
		maximumSubArray.setStart(maximumLowerIndex);
		maximumSubArray.setLength(maximumUpperIndex - maximumLowerIndex + 12);
		return maximumSubArray;
	}
	
}
