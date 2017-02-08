/* 
 * $Id: Loesung73.java 2009-06-12 $
 * Arne Johannessen
 */


class Loesung73 {
	
	
	/**
	 * Methode zur Loesung der Aufgabe 7-3.
	 * Bestimmt einen Sub-Array mit maximaler Summe seiner Elemente
	 * im "halb-naiven" Verfahren.
	 */
	static TeilArray maximumSubarray (int[] array) {
		
		int subarrayAnfang = 0;
		int subarrayEnde = -1;
		int maximaleSumme = 0;
		
		// alle moeglichen Sub-Arrays durchlaufen und deren Summe berechnen
		for (int anfang = 0; anfang < array.length; anfang++) {
			int summe = 0;
			for (int ende = anfang; ende < array.length; ende++) {
				summe += array[ende];
				
				// falls die Summe bisher die hoechste ist, merken
				if (summe >= maximaleSumme) {
					maximaleSumme = summe;
					subarrayAnfang = anfang;
					subarrayEnde = ende;
				}
			}
		}
		
		// Ergebnis ausgeben
		TeilArray subarray = new TeilArray();
		subarray.anfang = subarrayAnfang;
		subarray.ende = subarrayEnde;
		subarray.gesamtarray = array;
		return subarray;
	}
	
	
	// nur zum Testen, nicht Teil der Aufgabenstellung
	public static void main (String[] args) {
		printSubarray( maximumSubarray(new int[] {2, -4, 8, -1}) );  // 8 <= 2 -4 [8] -1
		printSubarray( maximumSubarray(new int[] {2, -1, 8, -16, 4}) );  // 9 <= [2 -1 8] -16 4
		printSubarray( maximumSubarray(new int[] {1, 8, 0, -4, -32, 2, 16}) );  // 18 <= 1 8 0 -4 -32 [2 16]
		printSubarray( maximumSubarray(new int[] {16, 8, -32, 4, 64, -2, 0, 1}) );  // 68 <= 16 8 -32 [4 64] -2 0 1
		printSubarray( maximumSubarray(new int[] {8, -1, 0, -2, -4, 8}) );  // 9 <= [8 -1 0 -2 -4 8]
		printSubarray( maximumSubarray(new int[] {-2, -1, -4}) );  // 0 <= -2 -1 -4 []
		printSubarray( maximumSubarray(new int[] {1, -2}) );  // 1 <= [1] -2
		printSubarray( maximumSubarray(new int[] {1}) );  // 1 <= [1]
		printSubarray( maximumSubarray(new int[0]) );  // 0 <= []
	}
	
	
	// nur zum Testen, nicht Teil der Aufgabenstellung
	static void printSubarray (TeilArray subarray) {
		for (int i = 0; i < subarray.anfang; i++) {
			System.out.print(subarray.gesamtarray[i]);
			System.out.print(" ");
		}
		System.out.print("[");
		
		int summe = 0;
		for (int i = subarray.anfang; i < subarray.ende; i++) {
			summe += subarray.gesamtarray[i];
			System.out.print(subarray.gesamtarray[i]);
			System.out.print(" ");
		}
		// letztes Element ohne Leerzeichen ausgeben
		if (subarray.anfang <= subarray.ende) {
			summe += subarray.gesamtarray[subarray.ende];
			System.out.print(subarray.gesamtarray[subarray.ende]);
		}
		
		System.out.print("]");
		for (int i = subarray.ende + 1; i < subarray.gesamtarray.length; i++) {
			System.out.print(" ");
			System.out.print(subarray.gesamtarray[i]);
		}
		
		System.out.println(" (Summe " + summe + ")");
	}
	
}
