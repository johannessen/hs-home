/* 
 * $Id: Loesung24.java 2009-04-27 $
 * Arne Johannessen
 */


class Loesung24 {
	
	/**
	 * Methode zur Loesung der Aufgabe 2-4.
	 * Prueft rekursiv, ob ein Array aufsteigend sortiert ist.
	 * Die Pruefung beginnt bei 'index'.
	 */
	static boolean istSortiert (int[] array, int index) {
		
		/* Gegeben: Array A (mit n Elementen)
		 *          Stelle i, ab der geprueft werden soll (anfangs 1)
		 * 
		 * Start
		 *     falls i oder i+1 ausserhalb der Array-Grenzen liegen:
		 *         gib zurueck "der Array ist sortiert"
		 *     falls A(i) nicht kleiner oder gleich A(i+1):
		 *         gib zurueck "der Array ist nicht sortiert"
		 *     sonst:
		 *         pruefe, ob der Array-Rest von A(i+1) bis A(n) sortiert ist
		 * Stop
		 */
		
		// bitte vergleichen mit Loesungsvorschlag zu 2-3!
		
		if (index + 1 >= array.length) {
			return true;
		}
		if (array[index] > array[index + 1]) {
			return false;
		}
		
		return istSortiert(array, index + 1);
	}
	
	
	/**
	 * Prueft rekursiv, ob ein Array aufsteigend sortiert ist.
	 */
	static boolean istSortiert (int[] array) {
		return istSortiert(array, 0);  // Anfangswert fuer 'index'
	}
	
	
	/**
	 * main-Methode als Treiber fuer den Aufruf von der Kommandozeile.
	 * Ruft obige Methode mit Testwerten auf.
	 */
	public static void main (String[] args) {
		System.out.println( istSortiert(new int[] {1, 3, 7}) );  // true
		System.out.println( istSortiert(new int[] {5, -8, 12}) );  // false
		System.out.println( istSortiert(new int[] {0, 0, 0}) );  // true
		System.out.println( istSortiert(new int[] {0}) );  // true
		System.out.println( istSortiert(new int[] {}) );  // true
	}
	
}
