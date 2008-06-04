/* $Id: Loesung82.java,v 1.1 2008-06-04 14:30:13 aj3 Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */


/**
 * Loesungsvorschlag fuer Aufgabe 8-2. Programm zur
 * Divide-and-Conquer--Loesung fuer das
 * Maximum-Sub-Array--Problem mit O(n*log(n))-Komplexitaet.
 * 
 * @see <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/ss08/ad2-t/aufgaben/blatt8/">Aufgabenblatt 8</A>
 * @author <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/">Arne Johannessen</A>
 * @version $Revision: 1.1 $
 */
class Loesung82 {
	
	
	
	/** Treiber fuer Aufruf von der Kommandozeilenschnittstelle. */
	public static void main (String[] args) {
		int[] myArray = new int[] {-8, 12, -23, 17, 42, -61, 7};
		Loesung81 instance = new Loesung81();
		int maximumSum = instance.findMaximumSum(myArray);
		System.out.println("Maximale Teilsumme: "+maximumSum);  // 59
	}
	
}
