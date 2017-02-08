/* $Id: Loesung71.java,v 1.2 2008/06/04 19:52:19 aj3 Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */


/**
 * Loesungsvorschlag fuer Aufgabe 7-1: Maximum-Sub-Array:
 * Scan-Line--Loesung mit O(n).
 * 
 * @see <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/ss08/ad2-t/aufgaben/blatt7/">Aufgabenblatt 7</A>
 * @author <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/">Arne Johannessen</A>
 * @version $Revision: 1.2 $
 */
class Loesung71 {
	
	
	
	/**
	 * Loest das Maximum-Sub-Array--Problem fuer den uebergebenen
	 * Array im Scan-Line--Verfahren. Bei einem einzigen Durchlauf
	 * des Arrays wird eine Teilfolge mit der hoechsten Summe
	 * ermittelt.
	 * 
	 * @param array der fuer die Bestimmung der Problemloesung
	 *  heranzuziehende Gesamt-Array
	 * @return Wert der groessten ermittelten Teilsumme
	 */
	int findMaximumSum (int[] array) {
		
		// lokale Variablen initialisieren
		int sum = 0;
		int maximumSum = 0;
		
		// Gesamt-Array genau einmal durchlaufen
		for (int i = 0; i < array.length; i++) {
			
			// laufende Summe fortfuehren
			sum += array[i];
			
			// negative Summe kann nie maximal werden, daher nullen
			if (sum < 0) {
				sum = 0;
			}
			
			// aktuelle laufende Summe merken, falls bisher hoechste
			if (sum > maximumSum) {
				maximumSum = sum;
			}
		}
		
		// Ergebnis ausgeben
		return maximumSum;
	}
	
}
