/* $Id: Loesung13a.java,v 1.1 2007-11-12 10:00:14 arne Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */


/**
 * Loesungsvorschlag fuer Aufgabe 1-3a. Naive Loesung (brute-force)
 * fuer das Maximum-Sub-Array--Problem mit O(n^3)-Effizienz.
 * 
 * @see <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/ws07/ad2-t/aufgaben/blatt1/">Aufgabenblatt 1</A>
 * @author <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/">Arne Johannessen</A>
 * @version $Revision: 1.1 $
 */
public class Loesung13a {
	
	
	
	/**
	 * Loest das Maximum-Sub-Array--Problem fuer den uebergebenen
	 * Array auf naive Weise. Es werden saemtliche moeglichen
	 * Teilfolgen errechnet, deren Summe bestimmt und diese dann
	 * miteinander verglichen. Der Wert der Summe der hoechsten
	 * Teilfolge wird auf dem Standard-Ausgabe-Stream ausgegeben.
	 * 
	 * @param array der fuer die Bestimmung der Problemloesung
	 * heranzuziehende Gesamt-Array
	 */
	public static void findeLoesung (int[] array) {
		
		// lokale Variablen initialisieren
		int[] A = array;
		int n = array.length;
		int mts = Integer.MIN_VALUE;  // -2147483648
		
		// alle moeglichen Sub-Arrays durchlaufen
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				
				// von jedem die Summe berechnen
				int ts = 0;
				for (int k = i; k <= j; k++) {
					ts = ts + A[k];
				}
				
				// falls die Summe bisher die hoechste ist, merken
				if (ts >= mts) {
					mts = ts;
				}
			}
		}
		
		// Ergebnis ausgeben
		System.out.println("Maximale Teilsumme: "+mts);  // 59
	}
	
	
	
	/** Treiber fuer Aufruf von der Kommandozeilenschnittstelle. */
	public static void main (String[] args) {
		int[] meinArray = {-8, 12, -23, 17, 42, -61, 7};
		findeLoesung(meinArray);
	}
	
}
