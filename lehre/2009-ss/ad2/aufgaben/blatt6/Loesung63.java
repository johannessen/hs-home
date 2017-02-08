/* 
 * $Id: Loesung63.java 2009-05-27 $
 * Arne Johannessen
 */


class TeilArray {
	
	/** Referenz auf den Gesamt-Array. */
	int[] gesamtarray;
	
	/** Index des ersten Elements im Sub-Array. */
	int anfang;
	
	/** Anzahl der Elemente im Sub-Array. */
	int ende;
	
	
	/**
	 * Methode als Vorschlag zur Loesung der Aufgabe 6-3.
	 * Berechnet die Summe der Elemente dieses Sub-Arrays.
	 */
	int summe () {
		int summe = 0;
		for (int i = anfang; i <= ende; i++) {
			summe += gesamtarray[i];
		}
		return summe;
	}
	
}



class Beispiel63 {
	
	// nur zur Veranschaulichung, nicht Teil der Aufgabenstellung
	public static void main (String[] args) {
		
		// Gesamt-Array erzeugen
		int[] array = new int[] {9, 5, -2, 12, 815, 1, 42, -9, 2, 0};
		
		// Sub-Array definieren: [5, -2, 12]
		TeilArray subarray = new TeilArray();
		subarray.gesamtarray = array;
		subarray.anfang = 1;
		subarray.ende = 3;
		
		// Summe berechnen
		System.out.println( subarray.summe() );  // 15
	}
	
}
