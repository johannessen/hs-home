/* $Id: Scanline.java,v 1.1 2008-06-18 00:29:55 aj3 Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */


/**
 * Loesungsvorschlag fuer Aufgabe 2-2c. Scan-Line--Loesung fuer
 * das Maximum-Sub-Array--Problem mit O(n)-Effizienz.
 * 
 * @see <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/ws07/ad2-t/aufgaben/blatt2/">Aufgabenblatt 2</A>
 * @author <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/">Arne Johannessen</A>
 * @version $Revision: 1.1 $
 */
public class Scanline extends AbstractMaximumSubArraySolver {
	
	
	
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
	 * @throws NullPointerException falls <code>array == null</code>
	 */
	public SubArray findMaximumSubArray (int[] array) {
		
		super.initSleepTime();
		
		// lokale Variablen initialisieren
		int sum = 0;
		int maximumSum = 0;
		int beginIndex = 0;  // Startannahme: Teilfolge beginnt bei 0
		int maximumBegin = -1;
		int maximumEnd = -1;
		
		// Gesamt-Array genau einmal durchlaufen
		for (int index = 0; index < array.length; index++) {
			
			// laufende Summe fortfuehren
			sum += array[index];
			
			// negative Summe kann nie maximal werden, daher nullen
			if (sum < 0) {
				sum = 0;
				beginIndex = index + 1;
			}
			
			// aktuelle laufende Summe merken, falls bisher hoechste
			if (sum > maximumSum) {
				maximumSum = sum;
				maximumBegin = beginIndex;
				maximumEnd = index;
				
				super.reportState(index, array.length / 5);  // Schaetzwert
			}
		}
		
		super.reportSleepTime();
		
		// Ergebnis zurueckgeben
		return new SubArray(array, maximumBegin, maximumEnd - maximumBegin + 1);
	}
	
}
