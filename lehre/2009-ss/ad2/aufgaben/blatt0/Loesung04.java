/* 
 * $Id: Loesung04.java 2009-04-20 $
 * Arne Johannessen
 */


class Loesung04 {
	
	/**
	 * Methode zur Loesung der Aufgabe 0-4.
	 * Gibt den Inhalt des eingebauten Arrays aus.
	 */
	static void printArray () {
		
		// aus Aufgabenstellung:
		int[] array = new int[] {5, -8, 12, 1, 42, -2, 2, 9};
		
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]);
			System.out.print(" ");
		}
		
		// Zeilenumbruch, um Ausgabe abzuschliessen
		System.out.println();
		
	}
	
	
	/**
	 * main-Methode als Treiber fuer den Aufruf von der Kommandozeile.
	 * Ruft testweise obige Methode auf.
	 */
	public static void main (String[] args) {
		printArray();
	}
	
}
