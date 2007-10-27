/* $Id: Loesung13.java,v 1.3 2007-10-27 20:00:33 arne Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */


/**
 * Loesungsvorschlag fuer Aufgabe 1-3. Naive Loesung (brute-force)
 * fuer das Maximum-Sub-Array--Problem mit O(n^3)-Effizienz.
 * @see <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/ws07/ad2-t/aufgaben/blatt1/">Aufgabenblatt 1</A>
 * @author <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/">Arne Johannessen</A>
 * @version $Revision: 1.3 $
 */
public class Loesung13 implements MaximumSubArraySolver {
	
	
	
	/**
	 * Loest das Maximum-Sub-Array--Problem fuer den uebergebenen
	 * Array auf naive Weise. Es werden saemtliche moeglichen
	 * Teilfolgen errechnet, deren Summe bestimmt und diese dann
	 * miteinander verglichen. Fuer eine Teilfolge mit der hoechsten
	 * Summe wird eine entsprechende <code>SubArray</code>-Instanz
	 * zurueckgeliefert.
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
		int maximumStartIndex = 0;
		int maximumLength = 0;
		
		// alle moeglichen Sub-Arrays durchlaufen
		for (int lowerIndex = 0; lowerIndex < array.length; lowerIndex++) {
			for (int upperIndex = 0; upperIndex < array.length; upperIndex++) {
				
				// von jedem die Summe berechnen
				int sum = 0;
				for (int index = lowerIndex; index < upperIndex + 1; index++) {
					sum += array[index];
				}
				
				// falls die Summe bisher die hoechste ist, merken
				if (sum >= maximumSum) {
					maximumSum = sum;
					maximumStartIndex = lowerIndex;
					maximumLength = upperIndex - lowerIndex + 1;
				}
			}
		}
		
		// als Ergebnis das SubArray-Objekt erstellen
		SubArray maximumSubArray = new SubArray(array);
		maximumSubArray.setStart(maximumStartIndex);
		maximumSubArray.setLength(maximumLength);
		return maximumSubArray;
	}
	
	
	
	/** Treiber fuer Aufruf von der Kommandozeilenschnittstelle. */
	public static void main (String[] args) {
		int[] array = null;
		SubArray maximumSubArray = null;
		
		array = SubArray.createRandomArray();
		maximumSubArray = new Loesung13().findMaximumSubArray(array);
		System.out.println(maximumSubArray);
		System.out.println("Summe: ["+maximumSubArray.sum()+"]");
	}
	
}
