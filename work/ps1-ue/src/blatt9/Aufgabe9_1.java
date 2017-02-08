/* 
 * $Id: Aufgabe9_1.java $ 
 * Programmiersprachen I
 * 
 * Encoding: us-ascii
 * 
 * Created by Arne Johannessen on 2005-12-20.
 */

package de.thaw.ps1.blatt9;

import de.thaw.ps1.Utils;



/**
 * Blatt 9, Aufgabe 1: Sichere Eingabe.
 * @see <a href="http://www.christophweser.de/java/aufgaben/WS2005/Blatt9.pdf">9. &Uuml;bungsblatt PS1</a>
 * @author Arne Johannessen
 */
public class Aufgabe9_1 {
	
	public static void main (String args []) {
		String input = null;
		
		while (true) {
			try {
				input = Utils.inputString("Gib eine Zahl oder \"ende\" ein: ");
				Integer.parseInt(input);
			}
			catch (NumberFormatException exception) {
				if (input != null && input.equals("ende")) {
					System.exit(0);
				}
				System.out.println("Sorry, war keine Zahl.");
			}
		}
	}
	
}
