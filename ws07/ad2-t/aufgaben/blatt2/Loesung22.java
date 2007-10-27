/* $Id: Loesung22.java,v 1.2 2007-10-27 20:00:31 arne Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */


/**
 * Loesungsvorschlag fuer Aufgabe 2-2. Scan-Line--Loesung fuer
 * das Maximum-Sub-Array--Problem mit O(n)-Effizienz.
 * @see <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/ws07/ad2-t/aufgaben/blatt2/">Aufgabenblatt 2</A>
 * @author <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/">Arne Johannessen</A>
 * @version $Revision: 1.2 $
 */
public class Loesung22 implements MaximumSubArraySolver {
	
	
	
	/**
	 * Loest das Maximum-Sub-Array--Problem fuer den uebergebenen
	 * Array im Scan-Line--Verfahren. Bei einem einzigen Durchlauf
	 * des Arrays wird eine Teilfolge mit der hoechsten Summe
	 * ermittelt. Fuer diese wird eine entsprechende
	 * <code>SubArray</code>-Instanz zurueckgeliefert.
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
		int currentStart = 0;
		int currentLength = 0;
		int currentSum = 0;
		SubArray maximum = new SubArray(array, 0, 0);
		int maximumSum = 0;
		
		// Gesamt-Array genau einmal durchlaufen
		for (int index = 0; index < array.length; index++) {
			currentSum += array[index];
			
			// ist aktuelle laufende Summe zu klein?
			if (currentSum < 0) {
				// ja: laufende Summe nullen
				currentSum = 0;
				currentStart = index + 1;
				currentLength = 0;
			}
			else {
				// nein: laufende Summe fortfuehren
				currentLength++;
			}
			
			// aktuelle laufende Summe merken, falls bisher hoechste
			if (currentSum > maximumSum) {
				maximumSum = currentSum;
				maximum.setStart(currentStart);
				maximum.setLength(currentLength);
			}
		}
		
		// Ergebnis zurueckgeben
		return maximum;
	}
	
	
	
	/** Treiber fuer Aufruf von der Kommandozeilenschnittstelle. */
	public static void main (String[] args) {
		int[] array = null;
		SubArray maximumSubArray = null;
		
		array = SubArray.createRandomArray();
		maximumSubArray = new Loesung22().findMaximumSubArray(array);
		System.out.println(maximumSubArray);
		System.out.println("Summe: ["+maximumSubArray.sum()+"]");
	}
	
}
