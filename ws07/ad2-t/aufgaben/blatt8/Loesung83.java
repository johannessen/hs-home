/* $Id: Loesung83.java,v 1.1 2007-12-20 15:23:15 arne Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */


/**
 * Loesungsvorschlag fuer Aufgabe 8-3. Fibonaccisuche.
 * 
 * @see <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/ws07/ad2-t/aufgaben/blatt8/">Aufgabenblatt 8</A>
 * @author <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/">Arne Johannessen</A>
 * @version $Revision: 1.1 $
 */
public class Loesung83 {
	
	
	/**
	 * Die Fibonaccifolge <i>F</i>, angepasst fuer die Fibonaccisuche.
	 * Diese Folge enthaelt alle Fibonaccizahlen in <code>int</code>;
	 * dies sind alle Fibonaccizahlen kleiner oder gleich
	 * <i>F</i><sub>47</sub> = 2971215073.
	 * <p>
	 * Im Allgemeinen ist die Fibonaccifolge wie folgt definiert:
	 * <br><code>FIBONACCI[n] = (n == 0 ? 0 : (n == 1 ? 1 :
	 * (FIBONACCI[n - 2] + FIBONACCI[n - 1])))</code>
	 * <p>
	 * Um klaren Code in der Rekursion zu haben, wird abweichend davon
	 * <code>FIBONACCI[0] = 1</code> definiert; dies spart eine eigene
	 * <code>if</code>-Bedingung fuer sehr kurze Teil-Arrays ein. Auch
	 * wird <code>FIBONACCI[48] = Integer.MAX_VALUE</code> definiert,
	 * um die Korrektheit des Algorithmus auch bei langen Arrays
	 * herzustellen.
	 */
	public static final int[] FIBONACCI = new int[] {1, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946, 17711, 28657, 46368, 75025, 121393, 196418, 317811, 514229, 832040, 1346269, 2178309, 3524578, 5702887, 9227465, 14930352, 24157817, 39088169, 63245986, 102334155, 165580141, 267914296, 433494437, 701408733, 1134903170, 1836311903, Integer.MAX_VALUE};
	
	
	/**
	 * Durchsucht einen Array mit der Fibonaccisuche.
	 * Zurueckgeliefert wird der Index einer Fundstelle.
	 * <p>
	 * Fuer eine Fibonaccisuche <b>muss</b> der Array sortiert sein.
	 * 
	 * @param array das zu durchsuchende Array
	 * @param key den zu suchenden Wert
	 * @return den Index desjenigen Elements in <code>array</code>, das
	 * den Wert <code>key</code> hat
	 * @throws KeyNotFoundException falls der Array den gesuchten Wert
	 * nicht enthaelt
	 * @throws NullPointerException falls <code>array == null</code>
	 * @see java.util.Arrays#sort(int[])
	 */
	public static int find (int[] array, int key) {
		
		// ein leerer Array fuehrt zu einer ArrayIndexOutOfBoundsException
		if (array.length == 0) {
			throw new KeyNotFoundException();
		}
		
		/* Wir brauchen nun die kleinste Fibonaccizahl groesser
		 * oder gleich der Laenge unseres Arrays. Die Laenge des
		 * Teilarrays von leftIndex bis rightIndex muss genau diese
		 * Fibonaccizahl sein. Da die Fibonaccifolge monoton
		 * steigend ist, waere eigentlich eine binaere Suche
		 * optimal. Bis zu einer Arraylaenge von einigen
		 * zehntausend Elementen ist aber eine sequentielle Suche
		 * nicht viel langsamer. 
		 */
		
		// Suchanfang: die richtigen Startwerte finden
		int fibonacciIndex = 2;
		while (fibonacciIndex < FIBONACCI.length && FIBONACCI[fibonacciIndex] < array.length) {
			fibonacciIndex++;
		}
		int firstIndex = 0;
		int lastIndex = FIBONACCI[fibonacciIndex] - 1;
		
		// Suche starten
		return find(array, key, firstIndex, lastIndex, fibonacciIndex);
	}
	
	
	
