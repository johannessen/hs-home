/* 
 * $Id: Loesung23.java 2009-04-27 $
 * Arne Johannessen
 */


class Loesung23 {
	
	/**
	 * Methode zur Loesung der Aufgabe 2-3.
	 * Prueft iterativ, ob ein Array aufsteigend sortiert ist.
	 */
	static boolean istSortiert (int[] array) {
		
		/* Gegeben: Array A (mit n Elementen)
		 * 
		 * Start
		 *     fuer i von 2 bis n:
		 *         falls A(i-1) nicht kleiner oder gleich A(i):
		 *             der Array ist nicht sortiert
		 *     andernfalls ist der Array sortiert
		 * Stop
		 */
		
		for (int i = 1; i < array.length; i++) {
			if (array[i - 1] > array[i]) {
				// der Array ist nicht sortiert
				return false;
			}
		}
		
		// der Array ist sortiert
		return true;
		
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
