/* $Id: Loesung63.java,v 1.3 2008-06-04 19:51:14 aj3 Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */


/**
 * Loesungsvorschlag fuer Aufgabe 6-3: Maximum-Sub-Array:
 * Programm zur naiven Loesung.
 * <p>
 * Dieses Programm ist aus zwei Gruenden voellig untauglich fuer alle
 * praktischen Zwecke: Erstens ist der Array "hart gecoded", d. h. fest
 * eincompiliert und nicht zur Laufzeit aenderbar; eigentlich muesste der
 * Array ueber die Kommandozeilenparamater o. ae. eingelesen werden.
 * Zweitens ist das Ergebnis keine eindeutige Definition des Sub-Arrays;
 * eigentlich muesste statt der Summe der Beginn-Index und die Anzahl der
 * Elemente des Sub-Arrays ausgegeben werden. Auf beides wurde aus
 * Zeitgruenden verzichtet.
 * 
 * @see <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/ss08/ad2-t/aufgaben/blatt6/">Aufgabenblatt 6</A>
 * @author <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/">Arne Johannessen</A>
 * @version $Revision: 1.3 $
 */
class Loesung63 {
	
	
	
	/** Treiber fuer Aufruf von der Kommandozeilenschnittstelle. */
	public static void main (String[] args) {
		int[] myArray = new int[] {-8, 12, -23, 17, 42, -61, 7};
		Loesung61 instance = new Loesung61();
		int maximumSum = instance.findMaximumSum(myArray);
		System.out.println("Maximale Teilsumme: "+maximumSum);  // 59
	}
	
}
