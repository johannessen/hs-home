/* 
 * $Id: Loesung54.java 2009-05-23 $
 * Arne Johannessen
 */


class Loesung54 extends Menge {
	
	
	/**
	 * Methode zur Loesung der Aufgabe 5-3.
	 * Sortiert die Menge.
	 */
	void collectionsSort () {
		java.util.Arrays.sort(menge);  // Quicksort: O(n log n)
	}
	
	
	Loesung54 (int[] menge) {
		super(menge);  // Konstruktor erben
	}
	
	
	/**
	 * main-Methode zum Testen von der Kommandozeile aus.
	 */
	public static void main (String[] args) {
		int[] array = new int[] {9, 5, -2, 12, 815, 1, 42, -9, 2, 0};
		Loesung54 meineMenge = new Loesung54(array);
		
		meineMenge.collectionsSort();
		
		Loesung05.printArray(meineMenge.menge);
	}
	
}
