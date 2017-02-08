/* 
 * $Id: Loesung21.java 2009-04-27 $
 * Arne Johannessen
 */


class Loesung21 {
	
	/**
	 * Methode zur Loesung der Aufgabe 2-1.
	 * Errechnet den groessten gemeinsamen Teiler zweier Zahlen.
	 */
	static int ggT (int a, int b) {
		
		/* Warum dieser Algorithmus funktioniert, waere vielleicht Thema fuer eine
		 * Lehrveranstaltung in Mathematik; uns interessiert es nicht. Entscheidend
		 * ist bei dieser Aufgabe allein die Uebersetzung von "natuerlicher Sprache"
		 * nach Java.
		 */
		
		if (b == 0) {
			return a;
		}
		else {
			return ggT(b, a % b);
		}
		
	}
	
	
	/**
	 * main-Methode als Treiber fuer den Aufruf von der Kommandozeile.
	 * Ruft obige Methode mit Testwerten auf.
	 */
	public static void main (String[] args) {
		System.out.println( ggT(12, 8) );  // 4
		System.out.println( ggT(1711, 1829) );  // 59
		System.out.println( ggT(55813, 10) );  // 1
	}
	
}
