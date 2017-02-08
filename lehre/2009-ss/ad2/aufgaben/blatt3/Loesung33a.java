/* 
 * $Id: Loesung33a.java 2009-05-18 $
 * Arne Johannessen
 */


class Loesung33a extends Menge {
	
	
	/**
	 * Methode zur Loesung der Aufgabe 3-3 (a).
	 * Liefert die Anzahl der Elemente der Menge.
	 */
	int groesse () {
		
		/* Gegeben: Array A (mit n Elementen)
		 * 
		 * Start
		 *     gib n zurueck (Anzahl der Elemente in A)
		 * Stop
		 */
		
		return menge.length;
	}
	
	
	Loesung33a (int[] menge) {
		super(menge);  // Konstruktor erben
	}
	
	
	/**
	 * main-Methode zum Testen von der Kommandozeile aus.
	 */
	public static void main (String[] args) {
		int[] array = new int[] {-9, -2, 0, 1, 2, 5, 9, 12, 42, 815};
		Loesung33a meineMenge = new Loesung33a(array);
		
		System.out.println( meineMenge.groesse() );  // 10
	}
	
}
