/* $Id: Loesung31c.java,v 1.2 2007-10-28 22:50:20 arne Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */



/**
 * Loesungsvorschlaege fuer Aufgabe 3-1c.
 * <p>
 * Diese Klasse enthaelt vier verschiedene Beispiele fuer moegliche
 * Loesungsansaetze. Traditionell ist die Toggle-Loesung die bevozugte,
 * weil sie in allen imperativen Programmiersprachen (von Assembler bis
 * einschliesslich Java) sehr einfach zu implementieren ist.
 * In objektorientierten Sprachen wie Java ist der moderne Ansatz mit
 * Ausnahmenbehandlung meist ebenfalls einfach zu implementieren und
 * mindestens gleichwertig. Diese beiden Methoden sind empfehlenswert
 * fuer dieses Problem.
 * <p>
 * Waehrend die rekursive Loesung durch ihre unuebertroffene
 * Einfachheit besticht, ist ihr praktischer Nutzen in Java eingeschraenkt
 * (siehe Aufgabe 3-4). Der Ansatz mit Modulo-Formel ist vergleichsweise
 * schwer verstaendlich und sollte daher nur Anwendung finden, wenn aus
 * irgendeinem Grund die anderen Methoden nicht praktikabel sind.
 * 
 * @see <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/ws07/ad2-t/aufgaben/blatt3/">Aufgabenblatt 3</A>
 * @author <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/">Arne Johannessen</A>
 * @version $Revision: 1.2 $
 */
public class Loesung31c extends ArraySum {
	
	
	
	/**
	 * Loesungsvorschlag fuer Aufgabe 3-1c mit Anwendung einer
	 * Modulo-Formel.
	 * <p>
	 * Berechnet die "alternierende Summe" aller Elemente eines
	 * Arrays. Dazu wird das jeweils richtige Vorzeichen fuer jedes
	 * Array-Element anhand einer Modulo-basierten Formel errechnet
	 * und dann mit jedem Summanden multipliziert.
	 * 
	 * @param array das aufzusummierende Array
	 * @return die alternierende Summe von <code>array</code>
	 * @throws NullObjectException falls <code>array == null</code>
	 */
	public static int alternatingSumModulo (int[] array) {
		int sum = 0;
		int sign = 0;
		for (int index = 0; index < array.length; index++) {
			sign = 1 - (index % 2) * 2;
			sum += sign * array[index];
		}
		return sum;
	}
	
	
	
	/**
	 * Loesungsvorschlag fuer Aufgabe 3-1c mit Toggle (Umschalten).
	 * <p>
	 * Berechnet die "alternierende Summe" aller Elemente eines
	 * Arrays. Dazu wird das jeweils richtige Vorzeichen in Form eines
	 * Boolean-Flags gespeichert und bei jeder Iteration umgeschaltet.
	 * 
	 * @param array das aufzusummierende Array
	 * @return die alternierende Summe von <code>array</code>
	 * @throws NullObjectException falls <code>array == null</code>
	 */
	public static int alternatingSumToggle (int[] array) {
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
	 * Rekursiver Loesungsvorschlag fuer Aufgabe 3-1c. Berechnet
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
	 * Elementsersten
	 * @return die alternierende Summe aller Elemente
	 * von <code>array</code> ab <code>index</code>;
	 * <code>0</code>, falls <code>index >= array.length</code>
	 * @throws NullObjectException falls <code>array == null</code>
	 */
	public static int alternatingSumRecursion (int[] array, int index) {
		if (index >= array.length) {
			return 0;
		}
		return array[index] - alternatingSumRecursion(array, index + 1);
	}
	
	
	
	/**
	 * Loesungsvorschlag fuer Aufgabe 3-1c mit Ausnahmenbehandlung.
	 * <p>
	 * Berechnet die "alternierende Summe" aller Elemente eines
	 * Arrays. Dazu werden bei jedem Iterationsschritt zwei
	 * Array-Elemente als Additions-Subtraktions-Paar gemeinsam
	 * behandelt. Anstelle einer Abbruchbedingung wird auf das
	 * Auftreten einer Ausnahme beim Array-Ueberlauf gewartet.
	 * 
	 * @param array das aufzusummierende Array
	 * @return die alternierende Summe von <code>array</code>
	 * @throws NullObjectException falls <code>array == null</code>
	 */
	public static int alternatingSumException (int[] array) {
		int sum = 0;
		try {
			for (int index = 0; ; ) {
				sum += array[index++];
				sum -= array[index++];
			}
		}
		catch (ArrayIndexOutOfBoundsException exception) {
			// Array wurde bis zum Ende durchlaufen
		}
		return sum;
	}
	
	
	
	/** Treiber fuer Aufruf von der Kommandozeilenschnittstelle. */
	public static void main (String[] args) {
		int[] array = ARRAY;
		printArray(array);
		System.out.println("Alternierende Summe der Elemente");
		System.out.println("\tmit Modulo: "+alternatingSumModulo(array));
		System.out.println("\tmit Umschalten: "+alternatingSumToggle(array));
		System.out.println("\tmit Rekursion: "+alternatingSumRecursion(array, 0));
		System.out.println("\tmit Ausnahme: "+alternatingSumException(array));
	}
	
}
