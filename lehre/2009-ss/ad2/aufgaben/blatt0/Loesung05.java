/* 
 * $Id: Loesung05.java 2009-04-20 $
 * Arne Johannessen
 */


class Loesung05 {
	
	/**
	 * Methode zur Loesung der Aufgabe 0-5.
	 * Gibt den Inhalt des uebergebenen Arrays aus.
	 */
	static void printArray (int[] array) {
		
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]);
			System.out.print(" ");
		}
		
		// Zeilenumbruch, um Ausgabe abzuschliessen
		System.out.println();
		
	}
	
	
	/**
	 * main-Methode als Treiber fuer den Aufruf von der Kommandozeile.
	 * Ruft testweise obige Methode mit einem Array auf.
	 */
	public static void main (String[] args) {
		int[] arrayAusAufgabe04 = new int[] {5, -8, 12, 1, 42, -2, 2, 9};
		printArray(arrayAusAufgabe04);
	}
	
}
