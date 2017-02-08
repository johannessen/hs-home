/* 
 * $Id: Aufgabe2_3.java $ 
 * Programmiersprachen I
 * 
 * Encoding: us-ascii
 * 
 * Created by Arne Johannessen on 2005-11-07.
 */

package de.thaw.ps1.blatt2;

import java.math.BigInteger;
import de.thaw.ps1.Utils;
import de.thaw.ps1.NegativeArgumentException;



/**
 * Blatt 2, Aufgabe 3: Nochmals Fakult&auml;t berechnen.
 * @see <a href="http://www.christophweser.de/java/aufgaben/WS2005/Blatt2.pdf">2. &Uuml;bungsblatt PS1</a>
 */
public class Aufgabe2_3 {
	
	/**
	 * Berechnet die Fakult&auml;t einer Zahl. Die Zahl, deren Fakult&auml;t
	 * berechnet werden soll, darf nicht negativ sein, da Fakult&auml;ten nicht
	 * f&uuml;r negative Zahlen definiert sind.
	 * <p>Diese Implementierung benutzt {@link BigInteger BigInteger}
	 * und hat daher eine unbegrenzte Pr&auml;zision.
	 * @param number Eine String-Repr&auml;sentation der Zahl, deren
	 * Fakult&auml;t berechnet werden soll.
	 * @return Die berechnete Fakult&auml;t.
	 * @throws NegativeArgumentException falls <var>number</var> negativ ist.
	 * @see de.thaw.ps1.blatt1.Aufgabe1_4#faculty(long)
	 */
	public static BigInteger faculty (BigInteger number) {
		// uses a "while"-loop for calculation as per excercise instruction
		// see Aufgabe1_4#faculty(long) for a recursive implementation
		
		BigInteger faculty;
		BigInteger index;
		
		if (number.compareTo(BigInteger.ZERO) == -1) {
			throw new NegativeArgumentException();
		}
		
		faculty = BigInteger.ONE;
		index = number;
		while (index.compareTo(BigInteger.ONE) == +1) {
			faculty = faculty.multiply(index);
			index = index.subtract(BigInteger.ONE);
		}
		return faculty;
	}
	
	
	/**
	 * Liest eine Zahl ein und gibt deren Fakult&auml;t aus.
	 */
	public static void main (String[] args) {
		BigInteger number;
		
		try {
			number = new BigInteger(Utils.inputString("Gib eine Zahl ein, von der die Fakult\u00e4t berechnet werden soll: "));
			System.out.println(number+"! = "+Aufgabe2_3.faculty(number).toString());
			
		}
		catch (NumberFormatException exception) {
			System.out.println("Dies ist keine g\u00fcltige Ganzzahl.");
		}
		catch (NegativeArgumentException exception) {
			System.out.println("Die Fakult\u00e4t einer negativen Zahl ist nicht definiert.");
		}
	}
	
}
