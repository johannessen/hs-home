/* 
 * $Id: Loesung32.java 2009-05-17 $
 * Arne Johannessen
 */


class Loesung32 extends Loesung31 {
	
	
	/**
	 * Methode zur Loesung der Aufgabe 3-2.
	 * Prueft, ob eine bestimmte Zahl in der Menge enthalten ist.
	 */
	boolean enthaelt (int zahl) {
		return finde(zahl) >= 0;
	}
	
	
	Loesung32 (int[] menge) {
		super(menge);  // Konstruktor erben
	}
	
	
	/**
	 * main-Methode zum Testen von der Kommandozeile aus.
	 */
	public static void main (String[] args) {
		int[] array = new int[] {-9, -2, 0, 1, 2, 5, 9, 12, 42, 815};
		Loesung32 meineMenge = new Loesung32(array);
		
		System.out.println( meineMenge.enthaelt(2) );  // true
		System.out.println( meineMenge.enthaelt(5) );  // true
		System.out.println( meineMenge.enthaelt(-9) );  // true
		System.out.println( meineMenge.enthaelt(0) );  // true
		System.out.println( meineMenge.enthaelt(-1000) );  // false
		System.out.println( meineMenge.enthaelt(1000) );  // false
	}
	
}