	/**
	 * Durchsucht einen Array mit der Fibonaccisuche.
	 * Zurueckgeliefert wird der Index einer Fundstelle.
	 * <p>
	 * Fuer eine Fibonaccisuche <b>muss</b> der Array sortiert sein.
	 * <p>
	 * Damit diese Methode korrekt ist, muessen beim Aufruf folgende
	 * Vorbedingungen erfuellt sein: <ul>
	 * <li><code>array</code> enthaelt den Suchraum, mit
	 * <code>array.length > 0</code></li>
	 * <li><code>array.length</code> > <code>leftIndex</code> >=
	 * <code>0</code></li>
	 * <li><code>rightIndex - leftIndex + 1 ==
	 * FIBONACCI[fibonacciIndex]</code></li>
	 * <li><code>FIBONACCI</code> enthaelt die Fibonaccifolge, mit
	 * <code>FIBONACCI[0] == 1</code></li>
	 * </ul>
	 * 
	 * @param array das zu durchsuchende Array
	 * @param key den zu suchenden Wert
	 * @param leftIndex der Index, der die untere Grenze des zu
	 *  durchsuchenden Bereichs im Array darstellt (einschliesslich)
	 * @param rightIndex der Index, der die obere Grenze des zu
	 *  durchsuchenden Bereichs im Array darstellt (einschliesslich);
	 *  der Abstand zu <code>leftIndex</code> muss in jedem Fall eine
	 *  Fibonaccizahl sein
	 * @param fibonacciIndex der Index derjenigen Fibonaccizahl, die
	 *  dem Abstand zwischen <code>leftIndex</code> und
	 *  <code>rightIndex</code> entspricht
	 * @return den Index desjenigen Elements in <code>array</code>, das
	 *  den Wert <code>key</code> hat
	 * @throws KeyNotFoundException falls der Array den gesuchten Wert
	 *  nicht enthaelt
	 * @throws ArrayIndexOutOfBoundsException falls
	 *  <code>array.length == 0</code>
	 * @throws NullPointerException falls <code>array == null</code>
	 * @see java.util.Arrays#sort(int[])
	 */
	protected static int find (int[] array, int key, int leftIndex, int rightIndex, int fibonacciIndex) {
		
		/* Grundannahme: die Array-Laenge ist genau eine
		 * Fibonaccizahl; dann setzt die Array-Laenge sich
		 * zusammen aus der Summe aus den beiden vorangehenden
		 * Fibonaccizahlen. Diese Zahlen stellen die beiden
		 * Haelften dar. Da alle Fibonaccizahlen per Definition
		 * aus zwei Fibonaccizahlen zusammengesetzt sind, ist eine
		 * rekursive Loesung moeglich. Allerdings muss auch noch
		 * ein Element in der Naehe der Mitte zur Entscheidung
		 * betrachtet werden -- aufgrund der Summenformel muss der
		 * Split trennscharf geschehen (vgl. MSA).
		 */
		
		// Rekursionsabbruch, wenn keine Fibonacci-Zahlen
		// zur weiteren Teilung mehr zur Verfuegung stehen
		if (fibonacciIndex < 1) {
			throw new KeyNotFoundException();
		}
		
		// "mittlere Position m" berechnen
		int pivotIndex = rightIndex - FIBONACCI[fibonacciIndex - 1];
		
		// rekursive binaere Suche
		if (key < array[pivotIndex]) {
			return find(array, key, leftIndex, pivotIndex, fibonacciIndex - 2);
		}
		else if (key > array[pivotIndex]) {
			return find(array, key, pivotIndex + 1, rightIndex, fibonacciIndex - 1);
		}
		
		// an diesem Punkt ist die Suche erfolgreich
		// (key == pivotValue)
		return pivotIndex;
	}
	
	
	
	/**
	 * Treiber fuer Aufruf von der Kommandozeilenschnittstelle.
	 */
	public static void main (String[] args) {
		// bei der Fibonaccisuche MUSS der Array sortiert sein!
		int[] array = new int[] {-1, 0, 1, 2, 3, 4, 5, 6, 6, 7, 8, 9};
		
		int valueToBeFound = 3;
		try {
			int index = find(array, valueToBeFound);
			System.out.println("Die Zahl "+valueToBeFound+" ist im Array an der Stelle "+index+".");
		}
		catch (IndexOutOfBoundsException exception) {
			System.out.println("Die Zahl "+valueToBeFound+" ist nicht im Array enthalten.");
		}
	}
	
}
