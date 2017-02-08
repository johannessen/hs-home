/* 
 * $Id: Aufgabe10_1.java $ 
 * Programmiersprachen I
 * 
 * Encoding: us-ascii
 * 
 * Created by Arne Johannessen on 2006-01-17.
 */

package de.thaw.ps1.blatt10;




/**
 * Blatt 10, Aufgabe 4: Nun der Rechner.
 * @see <a href="http://www.christophweser.de/java/aufgaben/WS2005/Blatt10.pdf">10. &Uuml;bungsblatt PS1</a>
 * @author Arne Johannessen
 */
public class Rechner {
	
	
	public static void main (String[] args) {
		KomplexeZahl komplexeZahl1, komplexeZahl2;
		
		komplexeZahl1 = new KomplexeZahl(1, 0);
		komplexeZahl2 = new KomplexeZahl(0, 1);
		
		System.out.println("Die Summe von "+komplexeZahl1+" und "+komplexeZahl2+
				" lautet: "+komplexeZahl1.doAddition(komplexeZahl2));
		try {
			System.out.println("Eine Division von "+komplexeZahl1+" und "+
					komplexeZahl2+" fuehrt zum Ergebnis: "+
					komplexeZahl1.doDivision(komplexeZahl2));
		}
		catch (ArithmeticException exception) {
			System.out.println("Eine Division ist nicht moeglich (Division durch"+
					" null)");
		}
	}
	
}
