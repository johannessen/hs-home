/* 
 * $Id: Aufgabe1_3.java $ 
 * Programmiersprachen I
 * 
 * Encoding: us-ascii
 * 
 * Created by Arne Johannessen on 2005-10-20.
 */

package de.thaw.ps1.blatt1;

import de.thaw.ps1.Utils;



/**
 * Blatt 1, Aufgabe 3: Summe berechnen.
 * @see <a href="http://www.christophweser.de/java/aufgaben/WS2005/Blatt1.pdf">1. &Uuml;bungsblatt PS1</a>
 */
class Aufgabe1_3 {
	// no re-usable code inside, thus not "public"
	
	/**
	 * Liest nacheinander zwei Zahlen ein und gibt deren Summe und Produkt aus.
	 */
	public static void main (String[] args) {
		int[] number = new int[2];
		
		number[0] = Utils.inputInteger("Gib eine Zahl ein: ");
		number[1] = Utils.inputInteger("Gib eine weitere Zahl ein: ");
		System.out.println("Die Summe der beiden Zahlen ist "+(number[0] + number[1])+" und ihr Produkt ist "+(number[0] * number[1])+".");
	}
	
}
