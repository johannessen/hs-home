/* $Id: LoesungE4.java,v 1.2 2008/06/04 19:51:14 aj3 Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */



/**
 * Loesungsvorschlaege fuer Aufgabe E-4: Alternierende Summe.
 * <p>
 * Diese Klasse enthaelt vier verschiedene Beispiele fuer moegliche
 * Loesungsansaetze. Traditionell ist die Toggle-Loesung die bevorzugte,
 * weil sie in allen imperativen Programmiersprachen (von Assembler bis
 * einschliesslich Java) sehr einfach zu implementieren ist. Diese
 * Methode ist die beste Loesung fuer dieses Problem.
 * <p>
 * Waehrend die rekursive Loesung durch ihre unuebertroffene
 * Einfachheit besticht, ist ihr praktischer Nutzen in Java eingeschraenkt
 * (siehe Aufgabe 3-4). Der Ansatz mit Modulo-Formel ist vergleichsweise
 * schwer verstaendlich und sollte daher nur Anwendung finden, wenn aus
 * irgendeinem Grund die anderen Methoden nicht praktikabel sind. Beide
 * Methoden sind langsamer als die Toggle-Loesung.
 * <p>
 * (Obwohl in objektorientierten Sprachen wie Java eine weitere Loesung
 * mit Ausnahmenbehandlung moeglich ist, sollte sie niemals verwendet
 * werden. Sie ist naemlich zwar einfach zu schreiben, aber schwer zu
 * lesen; ausserdem ist er sehr langsam. Naeheres erklaert Josh Bloch in
 * Effective Java auf Seite 169 ff.; vgl. Java Puzzlers Puzzle 42, S. 89.)
 * 
 * @see <A HREF="http://java.sun.com/docs/books/effective/" HREFLANG="en">Effective Java</A>
 * @see <A HREF="http://www.javapuzzlers.com/" HREFLANG="en">Java Puzzlers</A>
 * 
 * @see <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/ss08/ad2-t/aufgaben/blatt4/">Aufgabenblatt 4</A>
 * @author <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/">Arne Johannessen</A>
 * @version $Revision: 1.2 $
 */
class LoesungE4 {
	
	
	
	/**
	 * Loesungsvorschlag fuer Aufgabe E-4 mit Anwendung einer
	 * Modulo-Formel.
	 * <p>
	 * Berechnet die "alternierende Summe" aller Elemente eines
	 * Arrays. Dazu wird das jeweils richtige Vorzeichen fuer jedes
	 * Array-Element anhand einer Modulo-basierten Formel errechnet
	 * und dann mit jedem Summanden multipliziert.
	 * 
	 * @param array das aufzusummierende Array
	 * @return die alternierende Summe von <code>array</code>
	 * @throws NullPointerException falls <code>array == null</code>
	 */
	int alternatingSumModulo (int[] array) {
		int sum = 0;
		int sign = 0;
		for (int index = 0; index < array.length; index++) {
			sign = 1 - (index % 2) * 2;
			sum += sign * array[index];
		}
		return sum;
	}
	
	
	
	/**
	 * Loesungsvorschlag fuer Aufgabe E-4 mit Toggle (Umschalten).
	 * <p>
	 * Berechnet die "alternierende Summe" aller Elemente eines
	 * Arrays. Dazu wird das jeweils richtige Vorzeichen in Form eines
	 * Boolean-Flags gespeichert und bei jeder Iteration umgeschaltet.
	 * 
	 * @param array das aufzusummierende Array
	 * @return die alternierende Summe von <code>array</code>
	 * @throws NullPointerException falls <code>array == null</code>
	 */
	int alternatingSumToggle (int[] array) {
		int sum = 0;
		boolean plus = true;
		for (int index = 0; index < array.length; index++) {
			if (plus) {
				sum += array[index];
			}
			else {
				sum -= array[index];
			}
			plus = ! plus;  // Toggle-Flag umschalten
		}
		return sum;
	}
	
	
	
	/**
	 * Rekursiver Loesungsvorschlag fuer Aufgabe E-4. Berechnet
	 * rekursiv die "alternierende Summe" aller Elemente eines Arrays
	 * von einem bestimmten Element an.
	 * <p>
	 * Das durch <code>index</code> angegebene Element wird addiert
	 * und die durch die Rekursion gelieferte Summe des Rest-Arrays
	 * subtrahiert. Durch das Prinzip der Invertierbarkeit ("minus und
	 * minus gibt plus") ergibt sich direkt die alternierende Summe.
	 * 
	 * @param array das aufzusummierende Array
	 * @param index der Index des ersten aufzusummierenden
	 *  Elementsersten
	 * @return die alternierende Summe aller Elemente
	 *  von <code>array</code> ab <code>index</code>;
	 *  <code>0</code>, falls <code>index >= array.length</code>
	 * @throws NullPointerException falls <code>array == null</code>
	 */
	int alternatingSumRecursion (int[] array, int index) {
		if (index >= array.length) {
			return 0;
		}
		return array[index] - alternatingSumRecursion(array, index + 1);
	}
	
}
