/* 
 * $Id: Aufgabe2_1.java $ 
 * Programmiersprachen I
 * 
 * Encoding: us-ascii
 * 
 * Created by Arne Johannessen on 2005-11-07.
 */

package de.thaw.ps1.blatt2;

import de.thaw.ps1.Utils;



/**
 * Blatt 2, Aufgabe 1: Operatoren
 * @see <a href="http://www.christophweser.de/java/aufgaben/WS2005/Blatt2.pdf">2. &Uuml;bungsblatt PS1</a>
 */
public class Aufgabe2_1 {
	
	/**
	 * Liest zwei ganze Zahlen ein und gibt den ganzzahligen Quotienten, den
	 * ganzzahligen Rest, die Summe, die Differenz und das Produkt aus.
	 * Ausserdem wird auf Gleichheit der beiden Zahlen geprueft.
	 */
	public static void main (String[] args) {
		int[] number = new int[2];
		
		number[0] = Utils.inputInteger("Gib eine Zahl ein: ");
		number[1] = Utils.inputInteger("Gib eine weitere Zahl ein: ");
		
		System.out.println(number[0]+" / "+number[1]+" = "+Math.floor(number[0] / number[1]));
		System.out.println(number[0]+" % "+number[1]+" = "+(number[0] % number[1]));
		System.out.println(number[0]+" + "+number[1]+" = "+(number[0] + number[1]));
		System.out.println(number[0]+" - "+number[1]+" = "+(number[0] - number[1]));
		System.out.println(number[0]+" * "+number[1]+" = "+(number[0] * number[1]));
		System.out.println(number[0]+((number[0] == number[1]) ? " = " : " \u2260 ")+number[1]);
	}
	
}
