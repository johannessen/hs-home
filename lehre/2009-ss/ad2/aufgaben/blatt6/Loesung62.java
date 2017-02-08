/* 
 * $Id: Loesung62.java 2009-05-27 $
 * Arne Johannessen
 */


class TeilArray {
	
	/** Referenz auf den Gesamt-Array. */
	int[] gesamtarray;
	
	/** Index des ersten Elements im Sub-Array. */
	int anfang;
	
	/** Index des letzten Elements im Sub-Array. */
	int ende;
	
	/* Ein Sub-Array ist eindeutig definiert durch einen Bezug auf den
	 * gesamten Array und die Grenzen des Sub-Arrays (Indizes). Die Grenzen
	 * koennen wahlweise durch Anfangs- und Endindex oder aber durch
	 * Startindex und Laenge festgelegt werden.
	 */
	
}



class Beispiel62 {
	
	// nur zur Veranschaulichung, nicht Teil der Aufgabenstellung
	public static void main (String[] args) {
		
		// Gesamt-Array erzeugen
		int[] array = new int[] {9, 5, -2, 12, 815, 1, 42, -9, 2, 0};
		
		// Sub-Array definieren: [5, -2, 12]
		TeilArray subarray = new TeilArray();
		subarray.gesamtarray = array;
		subarray.anfang = 1;
		subarray.ende = 3;
		
		// Summe berechnen (vgl. Aufgabe 6-3)
//		System.out.println( subarray.summe() );  // 15
	}
	
}
