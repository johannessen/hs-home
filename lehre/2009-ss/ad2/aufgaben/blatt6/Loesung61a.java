/* 
 * $Id: Loesung61a.java 2009-05-27 $
 * Arne Johannessen
 */


class Loesung61a {
	
	
	// Array als Klassenvariable (vgl. Aufgabenstellung)
	static int[] array;
	
	
	/**
	 * Methode als Vorschlag zur Loesung der Teilaufgabe 6-1 (a).
	 * Fuegt einen Wert an einer bestimmten Stelle in den Array ein.
	 */
	static void insertAt (int value, int index) {
		
		// neuen Array in passender Groesse erzeugen
		int[] array2 = new int[array.length + 1];
		
		// neuen Array fuellen
		for (int i = 0; i < index; i++) {
			array2[i] = array[i];
		}
		array2[index] = value;
		for (int i = index; i < array.length; i++) {
			array2[i + 1] = array[i];
		}
		
		// alten Array durch Referenz auf neuen Array ersetzen
		array = array2;
	}
	
	
	/**
	 * main-Methode als Treiber fuer den Aufruf von der Kommandozeile.
	 * Erzeugt einen Array und ruft testweise obige Methode auf.
	 */
	public static void main (String[] args) {
		array = new int[] {3, 5, 7, 9, 11, 13};
		
		System.out.print("Vorher:  ");
		Loesung05.printArray(array);
		
		insertAt(8, 3);
		insertAt(2, 0);
		insertAt(14, array.length);
		
		System.out.print("Nachher: ");
		Loesung05.printArray(array);
	}
	
}
