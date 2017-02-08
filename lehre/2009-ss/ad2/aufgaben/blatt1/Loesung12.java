/* 
 * $Id: Loesung12.java 2009-04-27 $
 * Arne Johannessen
 */


class Loesung12 {
	
	/**
	 * Methode zur Loesung der Aufgabe 1-2.
	 * Sucht die Zahl 'value' im Array 'array' ab der Stelle 'index'.
	 * Rekursive Loesung.
	 */
	static int sequentialSearch (int[] array, int value, int index) {
		
		// Abbruchbedingung Fehlerfall
		if (index >= array.length) {
			// array[index] waere illegal => nicht gefunden
			return -1;
		}
		
		// Abbruchbedingung Erfolgsfall
		if (array[index] == value) {
			// gefunden!
			return index;
		}
		
		// Rekursion:
		// suche weiter ab der naechsten Stelle
		return sequentialSearch(array, value, index + 1);
		
	}
	
	
	/**
	 * main-Methode als Treiber fuer den Aufruf von der Kommandozeile.
	 * Ruft obige Methode mit Testwerten auf.
	 */
	public static void main (String[] args) {
		int[] array = new int[] {5, -8, 12, 1, 42, -2, 2, 9};
		System.out.println( sequentialSearch(array, -2, 0) );  // 5
		System.out.println( sequentialSearch(array, 7, 0) );  // -1
		System.out.println( sequentialSearch(array, 5, 100) );  // -1
	}
	
}
