/* 
 * $Id: Loesung91.java 2009-06-22 $
 * Arne Johannessen
 */



class Loesung91 {
	
	
	
	/**
	 * Methode zur Loesung der Aufgabe 9-1.
	 * Errechnet die groesste Summe der Elemente aller Sub-Arrays eines
	 * Arrays im Divide-and-Conquer--Verfahren.
	 */
	static int maximumSubarray (int[] array, int linkeGrenze, int rechteGrenze) {
		
		// Abbruchbedingung: triviale Faelle
		if (linkeGrenze >= rechteGrenze) {
			if (linkeGrenze > rechteGrenze) {
				return 0;  // leerer Array
			}
			if (array[linkeGrenze] < 0) {
				return 0;  // nur ein Element (negativ => Maximum ist leerer Sub-Array)
			}
			return array[linkeGrenze];  // nur ein Element
		}
		
		// Split (Divide)
		int mitte = (linkeGrenze + rechteGrenze) / 2;
		
		// Rekursion (Conquer)
		int linkeMaximaleSumme = maximumSubarray(array, linkeGrenze, mitte);
		int rechteMaximaleSumme = maximumSubarray(array, mitte + 1, rechteGrenze);
		
		// Join (Merge)
		int linkerTeilRechterRandMaximaleSumme = rechtesRandmaximum(array, linkeGrenze, mitte);
		int rechterTeilLinkerRandMaximaleSumme = linkesRandmaximum(array, mitte + 1, rechteGrenze);
		int mittlereMaximaleSumme = linkerTeilRechterRandMaximaleSumme + rechterTeilLinkerRandMaximaleSumme;
		
		// groesstes der drei Maxima ermitteln (noch Join)
		int maximaleSumme = mittlereMaximaleSumme;
		if (linkeMaximaleSumme > maximaleSumme) {
			maximaleSumme = linkeMaximaleSumme;
		}
		if (rechteMaximaleSumme > maximaleSumme) {
			maximaleSumme = rechteMaximaleSumme;
		}
		
		// Ergebnis zurueckgeben
		return maximaleSumme;
	}
	
	
	
	/**
	 * Methode zur Loesung der Aufgabe 8-1 (veraendert).
	 * Errechnet die Summe der Elemente des linken Randmaximums eines
	 * Sub-Arrays.
	 */
	static int linkesRandmaximum (int[] array, int linkeGrenze, int rechteGrenze) {
		
		// Sub-Array von links nach rechts durchlaufen und Maximum bestimmen
		int maximaleSumme = 0;
		int summe = 0;
		for (int i = linkeGrenze; i <= rechteGrenze; i++) {
			summe += array[i];
			if (summe > maximaleSumme) {
				maximaleSumme = summe;
			}
		}
		
		return maximaleSumme;
	}
	
	
	
	/**
	 * Methode zur Loesung der Aufgabe 8-2 (veraendert).
	 * Errechnet die Summe der Elemente des rechten Randmaximums eines
	 * Sub-Arrays.
	 */
	static int rechtesRandmaximum (int[] array, int linkeGrenze, int rechteGrenze) {
		
		// Sub-Array von rechts nach links durchlaufen und Maximum bestimmen
		int maximaleSumme = 0;
		int summe = 0;
		for (int i = rechteGrenze; i >= linkeGrenze; i--) {
			summe += array[i];
			if (summe > maximaleSumme) {
				maximaleSumme = summe;
			}
		}
		
		return maximaleSumme;
	}
	
	
	
	// nur zum Testen, nicht Teil der Aufgabenstellung
	static int maximumSubarray (int[] array) {
		return maximumSubarray(array, 0, array.length - 1);  // Anfangswerte fuer die Grenzen
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
