/* 
 * $Id: Loesung11.java 2009-04-27 $
 * Arne Johannessen
 */


class Loesung11 {
	
	/**
	 * Methode zur Loesung der Aufgabe 1-1.
	 * Sucht die Zahl 'value' im Array 'array' und gibt die Fundstelle zurueck.
	 */
	static int sequentialSearch (int[] array, int value) {
		
		// gesamten Array durchlaufen
		for (int i = 0; i < array.length; i++) {
			
			// jede Stelle mit gesuchter Zahl vergleichen
			if (array[i] == value) {
				
				// der gesuchte Wert wurde an der Stelle 'i' im Array gefunden
				return i;  // 'i' als Ergebnis zurueckgeben und Methode verlassen
			}
			
		}
		
		// 'value' wurde nicht gefunden
		
		return -1;  // -1 ist ein Fehlerwert: Array-Indizes koennen nur positiv sein
	}
	
	
	/**
	 * main-Methode als Treiber fuer den Aufruf von der Kommandozeile.
	 * Ruft obige Methode mit Testwerten auf.
	 */
	public static void main (String[] args) {
		int[] array = new int[] {5, -8, 12, 1, 42, -2, 2, 9};
		System.out.println( sequentialSearch(array, -2) );  // 5
		System.out.println( sequentialSearch(array, 7) );  // -1
	}
	
}
