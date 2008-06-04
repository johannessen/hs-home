/* $Id: Loesung51.java,v 1.3 2008-06-04 19:51:14 aj3 Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */


/**
 * Loesungsvorschlag fuer Aufgabe 5-1: Binaere Suche.
 * 
 * @see java.util.Arrays#binarySearch(int[],int)
 * @see <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/ss08/ad2-t/aufgaben/blatt5/">Aufgabenblatt 5</A>
 * @author <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/">Arne Johannessen</A>
 * @version $Revision: 1.3 $
 */
class Loesung51 {
	
	
	/**
	 * Durchsucht einen Array binaer. Zurueckgeliefert wird der
	 * Index einer Fundstelle.
	 * <p>
	 * Fuer eine binaere Suche <b>muss</b> der Array sortiert sein.
	 * 
	 * @param array das zu durchsuchende Array
	 * @param key den zu suchenden Wert
	 * @return den Index desjenigen Elements in <code>array</code>, das
	 *  den Wert <code>key</code> hat
	 * @throws KeyNotFoundException falls der Array den gesuchten Wert
	 *  nicht enthaelt
	 * @throws NullPointerException falls <code>array == null</code>
	 * @see java.util.Arrays#binarySearch(int[],int)
	 * @see java.util.Arrays#sort(int[])
	 */
	int find (int[] array, int key) {
		
		// Suchanfang: ganzen Array durchsuchen
		int firstIndex = 0;
		int lastIndex = array.length - 1;
		
		// Suche starten
		return find(array, key, firstIndex, lastIndex);
	}
	
	
	
	/**
	 * Durchsucht einen Teil eines Arrays binaer. Zurueckgeliefert
	 * wird der Index einer Fundstelle.
	 * <p>
	 * Fuer eine binaere Suche <b>muss</b> der Array sortiert sein.
	 * 
	 * @param array das zu durchsuchende Array
	 * @param key den zu suchenden Wert
	 * @param leftIndex der Index, der die untere Grenze des zu
	 *  durchsuchenden Bereichs im Array darstellt (einschliesslich)
	 * @param rightIndex der Index, der die obere Grenze des zu
	 *  durchsuchenden Bereichs im Array darstellt (einschliesslich)
	 * @return den Index desjenigen Elements in <code>array</code>, das
	 *  den Wert <code>key</code> hat
	 * @throws KeyNotFoundException falls der Array den gesuchten Wert
	 *  nicht enthaelt
	 * @throws NullPointerException falls <code>array == null</code>
	 * @see java.util.Arrays#binarySearch(int[],int)
	 * @see java.util.Arrays#sort(int[])
	 */
	int find (int[] array, int key, int leftIndex, int rightIndex) {
		
		// Rekursionsabbruch bei leerem Teil-Array
		if (leftIndex > rightIndex) {
			throw new KeyNotFoundException();
			
			// (man koennte auch -1 zurueckgeben)
		}
		
		// "mittleren Index m" berechnen
		int centerIndex = (leftIndex + rightIndex) / 2;
		
		// rekursive binaere Suche
		if (key < array[centerIndex]) {
			return find(array, key, leftIndex, centerIndex - 1);
		}
		else if (key > array[centerIndex]) {
			return find(array, key, centerIndex + 1, rightIndex);
		}
		
		// an diesem Punkt ist die Suche erfolgreich
		// (key == array[centerIndex])
		return centerIndex;
	}
	
}
