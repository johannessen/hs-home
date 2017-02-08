/* 
 * $Id: Loesung64.java 2009-06-12 $
 * Arne Johannessen
 */


class Loesung64 {
	
	
	/**
	 * Methode zur Loesung der Aufgabe 6-4.
	 * Errechnet die groesste Summe der Elemente aller Sub-Arrays eines
	 * Arrays im naiven Verfahren.
	 */
	static int maximumSubarray (int[] array) {
		
		int maximaleSumme = 0;
		
		// alle moeglichen Sub-Arrays durchlaufen
		for (int anfang = 0; anfang < array.length; anfang++) {
			for (int ende = 0; ende < array.length; ende++) {
				
				// von jedem die Summe berechnen
				int summe = 0;
				for (int i = anfang; i <= ende; i++) {
					summe += array[i];
				}
				
				// falls die Summe bisher die hoechste ist, merken
				if (summe > maximaleSumme) {
					maximaleSumme = summe;
				}
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
