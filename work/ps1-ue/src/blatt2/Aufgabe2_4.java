/* 
 * $Id: Aufgabe2_4.java $ 
 * Programmiersprachen I
 * 
 * Encoding: us-ascii
 * 
 * Created by Arne Johannessen on 2005-11-07.
 */

package de.thaw.ps1.blatt2;

import de.thaw.ps1.Utils;



/**
 * Blatt 2, Aufgabe 4: Vergleich von Zeichenketten.
 * @see <a href="http://www.christophweser.de/java/aufgaben/WS2005/Blatt2.pdf">2. &Uuml;bungsblatt PS1</a>
 */
public class Aufgabe2_4 {
	
	/**
	 * Liest zwei Strings ein und pr&uuml;ft auf Gleichheit.
	 * Gro&szlig;-/Kleinschreibung wird ignoriert.
	 */
	public static void main (String[] args) {
		String[] line = new String[2];
		
		line[0] = Utils.inputString("Gib eine Textzeile ein: ");
		line[1] = Utils.inputString("Gib noch eine Textzeile ein: ");
		
		System.out.print("Die beiden eingegebenen Textzeilen sind ");
		if (! line[0].equalsIgnoreCase(line[1])) {
			System.out.print("nicht ");
		}
		System.out.println("gleich (unter Vernachl\u00e4ssigung der Gro\u00df- und Kleinschreibung).");
	}
	
}
