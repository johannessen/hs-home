/* $Id: Loesung21.java,v 1.1 2007-10-21 03:05:14 arne Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */


/**
 * Loesungsvorschlag fuer Aufgabe 2-1. Halb-naive Loesung fuer
 * das Maximum-Sub-Array--Problem mit O(n^2)-Effizienz.
 * @author <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/">Arne Johannessen</A>
 */
public class Loesung21 implements MaximumSubArraySolver {
	
	
	/**
	 * Loest das Maximum-Sub-Array--Problem fuer den uebergebenen
	 * Array auf naive Weise. Es werden saemtliche moeglichen
	 * Teilfolgen errechnet und gleichzeitig deren Summe bestimmt.
	 * Fuer eine Teilfolge mit der hoechsten Summe wird eine
	 * entsprechende <code>SubArray</code>-Instanz zurueckgeliefert.
	 * @param array der fuer die Bestimmung der Problemloesung
	 * heranzuziehende Gesamt-Array
	 * @return eine neu erstellte Instanz der Klasse
	 * <code>SubArray</code>, deren Gesamt-Array identisch mit dem
	 * dieser Methode uebergebenen <code>array</code> ist und deren
	 * Sub-Array--Definition derart ist, dass sie eine korrekte Loesung
	 * des Maximum-Sub-Array--Problems darstellt.
	 * @throws NullObjectException falls <code>array == null</code>
	 */
	public SubArray findMaximumSubArray (int[] array) {
		
		// lokale Variablen initialisieren
		int maximumSum = 0;
		int minimumLowerIndex = 0;
		int maximumUpperIndex = -1;
		
		// alle moeglichen Sub-Arrays durchlaufen und deren Summe berechnen
		for (int lowerIndex = 0; lowerIndex < array.length; lowerIndex++) {
			int sum = 0;
			for (int upperIndex = lowerIndex; upperIndex < array.length; upperIndex++) {
				sum += array[upperIndex];
				
				// falls die Summe bisher die hoechste ist, merken
				if (sum >= maximumSum) {
					maximumSum = sum;
					minimumLowerIndex = lowerIndex;
					maximumUpperIndex = upperIndex;
				}
			}
		}
		
		// als Ergebnis das SubArray-Objekt erstellen
		SubArray maximumSubArray = new SubArray(array);
		maximumSubArray.setStart(minimumLowerIndex);
		maximumSubArray.setLength(maximumUpperIndex - minimumLowerIndex + 1);
		return maximumSubArray;
	}
	
	
	
	/** Treiber fuer Aufruf von der Kommandozeilenschnittstelle. */
	public static void main (String[] args) {
		int[] array = null;
		SubArray maximumSubArray = null;
		
		array = SubArray.createRandomArray();
		maximumSubArray = new Loesung21().findMaximumSubArray(array);
		System.out.println(maximumSubArray);
		System.out.println("Summe: ["+maximumSubArray.sum()+"]");
	}
	
}
