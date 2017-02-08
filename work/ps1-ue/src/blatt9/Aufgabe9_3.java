/* 
 * $Id: Aufgabe9_3.java $ 
 * Programmiersprachen I
 * 
 * Encoding: us-ascii
 * 
 * Created by Arne Johannessen on 2005-12-20.
 */

package de.thaw.ps1.blatt9;

import de.thaw.ps1.NegativeArgumentException;



/**
 * Blatt 9, Aufgabe 3: Ein kleiner Rechner.
 * @see <a href="http://www.christophweser.de/java/aufgaben/WS2005/Blatt9.pdf">9. &Uuml;bungsblatt PS1</a>
 * @author Arne Johannessen
 */
public class Aufgabe9_3 {
	
	
	/**
	 * this funny little method calculates the length of a hypotenuse.
	 */
	public static double calculateHypotenuse (int firstValue, int secondValue) throws NegativeArgumentException {
		if (firstValue < 0 || secondValue < 0) {
			throw new NegativeArgumentException("Negative Katheten existieren nicht.");
		}
		
		return Math.sqrt((firstValue * firstValue) + (secondValue * secondValue));
	}
	
	
	public static void main (String args []) {
		double ergebnis;
		int a;
		
		try {
			a = Integer.parseInt(args[0]);
			ergebnis = Aufgabe9_3.calculateHypotenuse(a, a);
			System.out.println("Ergebnis: "+ergebnis);
		}
		catch (NumberFormatException exception) {
			System.out.println("Die eingegebene Kathetenlaenge muss eine Zahl sein.");
			System.exit(0);  // unnecessary, but as per exercise instruction
		}
		catch (NegativeArgumentException exception) {
			System.out.println(exception.getMessage());
		}
	}
	
}
