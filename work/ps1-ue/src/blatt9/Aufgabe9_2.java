/* 
 * $Id: Aufgabe9_2.java $ 
 * Programmiersprachen I
 * 
 * Encoding: us-ascii
 * 
 * Created by Arne Johannessen on 2005-12-20.
 */

package de.thaw.ps1.blatt9;

import de.thaw.ps1.Utils;



/**
 * Blatt 9, Aufgabe 2: Arrays und die Exceptions.
 * @see <a href="http://www.christophweser.de/java/aufgaben/WS2005/Blatt9.pdf">9. &Uuml;bungsblatt PS1</a>
 * @author Arne Johannessen
 */
public class Aufgabe9_2 {
	
	public static void main (String args []) {
		int[] array = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
		int index;
		
		index = Utils.inputInteger("Gib eine Zahl zwischen 0 und 9 ein: ");
		if (index < 0) {
			System.out.println("Die Zahl darf nicht kleiner als null (0) sein.");
			return;
		}
		if (index >= 10) {
			System.out.println("Die Zahl darf nicht groesser als neun (9) sein.");
			return;
		}
		
//		try {
			System.out.println("Die Zahl im Array an der Stelle ["+index+"] lautet "+array[index]+".");
/*		}
		catch (ArrayIndexOutOfBoundsException exception) {
			System.out.println("Diese Zahl liegt nicht zwischen 0 und 9.");
		}
*/	}
	
}
