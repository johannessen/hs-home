/* 
 * $Id: Loesung13.java 2009-04-27 $
 * Arne Johannessen
 */


class Loesung13 {
	
	/**
	 * Methode zur Loesung der Aufgabe 1-3.
	 * Errechnet die 'n'-te Fibonaccizahl.
	 */
	static int fibonacci (int n) {
		
		if (n <= 0) {
			return 0;
		}
		if (n == 1) {
			return 1;
		}
		
		return fibonacci(n - 1) + fibonacci(n - 2);
		
	}
	
	
	/**
	 * main-Methode als Treiber fuer den Aufruf von der Kommandozeile.
	 * Ruft obige Methode mit Testwerten auf.
	 */
	public static void main (String[] args) {
		System.out.println( fibonacci(7) );  // 13
	}
	
}
