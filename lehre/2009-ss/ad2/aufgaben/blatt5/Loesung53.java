/* 
 * $Id: Loesung53.java 2009-05-23 $
 * Arne Johannessen
 */


class Loesung53 extends Menge {
	
	
	/**
	 * Methode zur Loesung der Aufgabe 5-3.
	 * Sortiert die Menge ab dem Index 'beginn'.
	 * (Selection-Sort rekursiv)
	 */
	void recursiveSort (int beginn) {
		
		// falls kein Array-Teil mehr uebrig ist, ist Sortieren nicht notwendig
		if (beginn >= menge.length) {
			return;
		}
		
		// kleinstes Element des (Rest-)Arrays bestimmen
		int kleinstes = beginn;
		for (int i = beginn + 1; i < menge.length; i++) {
			if (menge[i] < menge[kleinstes]) {
				kleinstes = i;
			}
		}
		
		// kleinstes Element an richtige Stelle setzen (ganz vorne)
		int kleinsterWert = menge[kleinstes];
		menge[kleinstes] = menge[beginn];
		menge[beginn] = kleinsterWert;
		
		// rekursiv den Rest des Arrays sortieren
		recursiveSort(beginn + 1);
	}
	
	
	/**
	 * Sortiert die Menge.
	 */
	void recursiveSort () {
		recursiveSort(0);  // Anfangswert fuer 'beginn'
	}
	
	
	Loesung53 (int[] menge) {
		super(menge);  // Konstruktor erben
	}
	
	
	/**
	 * main-Methode zum Testen von der Kommandozeile aus.
	 */
	public static void main (String[] args) {
		int[] array = new int[] {9, 5, -2, 12, 815, 1, 42, -9, 2, 0};
		Loesung53 meineMenge = new Loesung53(array);
		
		meineMenge.recursiveSort();
		
		Loesung05.printArray(meineMenge.menge);
	}
	
}
