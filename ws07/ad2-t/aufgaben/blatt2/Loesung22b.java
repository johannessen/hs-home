/* $Id: Loesung22b.java,v 1.1 2007-11-12 22:09:23 arne Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */


/**
 * Loesungsvorschlag fuer Aufgabe 2-2b. Scan-Line--Loesung fuer
 * das Maximum-Sub-Array--Problem mit O(n)-Effizienz.
 * 
 * @see <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/ws07/ad2-t/aufgaben/blatt2/">Aufgabenblatt 2</A>
 * @author <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/">Arne Johannessen</A>
 * @version $Revision: 1.1 $
 */
public class Loesung22b {
	
	
	
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
			}
		}
		
		// Ergebnis ausgeben
		System.out.println("Maximale Teilsumme: "+maximumSum);  // 59
		System.out.println("(Beginn bei Element mit Index "+maximumBegin+", Ende bei "+maximumEnd+")");  // 3 bzw. 4
		System.out.println("Laenge der Teilfolge mit maximaler Teilsumme: "+(maximumEnd - maximumBegin + 1));  // 2
	}
	
	
	
	/** Treiber fuer Aufruf von der Kommandozeilenschnittstelle. */
	public static void main (String[] args) {
		int[] meinArray = {-8, 12, -23, 17, 42, -61, 7};
		findeLoesung(meinArray);
	}
	
}
