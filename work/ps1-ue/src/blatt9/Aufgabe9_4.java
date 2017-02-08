/* 
 * $Id: Aufgabe9_4.java $ 
 * Programmiersprachen I
 * 
 * Encoding: us-ascii
 * 
 * Created by Arne Johannessen on 2005-12-20.
 */

package de.thaw.ps1.blatt9;



/**
 * Blatt 9, Aufgabe 4: Eine eigene Exception.
 * @see <a href="http://www.christophweser.de/java/aufgaben/WS2005/Blatt9.pdf">9. &Uuml;bungsblatt PS1</a>
 * @author Arne Johannessen
 */
public class Aufgabe9_4 {
	
	
	/**
	 * this funny little method calculates the length of a hypotenuse.
	 */
	public static double calculateHypotenuse (int firstValue, int secondValue) throws NegativeArgumentException {
		if (firstValue < 0 || secondValue < 0) {
			throw new NegativeArgumentException();
		}
		
		return Math.sqrt((firstValue * firstValue) + (secondValue * secondValue));
	}
	
	
	public static void main (String args []) {
		double ergebnis;
		int a;
		
		a = Integer.parseInt(args[0]);
		try {
			ergebnis = Aufgabe9_4.calculateHypotenuse(a, a);
			System.out.println("Ergebnis: "+ergebnis);
		}
		catch (NegativeArgumentException exception) {
			exception.getInfo();
		}
	}
	
}
