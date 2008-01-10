/* $Id: Loesung91.java,v 1.1 2008-01-10 01:51:35 arne Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */


/**
 * Loesungsvorschlag fuer Aufgabe 9-1: Bubble-Sort.
 * 
 * @see <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/ws07/ad2-t/aufgaben/blatt9/">Aufgabenblatt 9</A>
 * @author <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/">Arne Johannessen</A>
 * @version $Revision: 1.1 $
 */
public class Loesung91 {
	
	
	/**
	 * Sortiert einen Array aufsteigend. Verwendet wird der
	 * Bubble-Sort-Algorithmus.
	 * 
	 * @param array das zu sortierende Array
	 * @throws NullPointerException falls <code>array == null</code>
	 */
	public static void sort (int[] array) {
		
		// zur Geschwindigkeitsoptimierung achten
		// wir auf die Zahl der Vertauschungen:
		boolean swap = true;
		while (swap) {
			
			swap = false;
			for (int index = 0; index < array.length - 1; index++) {
				if (array[index] > array[index + 1]) {
					
					// vertausche array[index] mit array[index + 1]
					int temp = array[index];
					array[index] = array[index + 1];
					array[index + 1] = temp;
					swap = true;
				}
			}
			
		}
	}
	
	
	
	/** Treiber fuer Aufruf von der Kommandozeilenschnittstelle. */
	public static void main (String[] args) {
		int[] array = new int[] {7, 4, 6, 9, 1, -1, 2, 3, 8, 0, 6, 5};
		sort(array);
		
		for (int index = 0; index < array.length; index++) {
			System.out.print(array[index]+" ");
		}
		System.out.println();
	}
	
}
