/* 
 * $Id: Loesung14.java 2009-04-27 $
 * Arne Johannessen
 */


class Loesung14 {
	
	/**
	 * Methode zur Loesung der Aufgabe 1-4.
	 * Errechnet die Fibonacci-Folge bis zur 'n'-ten Fibonaccizahl.
	 */
	static int[] fibonacciFolge (int length) {
		
		// Eingabewerte kleiner 2 gesondert behandeln
		if (length == 0) {
			return new int[] {};
		}
		if (length == 1) {
			return new int[] {0};
		}
		
		int[] folge = new int[length];
		
		folge[0] = 0;
		folge[1] = 1;
		for (int i = 2; i < folge.length; i++) {
			folge[i] = folge[i - 1] + folge[i - 2];
		}
		
		return folge;
		
	}
	
	
	/**
	 * main-Methode als Treiber fuer den Aufruf von der Kommandozeile.
	 * Ruft obige Methode mit Testwerten auf.
	 */
	public static void main (String[] args) {
		// Loesung05.java muss hierfuer im gleichen Verzeichnis liegen
		Loesung05.printArray( fibonacciFolge(7) );  // 0 1 1 2 3 5 8
	}
	
}
