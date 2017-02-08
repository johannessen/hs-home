/* 
 * $Id: Loesung31.java 2009-05-17 $
 * Arne Johannessen
 */


class Loesung31 extends Menge {
	
	
	/**
	 * Methode zur Loesung der Aufgabe 3-1.
	 * Binaere Suche nach einer Zahl in der Menge. Die Suche ist auf den
	 * Bereich von 'links' bis 'rechts' beschraenkt (jeweils einschliesslich).
	 */
	int finde (int zahl, int links, int rechts) {
		
		// falls kein Array-Teil mehr uebrig ist, schlaegt die Suche fehl
		if (links > rechts) {
			return -1;
		}
		
		// 'mittleren Index m' berechnen
		int mitte = (links + rechts) / 2;
		
		// rekursive binaere Suche
		if (zahl < menge[mitte]) {
			return finde(zahl, links, mitte - 1);
		}
		else if (zahl > menge[mitte]) {
			return finde(zahl, mitte + 1, rechts);
		}
		
		// an diesem Punkt ist die Suche erfolgreich
		// (zahl == menge[mitte])
		return mitte;
	}
	
	
	/**
	 * Binaere Suche nach einer Zahl in der Menge.
	 */
	int finde (int zahl) {
		return finde(zahl, 0, menge.length - 1);  // Anfangswerte fuer 'links' und 'rechts'
	}
	
	
	Loesung31 (int[] menge) {
		super(menge);  // Konstruktor erben
	}
	
	
	/**
	 * main-Methode zum Testen von der Kommandozeile aus.
	 */
	public static void main (String[] args) {
		int[] array = new int[] {-9, -2, 0, 1, 2, 5, 9, 12, 42, 815};
		Loesung31 meineMenge = new Loesung31(array);
		
		System.out.println( meineMenge.finde(2) );  // 4
		System.out.println( meineMenge.finde(5) );  // 5
		System.out.println( meineMenge.finde(-9) );  // 0
		System.out.println( meineMenge.finde(0) );  // 2
		System.out.println( meineMenge.finde(-1000) );  // -1
		System.out.println( meineMenge.finde(1000) );  // -1
	}
	
}
