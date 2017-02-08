/* $Id: Loesung72.java,v 1.2 2008/06/04 19:52:19 aj3 Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */


/**
 * Loesungsvorschlag fuer Aufgabe 7-2: Maximum-Sub-Array:
 * Programm zur Scan-Line--Loesung.
 * 
 * @see <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/ss08/ad2-t/aufgaben/blatt7/">Aufgabenblatt 7</A>
 * @author <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/">Arne Johannessen</A>
 * @version $Revision: 1.2 $
 */
class Loesung72 {
	
	
	
	/** Treiber fuer Aufruf von der Kommandozeilenschnittstelle. */
	public static void main (String[] args) {
		int[] myArray = new int[] {-8, 12, -23, 17, 42, -61, 7};
		Loesung71 instance = new Loesung71();
		int maximumSum = instance.findMaximumSum(myArray);
		System.out.println("Maximale Teilsumme: "+maximumSum);  // 59
	}
	
}
