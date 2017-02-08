/* $Id: Loesung22a.java,v 1.1 2007/11/12 22:09:23 arne Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */


/**
 * Loesungsvorschlag fuer Aufgabe 2-2a. Scan-Line--Loesung fuer
 * das Maximum-Sub-Array--Problem mit O(n)-Effizienz.
 * 
 * @see <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/ws07/ad2-t/aufgaben/blatt2/">Aufgabenblatt 2</A>
 * @author <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/">Arne Johannessen</A>
 * @version $Revision: 1.1 $
 */
public class Loesung22a {
	
	
	
	/**
	 * Loest das Maximum-Sub-Array--Problem fuer den uebergebenen
	 * Array im Scan-Line--Verfahren. Bei einem einzigen Durchlauf
	 * des Arrays wird eine Teilfolge mit der hoechsten Summe
	 * ermittelt. Der Wert dieser Summe wird auf dem
	 * Standard-Ausgabe-Stream ausgegeben.
	 * 
	 * @param array der fuer die Bestimmung der Problemloesung
	 * heranzuziehende Gesamt-Array
	 */
	public static void findeLoesung (int[] array) {
		
		// lokale Variablen initialisieren
		int sum = 0;
		int maximumSum = 0;
		
		// Gesamt-Array genau einmal durchlaufen
		for (int index = 0; index < array.length; index++) {
			
			// laufende Summe fortfuehren
			sum += array[index];
			
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
		System.out.println("Maximale Teilsumme: "+maximumSum);  // 59
	}
	
	
	
	/** Treiber fuer Aufruf von der Kommandozeilenschnittstelle. */
	public static void main (String[] args) {
		int[] meinArray = {-8, 12, -23, 17, 42, -61, 7};
		findeLoesung(meinArray);
	}
	
}
