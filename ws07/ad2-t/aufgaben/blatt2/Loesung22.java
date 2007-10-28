/* $Id: Loesung22.java,v 1.4 2007-10-28 22:41:22 arne Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */


/**
 * Loesungsvorschlag fuer Aufgabe 2-2. Scan-Line--Loesung fuer
 * das Maximum-Sub-Array--Problem mit O(n)-Effizienz.
 * 
 * @see <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/ws07/ad2-t/aufgaben/blatt2/">Aufgabenblatt 2</A>
 * @author <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/">Arne Johannessen</A>
 * @version $Revision: 1.4 $
 */
public class Loesung22 implements MaximumSubArraySolver {
	
	
	
	/**
	 * Loest das Maximum-Sub-Array--Problem fuer den uebergebenen
	 * Array im Scan-Line--Verfahren. Bei einem einzigen Durchlauf
	 * des Arrays wird eine Teilfolge mit der hoechsten Summe
	 * ermittelt. Fuer diese wird eine entsprechende
	 * <code>SubArray</code>-Instanz zurueckgeliefert.
	 * 
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
		int currentSum = 0;
		int currentStart = 0;
		int currentLength = 0;
		int maximumSum = 0;
		int maximumStart = 0;
		int maximumLength = 0;
		
		// Gesamt-Array genau einmal durchlaufen
		for (int index = 0; index < array.length; index++) {
			
			// laufende Summe fortfuehren
			currentSum += array[index];
			currentLength++;
			
			// ist aktuelle laufende Summe negativ?
			if (currentSum < 0) {
				// laufende Summe auf null setzen
				currentSum = 0;
				// mit naechstem Element weitermachen
				currentStart = index + 1;
				currentLength = 0;
				continue;
			}
			
			// aktuelle laufende Summe merken, falls bisher hoechste
			if (currentSum > maximumSum) {
				maximumSum = currentSum;
				maximumStart = currentStart;
				maximumLength = currentLength;
			}
		}
		
		// Ergebnis zurueckgeben
		return new SubArray(array, maximumStart, maximumLength);
	}
	
	
	
	/** Treiber fuer Aufruf von der Kommandozeilenschnittstelle. */
	public static void main (String[] args) {
		int[] array = null;
		SubArray maximumSubArray = null;
		
		array = SubArray.createRandomArray();
		maximumSubArray = new Loesung22().findMaximumSubArray(array);
		System.out.println(maximumSubArray);
		System.out.println("Summe: ["+maximumSubArray.getSum()+"]");
	}
	
}
