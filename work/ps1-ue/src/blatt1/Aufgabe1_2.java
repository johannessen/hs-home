/* 
 * $Id: Aufgabe1_2.java $ 
 * Programmiersprachen I
 * 
 * Encoding: us-ascii
 * 
 * Created by Arne Johannessen on 2005-10-20.
 */

package de.thaw.ps1.blatt1;

import de.thaw.ps1.Utils;



/**
 * Blatt 1, Aufgabe 2: Gr&ouml;&szlig;envergleich.
 * @see <a href="http://www.christophweser.de/java/aufgaben/WS2005/Blatt1.pdf">1. &Uuml;bungsblatt PS1</a>
 */
class Aufgabe1_2 {
	// no re-usable code inside, thus not "public"
	
	/**
	 * Liest nacheinander zwei Zahlen ein und gibt aus, welche gr&ouml;&szlig;er
	 * ist (oder ob die Zahlen gleich gro&szlig; sind).
	 */
	public static void main (String[] args) {
		int[] number = new int[2];
		
		number[0] = Utils.inputInteger("Gib eine Zahl ein: ");
		number[1] = Utils.inputInteger("Gib eine weitere Zahl ein: ");
		if (number[0] == number[1]) {
			System.out.println("Die beiden Zahlen sind gleich gro\u00df.");
		}
		else {
			System.out.println("Die erste Zahl ist "+((number[0] < number[1]) ? "kleiner" : "gr\u00f6\u00dfer")+" als die zweite.");
		}
	}
	
}
