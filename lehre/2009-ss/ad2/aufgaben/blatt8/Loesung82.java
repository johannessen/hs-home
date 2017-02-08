/* 
 * $Id: Loesung82.java 2009-06-22 $
 * Arne Johannessen
 */


class Loesung82 {
	
	
	/**
	 * Methode zur Loesung der Aufgabe 8-2.
	 * Errechnet die Summe der Elemente des rechten Randmaximums eines
	 * Arrays (also desjenigen Sub-Arrays, dessen letztes Element mit dem
	 * letzten Element dieses Arrays identisch ist und dessen Summe der
	 * Elemente so gross wie moeglich ist).
	 */
	static int rechtesRandmaximum (int[] array) {
		
		// Array von rechts nach links durchlaufen und Maximum bestimmen
		int maximaleSumme = 0;
		int summe = 0;
		for (int i = array.length - 1; i >= 0; i--) {
			summe += array[i];
			if (summe > maximaleSumme) {
				maximaleSumme = summe;
			}
		}
		
		return maximaleSumme;
	}
	
	
	// nur zum Testen, nicht Teil der Aufgabenstellung
	public static void main (String[] args) {
		System.out.println( rechtesRandmaximum(new int[] {2, -1, 8, -16, 4}) );  // 4 <= 2 -1 8 -16 [4]
		System.out.println( rechtesRandmaximum(new int[] {1, 8, 0, -4, -32, 2, 16}) );  // 18 <= 1 8 0 -4 -32 [2 16]
		System.out.println( rechtesRandmaximum(new int[] {8, -1, 0, -2, -4, 8}) );  // 9 <= [8 -1 0 -2 -4 8]
		System.out.println( rechtesRandmaximum(new int[] {-2, -1, -4}) );  // 0 <= -2 -1 -4 []
		System.out.println( rechtesRandmaximum(new int[0]) );  // 0 <= []
	}
	
}
