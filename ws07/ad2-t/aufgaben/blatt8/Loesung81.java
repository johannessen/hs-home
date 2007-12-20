/* $Id: Loesung81.java,v 1.2 2007-12-20 15:23:46 arne Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */


/**
 * Loesungsvorschlag fuer Aufgabe 8-1.
 * 
 * @see <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/ws07/ad2-t/aufgaben/blatt8/">Aufgabenblatt 8</A>
 * @author <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/">Arne Johannessen</A>
 * @version $Revision: 1.2 $
 */
public class Loesung81 {
	
	
	/**
	 * Durchsucht einen Array im Interpolations-Verfahren.
	 * Zurueckgeliefert wird der Index einer Fundstelle.
	 * <p>
	 * Fuer diese Suche <b>muss</b> der Array sortiert sein.
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
		
		// Suchanfang: ganzen Array durchsuchen
		int firstIndex = 0;
		int lastIndex = array.length - 1;
		
		// Suche starten
		return find(array, key, firstIndex, lastIndex);
	}
	
	
	
	/**
	 * Durchsucht einen Teil eines Array im Interpolations-Verfahren.
	 * Zurueckgeliefert wird der Index einer Fundstelle.
	 * <p>
	 * Fuer diese Suche <b>muss</b> der Array sortiert sein.
	 * 
	 * @param array das zu durchsuchende Array
	 * @param key den zu suchenden Wert
	 * @param leftIndex der Index, der die untere Grenze des zu
	 * durchsuchenden Bereichs im Array darstellt (einschliesslich)
	 * @param rightIndex der Index, der die obere Grenze des zu
	 * durchsuchenden Bereichs im Array darstellt (einschliesslich)
	 * @return den Index desjenigen Elements in <code>array</code>, das
	 * den Wert <code>key</code> hat
	 * @throws IndexOutOfBoundsException falls der Array den gesuchten
	 * Wert nicht enthaelt
	 * @throws NullPointerException falls <code>array == null</code>
	 * @see java.util.Arrays#sort(int[])
	 */
	protected static int find (int[] array, int key, int leftIndex, int rightIndex) {
		
		// Rekursionsabbruch bei leerem Teil-Array
		if (leftIndex > rightIndex) {
			throw new KeyNotFoundException();
		}
		
		// Interpolations-Formel fuer "m" (pivotIndex) anwenden:
		int indexRange = rightIndex - leftIndex;
		int valueRange = array[rightIndex] - array[leftIndex];
		int pivotIndex = leftIndex + ((key - array[leftIndex]) * indexRange / valueRange);
		
		// rekursive binaere Suche
		if (key < array[pivotIndex]) {
			return find(array, key, leftIndex, pivotIndex - 1);
		}
		else if (key > array[pivotIndex]) {
			return find(array, key, pivotIndex + 1, rightIndex);
		}
		
		// an diesem Punkt ist die Suche erfolgreich
		// (key == array[pivotIndex])
		return pivotIndex;
	}
	
	
	
	/**
	 * Treiber fuer Aufruf von der Kommandozeilenschnittstelle.
	 * 
	 * @see java.util.Arrays#sort(int[])
	 */
	public static void main (String[] args) {
		// bei der Interpolationssuche MUSS der Array sortiert sein!
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
