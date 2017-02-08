/* 
 * $Id: Aufgabe1_4.java $ 
 * Programmiersprachen I
 * 
 * Encoding: us-ascii
 * 
 * Created by Arne Johannessen on 2005-10-20.
 */

package de.thaw.ps1.blatt1;

import de.thaw.ps1.Utils;
import de.thaw.ps1.NegativeArgumentException;



/**
 * Blatt 1, Aufgabe 4: Fakult&auml;t berechnen.
 * @see <a href="http://www.christophweser.de/java/aufgaben/WS2005/Blatt1.pdf">1. &Uuml;bungsblatt PS1</a>
 */
public class Aufgabe1_4 {
	
	/**
	 * Berechnet die Fakult&auml;t einer Zahl. Die Zahl, deren Fakult&auml;t
	 * berechnet werden soll, darf nicht negativ sein, da Fakult&auml;ten nicht
	 * f&uuml;r negative Zahlen definiert sind.
	 * @param number Die Zahl, deren Fakult&auml;t berechnet werden soll.
	 * @return Die berechnete Fakult&auml;t.
	 * @throws NegativeArgumentException falls <var>number</var> negativ ist.
	 */
	public static long faculty (long number) {
		if (number < 0) {
			throw new NegativeArgumentException();
		}
		if (number > 1) {
			return number * faculty(number - 1);
		}
		else {
			return 1;
		}
	}
	
	
	/**
	 * Liest eine Zahl ein und gibt deren Fakult&auml;t aus.
	 */
	public static void main (String[] args) {
		int number;
		
		number = Utils.inputInteger("Gib eine Zahl ein: ");
		try {
			System.out.println(number+"! = "+Aufgabe1_4.faculty((long)number));
		}
		catch (NegativeArgumentException exception) {
			System.out.println("Die Fakult\u00e4t einer negativen Zahl ist nicht definiert.");
		}
	}
	
}
