/* 
 * $Id: Loesung83.java 2009-06-22 $
 * Arne Johannessen
 */


class Loesung83 {
	
	
	/**
	 * Methode zur Loesung der Aufgabe 8-3.
	 * Loest das Maximum-Sub-Array--Problem fuer den uebergebenen Array im
	 * Scanline-Verfahren mit dem Algorithmus von Kadane. Bei einem einzigen
	 * Durchlauf des Arrays wird eine Teilfolge mit der hoechsten Summe
	 * ermittelt.
	 */
	static int maximumSubarray (int[] array) {
		
		int maximaleSumme = 0;
		int summe = 0;
		
		// Gesamt-Array genau einmal durchlaufen
		for (int i = 0; i < array.length; i++) {
			
			// laufende Summe fortfuehren
			summe += array[i];
			
			// negative Summe kann nie maximal werden, daher nullen
			if (summe < 0) {
				summe = 0;
			}
			
			// aktuelle laufende Summe merken, falls bisher hoechste
			if (summe > maximaleSumme) {
				maximaleSumme = summe;
			}
		}
		
		// Ergebnis ausgeben
		return maximaleSumme;
	}
	
	
	// nur zum Testen, nicht Teil der Aufgabenstellung
	public static void main (String[] args) {
		System.out.println( maximumSubarray(new int[] {2, -4, 8, -1}) );  // 8 <= 2 -4 [8] -1
		System.out.println( maximumSubarray(new int[] {2, -1, 8, -16, 4}) );  // 9 <= [2 -1 8] -16 4
		System.out.println( maximumSubarray(new int[] {1, 8, 0, -4, -32, 2, 16}) );  // 18 <= 1 8 0 -4 -32 [2 16]
		System.out.println( maximumSubarray(new int[] {16, 8, -32, 4, 64, -2, 0, 1}) );  // 68 <= 16 8 -32 [4 64] -2 0 1
		System.out.println( maximumSubarray(new int[] {8, -1, 0, -2, -4, 8}) );  // 9 <= [8 -1 0 -2 -4 8]
		System.out.println( maximumSubarray(new int[] {-2, -1, -4}) );  // 0 <= -2 -1 -4 []
		System.out.println( maximumSubarray(new int[] {1, -2}) );  // 1 <= [1] -2
		System.out.println( maximumSubarray(new int[] {1}) );  // 1 <= [1]
		System.out.println( maximumSubarray(new int[0]) );  // 0 <= []
	}
	
}
