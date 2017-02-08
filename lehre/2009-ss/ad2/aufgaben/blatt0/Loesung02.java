/* 
 * $Id: Loesung02.java 2009-04-06 $
 * Arne Johannessen
 * 
 * Compilieren: javac Loesung02.java
 * Ausfuehren:  java Loesung02
 */


class Loesung02 {
	
	/* 
	 * direkt in Klassen duerfen stehen:
	 * - Klassen- und Objektvariablen
	 * - Klassen- und Objektmethoden
	 * - Konstruktoren
	 * - Kommentare :)
	 * 
	 * aber _keine_ Anweisungen (die gehoeren in Methoden oder Konstruktoren!)
	 */
	
	
	/**
	 * Standard-Konstruktor.
	 * Schreibt "Hello world" beim Erzeugen jeder neuen Instanz.
	 */
	Loesung02 () {
		System.out.println("Hello world.");
	}
	
	
	/**
	 * main-Methode als Treiber fuer den Aufruf von der Kommandozeile.
	 * Erzeugt eine Instanz dieser Klasse.
	 */
	public static void main (String[] args) {
		new Loesung02();
	}
	
}
