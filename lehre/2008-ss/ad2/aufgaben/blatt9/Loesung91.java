/* $Id: Loesung91.java,v 1.1 2008/06/04 14:30:37 aj3 Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */


/**
 * Loesungsvorschlag fuer Aufgabe 9-1: Bubble-Sort.
 * 
 * @see <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/ss08/ad2-t/aufgaben/blatt9/">Aufgabenblatt 9</A>
 * @author <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/">Arne Johannessen</A>
 * @version $Revision: 1.1 $
 */
class Loesung91 {
	
	
	/**
	 * Sortiert einen Array aufsteigend. Verwendet wird der
	 * Bubble-Sort-Algorithmus.
	 * 
	 * @param array das zu sortierende Array
	 * @throws NullPointerException falls <code>array == null</code>
	 */
	void sort (int[] array) {
		
		// zur Geschwindigkeitsoptimierung achten
		// wir auf die Zahl der Vertauschungen:
		boolean swap = true;
		while (swap) {
			
			swap = false;
			for (int i = 0; i < array.length - 1; i++) {
				if (array[i] > array[i + 1]) {
					
					// vertausche array[index] mit array[index + 1]
					int temp = array[i];
					array[i] = array[i + 1];
					array[i + 1] = temp;
					swap = true;
				}
			}
			
		}
	}
	
}
