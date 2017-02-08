/* 
 * $Id: Loesung22.java 2009-04-27 $
 * Arne Johannessen
 */


class Loesung22 {
	
	/**
	 * Methode zur Loesung der Aufgabe 2-2.
	 * Errechnet die Quersumme einer Zahl.
	 */
	static int quersumme (int zahl) {
		
		if (zahl == 0) {
			return 0;
		}
		
		int letzteZiffer = zahl % 10;
		int restlicheZiffern = zahl / 10;
		
		return letzteZiffer + quersumme(restlicheZiffern);
		
	}
	
	
	/**
	 * main-Methode als Treiber fuer den Aufruf von der Kommandozeile.
	 * Ruft obige Methode mit Testwerten auf.
	 */
	public static void main (String[] args) {
		System.out.println( quersumme(82457) );  // 26
		System.out.println( quersumme(1919191919) );  // 50
		System.out.println( quersumme(1) );  // 1
		System.out.println( quersumme(1000000000) );  // 1
	}
	
}
