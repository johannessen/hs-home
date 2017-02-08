/* 
 * $Id: Loesung43a.java 2009-05-17 $
 * Arne Johannessen
 */


class Loesung43a extends Loesung31 {
	
	
	/**
	 * Methode zur Loesung der Aufgabe 4-3 (a).
	 * Iterative Implementierung der binaeren Suche nach einer Zahl
	 * in der Menge.
	 */
	int finde (int zahl) {
		int links = 0;
		int rechts = menge.length - 1;
		
		while (links <= rechts) {
			
			// 'mittleren Index m' berechnen
			int mitte = (links + rechts) / 2;
			
			// binaere Suche: Grenzen fuer naechsten Schritt setzen
			if (zahl < menge[mitte]) {
				rechts = mitte - 1;
			}
			else if (zahl > menge[mitte]) {
				links = mitte + 1;
			}
			else {
				return mitte;  // die Suche ist erfolgreich
			}
		}
		
		// an diesem Punkt ist die Suche fehlgeschlagen
		// es ist kein Array-Teil mehr uebrig
		return -1;
	}
	
	
	Loesung43a (int[] menge) {
		super(menge);  // Konstruktor erben
	}
	
	
	/**
	 * main-Methode zum Testen von der Kommandozeile aus.
	 */
	public static void main (String[] args) {
		int[] array = new int[] {-9, -2, 0, 1, 2, 5, 9, 12, 42, 815};
		Loesung43a meineMenge = new Loesung43a(array);
		
		System.out.println( meineMenge.finde(2) );  // 4
		System.out.println( meineMenge.finde(5) );  // 5
		System.out.println( meineMenge.finde(-9) );  // 0
		System.out.println( meineMenge.finde(0) );  // 2
		System.out.println( meineMenge.finde(-1000) );  // -1
		System.out.println( meineMenge.finde(1000) );  // -1
	}
	
}
