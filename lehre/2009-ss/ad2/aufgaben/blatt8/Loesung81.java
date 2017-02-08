/* 
 * $Id: Loesung81.java 2009-06-22 $
 * Arne Johannessen
 */


class Loesung81 {
	
	
	/**
	 * Methode zur Loesung der Aufgabe 8-1.
	 * Errechnet die Summe der Elemente des linken Randmaximums eines
	 * Arrays (also desjenigen Sub-Arrays, dessen erstes Element mit dem
	 * ersten Element dieses Arrays identisch ist und dessen Summe der
	 * Elemente so gross wie moeglich ist).
	 */
	static int linkesRandmaximum (int[] array) {
		
		// Array von links nach rechts durchlaufen und Maximum bestimmen
		int maximaleSumme = 0;
		int summe = 0;
		for (int i = 0; i < array.length; i++) {
			summe += array[i];
			if (summe > maximaleSumme) {
				maximaleSumme = summe;
			}
		}
		
		return maximaleSumme;
	}
	
	
	// nur zum Testen, nicht Teil der Aufgabenstellung
	public static void main (String[] args) {
		System.out.println( linkesRandmaximum(new int[] {2, -1, 8, -16, 4}) );  // 9 <= [2 -1 8] -16 4
		System.out.println( linkesRandmaximum(new int[] {1, 8, 0, -4, -32, 2, 16}) );  // 9 <= [1 8] 0 -4 -32 2 16
		System.out.println( linkesRandmaximum(new int[] {8, -1, 0, -2, -4, 8}) );  // 9 <= [8 -1 0 -2 -4 8]
		System.out.println( linkesRandmaximum(new int[] {-2, -1, -4}) );  // 0 <= [] -2 -1 -4
		System.out.println( linkesRandmaximum(new int[0]) );  // 0 <= []
	}
	
}
